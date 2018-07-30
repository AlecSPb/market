package ru.tsystem.javaschool.ordinaalena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;
import ru.tsystem.javaschool.ordinaalena.entities.Product;
import ru.tsystem.javaschool.ordinaalena.services.api.ProductService;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/advertising")
public class AdvertisingRestController {

    private static final Logger logger=Logger.getLogger(AdvertisingRestController.class);
    private final Set<SseEmitter> emitters= Collections.synchronizedSet(new HashSet<>());
    private final ProductService productService;

    @Autowired
    public AdvertisingRestController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/stand")
    public List<Map<String,String>> getStandInformation(){
        List<Product> top = productService.getTops();
        List<ProductDTO> list;
        if (top.isEmpty()) list = productService.findTopProduct();
        else list = productService.convertProductsToProductsDTO(top);
        List<Map<String,String >> tops=new ArrayList<>();
        for(ProductDTO product:list){
            Map<String,String> item=new HashMap<>();
            item.put("id",String.valueOf(product.getId()));
            item.put("title",product.getTitle());
            item.put("price",String.valueOf(product.getPrice()));
            tops.add(item);
        }
        new Timer(true).schedule(new TimerTask() {
            @Override
            public void run() {
                sendNotificationForAllSubscribers();
            }
        }, 10000);

        //log
        logger.info("Someone has requested stand information. Application is returning list of top products.");

        return tops;
        }


    @RequestMapping(value = "/stand/connection")
   public SseEmitter openConnection(HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        final SseEmitter emitter= new SseEmitter(20000L);

        emitter.onTimeout(emitter::complete);
        emitter.onCompletion(()-> {
            synchronized (this.emitters) {
                emitters.remove(emitter);
            }
        });
         emitters.add(emitter);
         return emitter;
        }
     @RequestMapping(value = "/stand/update")
    private void sendNotificationForAllSubscribers(){
      synchronized (this.emitters){
          for (SseEmitter emitter:emitters){
              try{
                  emitter.send("update");
                  emitter.complete();
              }
              catch (Exception ignored){
          }
      }
     }
     logger.info("All stand viewers have received message to refresh page in browser.");

}}

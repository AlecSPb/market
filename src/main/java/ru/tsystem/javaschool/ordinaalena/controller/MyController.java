package ru.tsystem.javaschool.ordinaalena.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tsystem.javaschool.ordinaalena.DAO.UsertDAO;
import ru.tsystem.javaschool.ordinaalena.DAO.UsertDAOImpl;
import ru.tsystem.javaschool.ordinaalena.models.UsertEntity;


@Controller
public class MyController {

 /*  @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
   public String hello(@PathVariable String name, Model model){
       model.addAttribute ("name",name);
          return "index"; }


 @RequestMapping(value = "/", method = RequestMethod.GET)
    public String toIndexPage(Model ui){
        ui.addAttribute("user", new UsertEntity("user1", "ln1"));
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postFormData(UsertEntity u, BindingResult br, Model ui){

        System.out.println(br.toString());
        System.out.println(u.getUsername());
        System.out.println(u.getPassword());



        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        UsertDAOImpl implementation = new UsertDAOImpl(factory);
        implementation.create(u.getUsername(), u.getPassword());

        ui.addAttribute("user", u);

        return "/index";
    }

}*/


    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String indexPost(UsertEntity user, BindingResult bindingResult, Model ui){
        UsertDAO usersDAO = UsertDAOImpl.getUsertDAOInstance();
        UsertEntity createdUser = usersDAO.create(user.getUsername(),user.getPassword());

        ui.addAttribute("createdUserName", user.getUsername());
        ui.addAttribute("createdUserPassword", user.getPassword());


        return "signup/success";
    }





    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexGet(){

        return "signup/failed";
    }

}
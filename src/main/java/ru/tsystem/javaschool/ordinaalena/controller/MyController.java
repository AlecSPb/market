package ru.tsystem.javaschool.ordinaalena.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tsystem.javaschool.ordinaalena.entities.Customer;


@Controller
public class MyController {


    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String indexPost(Customer customer, BindingResult bindingResult, Model ui){

        ui.addAttribute("customer", customer);

        return "checkout";
    }





    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String indexGet(){

        return "/checkout";
    }

}
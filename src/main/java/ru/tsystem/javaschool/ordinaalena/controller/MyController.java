package ru.tsystem.javaschool.ordinaalena.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tsystem.javaschool.ordinaalena.entities.Customer;


@Controller
public class MyController {

    /**
     * @return home page
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

}
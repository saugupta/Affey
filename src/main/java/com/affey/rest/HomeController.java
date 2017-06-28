package com.affey.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
 
    @RequestMapping(value="/api")
    public String api(){
        return "redirect:/swagger-ui.html";
    }
 
    @RequestMapping(value="/swagger")
    public String swagger(){
        return "redirect:/swagger-ui.html";
    }
    @RequestMapping(value="/login")
    public String login(){
        return "redirect:/templates/loginlocal.html";
    }
    @RequestMapping(value="/login1")
    public String login1(){
        return "redirect:/templates/loginlocal.html";
    }
    @RequestMapping(value="/login2")
    public String login2(){
        return "redirect:/loginlocal.html";
    }
}
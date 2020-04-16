package org.itstep.security.web.controllers;

import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String goHome() {
        return "home";
    }

    @GetMapping("/services")
    public String goServices() {
        return "services";
    }

    @GetMapping("/login")
    public String goLogin() {
        return "login";
    }

    @GetMapping("/schedule")
    public String goSchedule() {
        return "schedule";
    }

    @RequestMapping(value="/register", method=RequestMethod.GET)
    public String goRegister(){
        return "register";
    }
}

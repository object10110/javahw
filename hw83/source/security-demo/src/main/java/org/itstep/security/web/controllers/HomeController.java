package org.itstep.security.web.controllers;

import lombok.extern.slf4j.Slf4j;
import org.itstep.security.domain.entities.AutoUser;
import org.itstep.security.domain.repositories.AutoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

    final AutoUserRepository autoUserRepository;

    public HomeController(AutoUserRepository autoUserRepository) {
        this.autoUserRepository = autoUserRepository;
    }

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

    @GetMapping("/register")
    public String goRegister(){
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@ModelAttribute AutoUser autoUser) {
        log.info("Register user: {}", autoUser);
        autoUser.setPassword("{noop}"+autoUser.getPassword());
        autoUser.setRole("ROLE_USER");
        autoUserRepository.save(autoUser);
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(autoUser, autoUser.getPassword(), autoUser.getAuthorities());
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/hashing")
    public String passwordHash(@RequestParam String pass) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(pass);
    }

}

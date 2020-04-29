package org.itstep.hellospringboot.contoller;

import org.itstep.hellospringboot.domain.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    final StudentRepository studentRepository;

    public HomeController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String hello(@RequestParam(required = false) String name, Model model) {
        if(name == null) {
            name = "Guest";
        }
        model.addAttribute("msg", "Hello " + name);
        return "index";
    }

}

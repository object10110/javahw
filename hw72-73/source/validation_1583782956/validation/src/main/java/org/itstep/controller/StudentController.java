package org.itstep.controller;

import lombok.extern.slf4j.Slf4j;
import org.itstep.data.Repository;
import org.itstep.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Slf4j
@RequestMapping("/")
@Controller
public class StudentController {

    Repository<Student, Integer> repository;

    @Autowired
    public StudentController(Repository<Student, Integer> repository) {
        this.repository = repository;
    }

    @ModelAttribute(name = "students")
    public List<Student> getStudents() {
        log.debug("getStudents()");
        return repository.findAll();
    }

    @ModelAttribute(name = "student")
    public Student getStudent() {
        log.debug("getStudent()");
        return new Student();
    }

    @GetMapping
    public String index() {
//        model.addAttribute("students", repository.findAll());
//        model.addAttribute("student", new Student());
        return "students/index";
    }

    @PostMapping("/new")
    public String createStudent(@Validated @ModelAttribute Student student, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        log.debug(student.toString());
        String message = "";
        if (bindingResult.hasErrors()) {
            log.debug(bindingResult.toString());
            redirectAttributes.addAttribute("message", "Validation error");
            return "students/index";
        }
        try {
            repository.save(student);
            message = "successfully saved";
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
            message = "some error";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            repository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Delete successfully");
        } catch (Throwable ex) {
            redirectAttributes.addFlashAttribute("message", "Error: " + ex.getLocalizedMessage());
        }
        return "redirect:/";
    }
}

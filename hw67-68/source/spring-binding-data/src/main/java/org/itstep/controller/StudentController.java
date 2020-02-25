package org.itstep.controller;

import org.itstep.data.Repository;
import org.itstep.data.StudentRepository;
import org.itstep.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/students")
@Controller
public class StudentController {

    Repository<Student, Integer> repository;

    @Autowired
    public StudentController(Repository<Student, Integer> repository) {
        this.repository = repository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("students", repository.findAll());
        return "students/index";
    }

    @GetMapping("/new")
    public String createStudent() {
        return "students/create";
    }

    @PostMapping("/new")
    public String createStudent(Student student, RedirectAttributes redirectAttributes) {
        String message = "";
        int id = 0;
        try {
            id = repository.save(student);
            message = "successfully saved";
        } catch(Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            message = "some error";
        }
        redirectAttributes.addFlashAttribute("error", message);
        System.out.println(student);
        return "redirect:/students/info/" + id;
    }

    @GetMapping("/info/{id}")
    public String info(@PathVariable int id, Model model) {
        model.addAttribute("student", repository.find(id));
        return "students/info";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            repository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Delete successfully");
        }catch (Throwable ex) {
            redirectAttributes.addFlashAttribute("message", "Error: " + ex.getLocalizedMessage());
        }
        return "redirect:/students";
    }
}

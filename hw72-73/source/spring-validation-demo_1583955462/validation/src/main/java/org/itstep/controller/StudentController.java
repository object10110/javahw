package org.itstep.controller;

import lombok.extern.slf4j.Slf4j;
import org.itstep.data.Repository;
import org.itstep.dto.StudentDto;
import org.itstep.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Slf4j
@RequestMapping("/")
@Controller
public class StudentController {

    Repository<Student, Integer> repository;

    Validator vasjaPupkinValidator;

    @Autowired
    public StudentController(Repository<Student, Integer> repository,
                             Validator vasjaPupkinValidator) {
        this.repository = repository;
        this.vasjaPupkinValidator = vasjaPupkinValidator;
    }

    @ModelAttribute(name = "students")
    public List<Student> getStudents() {
        log.debug("getStudents()");
        return repository.findAll();
    }

    @ModelAttribute(name = "student")
    public StudentDto getStudent() {
        log.debug("getStudent()");
        return new StudentDto();
    }

    @GetMapping
    public String index() {
        return "students/index";
    }

    @PostMapping("/new")
    public String createStudent(@Validated @ModelAttribute(name = "student") StudentDto studentDto, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        log.debug(studentDto.toString());
        String message = "";
        //vasjaPupkinValidator.validate(student, bindingResult);
        if (bindingResult.hasErrors()) {
            log.debug(bindingResult.toString());
            redirectAttributes.addAttribute("message", "Validation error");
            return "students/index";
        }
        try {
            repository.save(new Student(0, studentDto.getFirstName(), studentDto.getLastName(), studentDto.getAge(), studentDto.getGroup()));
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

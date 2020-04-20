package org.itstep.controller;

import lombok.extern.slf4j.Slf4j;
import org.itstep.domain.Group;
import org.itstep.domain.Student;
import org.itstep.service.StudentService;
import org.itstep.service.dto.StudentDto;
import org.itstep.service.mapper.StudentMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class HomeController {
    final StudentService studentService;
    final PasswordEncoder passwordEncoder;
    final StudentMapper studentMapper;

    public HomeController(StudentService studentService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();;
    }


    @GetMapping
    public String home() {
        return "index";
    }

    @PostMapping("/register")
    public String doRegister(@ModelAttribute Student student) {
        log.info("Register student: {}", student);
        //student.setPassword("{noop}"+student.getPassword());
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setRole("ROLE_STUDENT");
        Group group = new Group();
        group.setId(1);
        student.setGroup(group);
        StudentDto studentDto = studentMapper.toDto(student);
        studentService.save(studentDto);
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        Authentication authentication =
                new UsernamePasswordAuthenticationToken((UserDetails)student, student.getPassword(), student.getAuthorities());
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
        return "redirect:/";
    }
}

package org.itstep.service;

import org.itstep.repository.StudentRepository;
import org.itstep.repository.TeacherRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    final StudentRepository studentRepository;
    final TeacherRepository teacherRepository;

    public CustomUserDetailsService(StudentRepository autoUserRepository, TeacherRepository teacherRepository) {
        this.studentRepository = autoUserRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user;
        try {
            user = studentRepository.findStudentByUsername(username);
        } catch (Exception ex) {
            throw new UsernameNotFoundException("Not found student by: " + username, ex);
        }
        if(user==null) {
            try {
                user = teacherRepository.findTeacherByUsername(username);
            } catch (Exception ex) {
                throw new UsernameNotFoundException("Not found teacher by: " + username, ex);
            }
        }
        return user;
    }
}

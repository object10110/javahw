package org.itstep.validator;

import org.itstep.model.Student;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class VasjaPupkinValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Student.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Student s = (Student) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotBlank.student.firstName");
        if("Вася".equals(s.getFirstName()) && "Пупкин".equals(s.getLastName())) {
            errors.rejectValue("firstName", "Vasja.student.firstName");
            errors.rejectValue("lastName", "Pupkin.student.lastName");
        }
    }
}

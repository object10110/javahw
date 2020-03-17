package org.itstep.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ScriptAssert;
import org.itstep.validator.AgeRange;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private int id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @AgeRange
    private int age;
    @NotBlank
    private String group;
    @Past
    private LocalDate birthDate;
}
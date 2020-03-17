package org.itstep.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itstep.validator.AgeRange;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private int id;
    //@NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @AgeRange
    private int age;
    @NotBlank
    private String group;
}
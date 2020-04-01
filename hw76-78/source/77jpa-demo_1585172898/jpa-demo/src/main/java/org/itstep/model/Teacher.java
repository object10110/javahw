package org.itstep.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Table(name = "teacher")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @NonNull
    @NotBlank
    @Length(max = 50)
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @NotBlank
    @Length(max = 50)
    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(mappedBy = "teachers")
    List<Group> groups;
}

package org.itstep.security.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APPOINTMENT")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPOINTMENT_ID")
    private Long appointmentId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    @NonNull
    private AutoUser user;

    @Embedded
    @NonNull
    private Automobile automobile;

    @Column(name = "APPOINTMENT_DT")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NonNull
    private LocalDate appointmentDt;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "SERVICES", joinColumns = {@JoinColumn(name = "APPOINTMENT_ID")})
    @Column(name = "NAME")
    @NonNull
    private List<String> services = new ArrayList<String>();

    @Column(name = "STATUS")
    @NonNull
    private String status;
}

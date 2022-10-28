package com.epam.lashchenkova.registrationServices.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "family_doctors", uniqueConstraints = {
        @UniqueConstraint(columnNames = "doctor_id"),
        @UniqueConstraint(columnNames = {"first_name", "last_name"})
})
public class Doctor extends AbstractEntity{

    @Id
    @Column(name = "doctor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "specialization")
    private String specialization;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH,
            CascadeType.DETACH})
    @JsonIgnore
    private List<Patient> patients;

    @ManyToOne(cascade = { CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH,
            CascadeType.DETACH})
    @JoinColumn(name = "hospital")
    private Hospital hospital;
}

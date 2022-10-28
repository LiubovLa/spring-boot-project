package com.epam.lashchenkova.registrationServices.entities;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "patients", uniqueConstraints = @UniqueConstraint(columnNames = "medical_card"))
public class Patient extends AbstractEntity {

    @Id
    @Column(name = "medical_card")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH,
            CascadeType.DETACH})
    @JoinColumn(name = "family_doctor")
    private Doctor doctor;

}

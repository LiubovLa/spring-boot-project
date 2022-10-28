package com.epam.lashchenkova.polyclinic.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "hospitals", uniqueConstraints = @UniqueConstraint(columnNames = "hospital_id"))
public class Hospital extends AbstractEntity {

    @Id
    @Column(name = "hospital_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Doctor> doctors;
}

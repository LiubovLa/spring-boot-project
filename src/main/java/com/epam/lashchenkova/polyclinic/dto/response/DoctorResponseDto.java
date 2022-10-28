package com.epam.lashchenkova.polyclinic.dto.response;

import com.epam.lashchenkova.polyclinic.dto.AbstractDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
public class DoctorResponseDto extends AbstractDto {

    @NotEmpty()
    private Long id;

    private String firstName;

    private String lastName;

    private String hospital;

    private String specialization;

    private List<String> patients;
}

package com.epam.lashchenkova.polyclinic.dto.request;

import com.epam.lashchenkova.polyclinic.dto.AbstractDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
public class PatientDto extends AbstractDto {

    @NotEmpty(message = "Medical card should not be empty")
    private Long id;

    private String firstName;

    private String lastName;

    @JsonFormat(pattern="YYYY-MM-DD")
    private Date birthday;

    private String address;

    private Long doctor;
}

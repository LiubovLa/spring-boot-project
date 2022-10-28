package com.epam.lashchenkova.polyclinic.dto.request;

import com.epam.lashchenkova.polyclinic.dto.AbstractDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
public class HospitalDto extends AbstractDto {

    @NotEmpty()
    private Long id;

    private String name;

    private String address;
}

package com.epam.lashchenkova.registrationServices.dto.request;

import com.epam.lashchenkova.registrationServices.dto.AbstractDto;
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
public class HospitalDto extends AbstractDto {

    @NotEmpty()
    private Long id;

    private String name;

    private String address;
}

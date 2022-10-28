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
public class HospitalResponseDto extends AbstractDto {

    @NotEmpty()
    private Long id;

    private String name;

    private String address;

    private List<String> doctors;
}

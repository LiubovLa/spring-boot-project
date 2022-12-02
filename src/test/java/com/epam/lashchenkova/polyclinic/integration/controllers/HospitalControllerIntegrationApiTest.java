package com.epam.lashchenkova.polyclinic.integration.controllers;

import com.epam.lashchenkova.polyclinic.controller.HospitalController;
import com.epam.lashchenkova.polyclinic.core.AbstractApiTest;
import com.epam.lashchenkova.polyclinic.dto.request.HospitalDto;
import com.epam.lashchenkova.polyclinic.dto.response.HospitalResponseDto;
import com.epam.lashchenkova.polyclinic.entities.Hospital;
import com.epam.lashchenkova.polyclinic.exception.GlobalExceptionHandler;
import com.epam.lashchenkova.polyclinic.repositories.HospitalRepository;
import com.epam.lashchenkova.polyclinic.services.HospitalService;
import com.epam.lashchenkova.polyclinic.util.Builder;
import com.epam.lashchenkova.polyclinic.util.TestDataFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import javax.servlet.http.HttpServletResponse;

import java.text.MessageFormat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WebMvcTest(HospitalController.class)
@ContextConfiguration(classes = {HospitalController.class, GlobalExceptionHandler.class})
public class HospitalControllerIntegrationApiTest extends AbstractApiTest {

    private static final String HOSPITAL_URL = "/hospital";
    private static final String NOT_FOUND_MESSAGE = "Resource was not found";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private HospitalService hospitalService;

    @MockBean
    private HospitalRepository hospitalRepository;

    @Test
    void whenGetHospitalWithNotExistingId_thenBadRequestIsExpected() throws Exception {
        long nonExistingId = 123L;
        Mockito.when(hospitalService.findById(nonExistingId))
                .thenReturn(null);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(HOSPITAL_URL + "/"
                + nonExistingId))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpServletResponse.SC_NOT_FOUND, response.getStatus());

        String body = response.getContentAsString();
        Assertions.assertTrue(body.contains(NOT_FOUND_MESSAGE));
    }

    @Test
    void whenInvalidHospitalDtoIsProvidedOnPostHospital_thenBadRequestIsExpected() throws Exception {
        String oneCharString = "T";
        HospitalDto testHospitalDto =
                Builder.build(HospitalDto.class)
                        .with(s -> s.setAddress(oneCharString))
                        .get();

        HospitalResponseDto organizationResponseDto = TestDataFactory.getStubHospitalOneResponseDto();

        Mockito.when(hospitalService.save(testHospitalDto))
                .thenReturn(organizationResponseDto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(HOSPITAL_URL + "/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(testHospitalDto)))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpServletResponse.SC_BAD_REQUEST, response.getStatus());
        assertTrue(response.getContentAsString().contains(
                MessageFormat.format("", oneCharString)));
    }

    @Test
    void whenValidHospitalDtoIsProvided_thenOrganizationIsCreated() throws Exception {

        HospitalDto testHospitalDto = TestDataFactory.getStubHospitalOneDto();
        HospitalResponseDto hospitalResponseDto = TestDataFactory.getStubHospitalOneResponseDto();

        Mockito.when(hospitalService.save(testHospitalDto))
                .thenReturn(hospitalResponseDto);

        Mockito.when(hospitalRepository.findById(ArgumentMatchers.any()))
                .thenReturn(ArgumentMatchers.any());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(HOSPITAL_URL + "/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(testHospitalDto)))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpServletResponse.SC_CREATED, response.getStatus());

        String body = response.getContentAsString();
        HospitalResponseDto actualHospitalResponseDto =
                mapper.readValue(body, new TypeReference<HospitalResponseDto>() {});

        Assertions.assertAll( () -> {
                    assertEquals(testHospitalDto.getName(), actualHospitalResponseDto.getName());
                    assertEquals(testHospitalDto.getAddress(), actualHospitalResponseDto.getAddress());
                }
        );
    }
}
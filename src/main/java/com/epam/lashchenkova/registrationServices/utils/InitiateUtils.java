package com.epam.lashchenkova.registrationServices.utils;

import com.epam.lashchenkova.registrationServices.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InitiateUtils implements CommandLineRunner {

    private final PatientService userService;

    @Override
    public void run(String... args) throws Exception {

//        List<UserEntity> userEntities = new ArrayList<>( Arrays.asList(
//                new UserEntity()
//                        .setEmail("dozer0@gmail.com")
//                        .setFirstName("Bill")
//                        .setPassword("password")
//        ));

    }
}

package com.epam.lashchenkova.polyclinic.core;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = { "spring.config.location = classpath:application-test.yml" })
public abstract class AbstractDbTest extends AbstractTest {

}

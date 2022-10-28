package com.epam.lashchenkova.registrationServices;

import com.epam.lashchenkova.registrationServices.config.ProjectConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Applications {

    public static void main(String[] args) {
      SpringApplication app = new SpringApplication(Applications.class);
      ApplicationContext context =
               new AnnotationConfigApplicationContext(ProjectConfig.class);
      app.run(args);
    }
}

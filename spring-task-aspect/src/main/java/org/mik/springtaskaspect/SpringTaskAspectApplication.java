package org.mik.springtaskaspect;


import org.mik.starterhomeworkaspect.config.LoggingAspectAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;



@SpringBootApplication
public class SpringTaskAspectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTaskAspectApplication.class, args);
    }

}

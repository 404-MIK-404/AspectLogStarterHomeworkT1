package org.mik.springtaskaspect;


import org.mik.starterhomeworkaspect.config.LoggingAspectAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


/**
 * Ситуация здесь такова что почему-то он не цепляет зависимости как надо.
 * Пришлось использовать Аннотацию @Import в этом классе + @ComponentScan в авто конфиге
 * Тестил я в модуле spring-task-aspect + отдельно создал Spring проект.
 */
@SpringBootApplication
@Import(LoggingAspectAutoConfiguration.class)
public class SpringTaskAspectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTaskAspectApplication.class, args);
    }

}

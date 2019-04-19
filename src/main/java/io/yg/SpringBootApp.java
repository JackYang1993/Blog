package io.yg;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

/**
 * Created by Administrator on 2017/12/2.
 */

@SpringBootApplication
@EnableScheduling
@ImportResource(locations = {"classpath:spring-quartz.xml"})
public class SpringBootApp {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SpringBootApp.class, args);


    }



}

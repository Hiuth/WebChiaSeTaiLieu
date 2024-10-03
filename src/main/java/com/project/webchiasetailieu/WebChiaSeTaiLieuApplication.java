package com.project.webchiasetailieu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebChiaSeTaiLieuApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebChiaSeTaiLieuApplication.class, args);
    }
}
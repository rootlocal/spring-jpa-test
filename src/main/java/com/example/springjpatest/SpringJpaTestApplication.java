package com.example.springjpatest;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.context.WebServerPortFileWriter;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class SpringJpaTestApplication {
    private static final String[] profiles = new String[]{"dev", "production"};

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(SpringJpaTestApplication.class)
                .listeners(applicationPidFileWriter(), webServerPortFileWriter())
                .web(WebApplicationType.SERVLET)
                .profiles(profiles)
                .run(args);
    }

    @Contract(" -> new")
    public static @NotNull ApplicationListener<?> applicationPidFileWriter() {
        return new ApplicationPidFileWriter("application.pid");
    }

    @Contract(" -> new")
    public static @NotNull ApplicationListener<?> webServerPortFileWriter() {
        return new WebServerPortFileWriter("application.port");
    }

}

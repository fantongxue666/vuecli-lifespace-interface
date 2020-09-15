package com.ftx.saysomthing;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan("com.ftx")
@Import(FdfsClientConfig.class)
public class SaysomthingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaysomthingApplication.class, args);
    }

}

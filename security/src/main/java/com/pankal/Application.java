package com.pankal;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableScheduling
@EnableWebSecurity
@SpringBootApplication(scanBasePackages = "com.pankal")
@RestController
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Application  implements CommandLineRunner {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Starting app");

//        try {
//            DBUtilities.connect(jdbcTemplate.getDataSource());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);


    }
}

package gr.mfa.alfasigma.rest;

import hello.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"hello", "gr.mfa"})
@RestController
public class DemoApplication {

    private final MyService myService;

//    @Autowired
//    JdbcTemplate jdbcTemplate;

    public DemoApplication(MyService myService) {

        this.myService = myService;
    }

    @GetMapping("/")
    public String home() {
//        return "hello";
        return myService.message();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

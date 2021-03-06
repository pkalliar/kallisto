package gr.mfa;


import com.pankal.commons.DBUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.sql.SQLException;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "gr.mfa")
@RestController
public class Application  implements CommandLineRunner {


    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Override
    public void run(String... strings) throws Exception {
        log.info("Starting app");

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

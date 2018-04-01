package com.pankal;

import com.pankal.commons.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import com.pankal.filters.pre.SimpleFilter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

@EnableZuulProxy
@SpringBootApplication()
public class GatewayApplication implements CommandLineRunner {

  @Autowired
  JdbcTemplate jdbcTemplate;

  private final DBService dbService;

  public GatewayApplication(DBService dbService) {
    this.dbService = dbService;
  }

  @Override
  public void run(String... strings) throws Exception {
    System.out.println("Starting app " + dbService.message());

//    try {
//      dbService.connect();
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
  }

  public static void main(String[] args) {

    SpringApplication.run(GatewayApplication.class, args);
  }

  @Bean
  public SimpleFilter simpleFilter() {
    return new SimpleFilter();
  }

}

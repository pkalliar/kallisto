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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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

  @Bean
  public CorsFilter corsFilter() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("OPTIONS");
    config.addAllowedMethod("HEAD");
    config.addAllowedMethod("GET");
    config.addAllowedMethod("PUT");
    config.addAllowedMethod("POST");
    config.addAllowedMethod("DELETE");
    config.addAllowedMethod("PATCH");
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
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

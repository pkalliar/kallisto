package com.pankal;

@Configuration
@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Bean
    public RouteLocator customRouteLocator(ThrottleWebFilterFactory throttle) {
        return Routes.locator()
                .route("test")
                .uri("http://httpbin.org:80")
                .predicate(host("**.abc.org").and(path("/image/png")))
                .addResponseHeader("X-TestHeader", "foobar")
                .and()
                .route("test2")
                .uri("http://httpbin.org:80")
                .predicate(path("/image/webp"))
                .add(addResponseHeader("X-AnotherHeader", "baz"))
                .and()
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
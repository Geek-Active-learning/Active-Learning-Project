package activelearning.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class ActiveLearningProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActiveLearningProjectApplication.class, args);
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//
//                registry.addMapping("/api/**")
//                        .allowedOrigins("http://localhost:4200/")
//                        .allowedMethods("PUT", "DELETE","POST")
//                        .allowCredentials(false).maxAge(3600);
//            }
//        };
//    }
}
package com.example.demo;

import com.example.demo.controllers.UserController;
import com.example.demo.entities.Course;
import com.example.demo.entities.Roles;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
@ComponentScan
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


//	@Bean
//	CommandLineRunner init(UserRepository userRepository) {
//		return args -> {
//			Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
//				User user = new User(1L,
//						name.toLowerCase(),
//						name.toUpperCase(),
//						new Date(), name.toLowerCase(),
//						name.toLowerCase()+"@gmail.com",
//						"0661826809",
//						"1234",
//						Course.builder().name("JAVA").build(),
//						new Date(),
//						Roles.builder().name("ADMIN").build()
//						);
//				userRepository.save(user);
//			});
//			userRepository.findAll().forEach(System.out::println);
//		};
//	}
	

}

package src.main.java.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ActiveLearningProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActiveLearningProjectApplication.class, args);
	}

}

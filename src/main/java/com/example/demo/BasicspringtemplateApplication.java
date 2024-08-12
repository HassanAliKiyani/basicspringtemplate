package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication()
public class BasicspringtemplateApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BasicspringtemplateApplication.class, args);
		System.out.println("Started serving");
		try {
			JdbcTemplate jdbc = context.getBean(JdbcTemplate.class);
			jdbc.execute("SELECT 1");
            System.out.println("Database connection is successful.");
		}catch (Exception e) {
	        System.err.println("Failed to connect to the database.");
	        e.printStackTrace();
	     }
		
	}

}

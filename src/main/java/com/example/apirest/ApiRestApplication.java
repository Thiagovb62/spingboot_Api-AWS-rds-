package com.example.apirest;

import com.example.apirest.Entity.Employee;
import com.example.apirest.Entity.OrderHateoas;
import com.example.apirest.Entity.Person;
import com.example.apirest.Entity.Status;
import com.example.apirest.Repository.EmployeeRepository;
import com.example.apirest.Repository.OrderHateosRepository;
import com.example.apirest.Repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(PersonRepository personRepository) {
		return args -> {
			personRepository.save(new Person("John", "Doe",12));
			personRepository.save(new Person("John", "Doe",12));
		};
	}
	private static final Logger log = LoggerFactory.getLogger(ApiRestApplication.class);

	@Bean
	CommandLineRunner loadData(EmployeeRepository repository,OrderHateosRepository orderRepository) {
		return args -> {
			log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
			log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
			orderRepository.save(new OrderHateoas("MacBook Pro", Status.COMPLETED));
			orderRepository.save(new OrderHateoas("iPhone", Status.IN_PROGRESS));

			orderRepository.findAll().forEach(order -> {
				log.info("Preloaded " + order);
			});
		};
	}

}

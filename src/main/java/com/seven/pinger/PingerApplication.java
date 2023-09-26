package com.seven.pinger;

import com.seven.pinger.enums.Status;
import com.seven.pinger.model.Server;
import com.seven.pinger.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PingerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PingerApplication.class, args);
	}
}

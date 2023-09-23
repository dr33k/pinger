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
	@Bean
	CommandLineRunner runner(ServerRepository repository){
		return args -> {
			repository.save(new Server(null, "192.168.1.160", "Server A", "10GB", "Arch Linux", "http://localhost:8080/servers/server-image/1.png", Status.SERVER_UP));
			repository.save(new Server(null, "192.168.1.161", "Server B", "20GB", "Ubuntu", "http://localhost:8080/servers/server-image/2.png", Status.SERVER_DOWN));
			repository.save(new Server(null, "192.168.1.162", "Server C", "30GB", "Windows", "http://localhost:8080/servers/server-image/3.png", Status.SERVER_UP));
			repository.save(new Server(null, "192.168.1.163", "Server D", "40GB", "Kali", "http://localhost:8080/servers/server-image/4.png", Status.SERVER_DOWN));
		};
	}
}

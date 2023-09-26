package com.seven.pinger.components;

import com.seven.pinger.enums.Status;
import com.seven.pinger.model.Server;
import com.seven.pinger.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CLRunner {
    @Bean
    CommandLineRunner runner(ServerRepository repository){
        return args -> {
            repository.save(new Server(null, "192.168.1.160", "Server A", "10GB", "Arch Linux", "servers/server-image/1.png", Status.SERVER_UP));
            repository.save(new Server(null, "192.168.1.161", "Server B", "20GB", "Ubuntu", "servers/server-image/2.png", Status.SERVER_DOWN));
            repository.save(new Server(null, "192.168.1.162", "Server C", "30GB", "Windows", "servers/server-image/3.png", Status.SERVER_UP));
            repository.save(new Server(null, "192.168.1.163", "Server D", "40GB", "Kali", "servers/server-image/4.png", Status.SERVER_DOWN));
        };
    }
}

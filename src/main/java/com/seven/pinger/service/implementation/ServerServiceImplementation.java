package com.seven.pinger.service.implementation;

import com.seven.pinger.enums.Status;
import com.seven.pinger.model.Server;
import com.seven.pinger.repository.ServerRepository;
import com.seven.pinger.service.ServerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ServerServiceImplementation implements ServerService {
    private final ServerRepository serverRepository;

    @Override
    public Server create(Server server) {
        log.info("Saving new server {}", server.getName());

        server.setImageUrl(getServerImageUrl());
        serverRepository.save(server);
        return server;
    }



    @Override
    public Server ping(String ipAddress){
        log.info("Pinging server ip {}", ipAddress);

        Server server = serverRepository.findByIpAddress(ipAddress).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        try {
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            server.setStatus(inetAddress.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);

            serverRepository.save(server);
            return server;
        }
        catch (IOException ex){ throw new RuntimeException(ex.getMessage());}

    }

    @Override
    public List<Server> list(int limit) {
        log.info("Fetching all servers");

        return serverRepository.findAll(PageRequest.of(0, limit, Sort.by(Sort.Order.desc("updatedAt")))).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id {}", id);

        return serverRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @Override
    public Server update(Server server) {
        log.info("Updating server {}", server.getIpAddress());

        serverRepository.save(server);
        return server;
    }

    @Override
    public Boolean delete(Long id) {
        serverRepository.deleteById(id);
        return Boolean.TRUE;
    }

    private String getServerImageUrl() {
        String[] imageNames = {"1.png", "2.png", "3.png", "4.png"};
        String name = imageNames[(int)(Math.random() * 4)];
        return "servers/server-image/"+name;
    }
}

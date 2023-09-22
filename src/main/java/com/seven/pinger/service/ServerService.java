package com.seven.pinger.service;

import com.seven.pinger.model.Server;

import java.util.List;

public interface ServerService {
    Server create(Server server);
    Server ping(String ipAddress);
    List<Server> list(int limit);
    Server get(Long id);
    Server update(Server server);
    Boolean delete(Long id);
}

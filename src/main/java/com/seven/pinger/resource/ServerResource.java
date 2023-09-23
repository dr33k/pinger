package com.seven.pinger.resource;

import com.seven.pinger.enums.Status;
import com.seven.pinger.model.Response;
import com.seven.pinger.model.Server;
import com.seven.pinger.service.ServerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/servers/")
@RequiredArgsConstructor
public class ServerResource {
    private final ServerService serverService;

    @GetMapping("list")
    public ResponseEntity<Response> getServers() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("servers", serverService.list(30)))
                        .message("Servers retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
    @GetMapping("ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("server", server))
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message(server.getStatus() == Status.SERVER_UP ? "Ping Success" : "Ping Failure")
                        .build()
        );
    }
    @PostMapping("save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
        server = serverService.create(server);
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("server", server))
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Server Created")
                        .build()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
        Server server = serverService.get(id);
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("server", server))
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("Server Retrieved")
                        .build()
        );
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
        Boolean result = serverService.delete(id);
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("result", result))
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("Server Deleted")
                        .build()
        );
    }

    @GetMapping(path="server-image/{name}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("name") String name){
        try(InputStream byteStream = new ClassPathResource("static/servers/"+name, getClass().getClassLoader()).getInputStream()){
            return Optional.of(byteStream).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND)).readAllBytes();
        }
        catch (IOException ex){throw new ResponseStatusException(HttpStatus.NOT_FOUND);}
    }
}
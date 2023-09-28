package com.seven.pinger.model;

import com.seven.pinger.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(name = "server")
@Data
@NoArgsConstructor
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    @NotEmpty(message = "IP Address cannot be empty or null")
    private String ipAddress;
    private String name;
    private String memory;
    private String type;
    private String imageUrl;
    private Status status;
    @CreationTimestamp
    private ZonedDateTime createdAt;
    @UpdateTimestamp
    private ZonedDateTime updatedAt;

    public Server(String ipAddress){this.ipAddress = ipAddress;}
    public Server(Long id, String ipAddress, String name, String memory, String type, String imageUrl, Status status) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.name = name;
        this.memory = memory;
        this.type = type;
        this.imageUrl = imageUrl;
        this.status = status;
    }
}

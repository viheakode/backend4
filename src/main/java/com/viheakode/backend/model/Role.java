package com.viheakode.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private String uuid;
    @Column(nullable = false, unique = true)
    private String roleName;
    private String description;
    private String status;
    private String publisher;
    private Date publishedDate;
    private Date modifiedDate;

    public Role() {
        this.status = "ACTIVE";
        this.publisher = "ADMIN";
        this.publishedDate = new Date();
        this.modifiedDate = new Date();
    }
}

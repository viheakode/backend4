package com.viheakode.backend.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private Long userId;
    private String uuid;
    private String username;
    private String email;
    private String status;
    private String publisher;
    private Date publishedDate;
    private Date modifiedDate;
}

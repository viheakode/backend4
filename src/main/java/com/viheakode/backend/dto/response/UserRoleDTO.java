package com.viheakode.backend.dto.response;

import com.viheakode.backend.model.Role;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserRoleDTO {
    private Long userId;
    private String uuid;
    private String username;
    private String email;
    private List<Role> roles;
    private String status;
    private String publisher;
    private Date publishedDate;
    private Date modifiedDate;
}

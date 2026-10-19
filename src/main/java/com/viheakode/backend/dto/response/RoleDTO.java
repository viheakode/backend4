package com.viheakode.backend.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class RoleDTO {
    private Long roleId;
    private String uuid;
    private String roleName;
    private String description;
    private String status;
    private String publisher;
    private Date publishedDate;
    private Date modifiedDate;
}

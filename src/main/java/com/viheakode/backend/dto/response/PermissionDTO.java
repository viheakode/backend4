package com.viheakode.backend.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class PermissionDTO {
    private Long permissionId;
    private String uuid;
    private String permissionName;
    private String description;
    private String status;
    private String publisher;
    private Date publishedDate;
    private Date modifiedDate;
}

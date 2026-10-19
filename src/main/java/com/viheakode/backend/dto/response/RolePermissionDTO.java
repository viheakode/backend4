package com.viheakode.backend.dto.response;

import com.viheakode.backend.model.Permission;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RolePermissionDTO {
    private Long roleId;
    private String uuid;
    private String roleName;
    private String description;
    private List<Permission> permissions;
    private String status;
    private String publisher;
    private Date publishedDate;
    private Date modifiedDate;
}

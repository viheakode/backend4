package com.viheakode.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PermissionRequest {
    @NotBlank(message = "The permissionName is required")
    private String permissionName;

    public PermissionRequest(String permissionName, String description) {
        this.permissionName = permissionName;
        this.description = description;
    }

    private String description;
}

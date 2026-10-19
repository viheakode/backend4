package com.viheakode.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RolePermissionRequest {
    private Long roleId;
    @NotNull(message = "The permissionId is required")
    private Long permissionId;

    public RolePermissionRequest(Long roleId, Long permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }
}

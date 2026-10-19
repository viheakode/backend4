package com.viheakode.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRoleRequest {
    private Long userId;
    @NotNull(message = "Th roleId is required")
    private Long roleId;

    public UserRoleRequest(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}

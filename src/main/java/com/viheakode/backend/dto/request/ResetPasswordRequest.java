package com.viheakode.backend.dto.request;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String newPassword;
}

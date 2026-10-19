package com.viheakode.backend.service;

import com.viheakode.backend.dto.request.ChangePasswordRequest;
import com.viheakode.backend.dto.request.ForgotPasswordRequest;
import com.viheakode.backend.dto.request.LoginRequest;
import com.viheakode.backend.dto.request.UserRequest;
import com.viheakode.backend.dto.response.UserDTO;

public interface IAuthenticationService {
    String authenticate(LoginRequest request);
    UserDTO register(UserRequest request);
    String forgotPassword(ForgotPasswordRequest request);
}

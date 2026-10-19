package com.viheakode.backend.service.serviceImp;

import com.viheakode.backend.dto.request.ForgotPasswordRequest;
import com.viheakode.backend.dto.request.LoginRequest;
import com.viheakode.backend.dto.request.UserRequest;
import com.viheakode.backend.dto.response.UserDTO;
import com.viheakode.backend.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements IAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final MoyJwtService moyJwtService;
    @Override
    public String authenticate(LoginRequest request) {


        String username = request.getUsername().trim().toUpperCase(Locale.ROOT);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username,
                request.getPassword())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return moyJwtService.generateToken(userDetails);
    }

    @Override
    public UserDTO register(UserRequest request) {
        return null;
    }

    @Override
    public String forgotPassword(ForgotPasswordRequest request) {
        return "";
    }
}

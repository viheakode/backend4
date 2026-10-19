package com.viheakode.backend.viheakode_core_api;

import com.viheakode.backend.dto.request.LoginRequest;
import com.viheakode.backend.service.serviceImp.AuthenticationServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class RAuthenticationController {

    private final AuthenticationServiceImp authenticationServiceImp;

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody LoginRequest request){
        String token = authenticationServiceImp.authenticate(request);
        Map<String, Object> body = new HashMap<>();
        body.put("token", token);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}

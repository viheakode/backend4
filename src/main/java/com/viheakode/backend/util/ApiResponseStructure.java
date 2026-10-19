package com.viheakode.backend.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ApiResponseStructure {
    public static ResponseEntity<Object> success(String msg, Object data, HttpStatus status){
        Map<String, Object> body = new HashMap<>();
        body.put("message", msg);
        body.put("data", data);
        body.put("status", status.value());
        return new ResponseEntity<>(body, status);
    }

    public static ResponseEntity<Object> error(String msg, String error, String path, HttpStatus status){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", msg);
        body.put("path", path);
        return new ResponseEntity<>(body, status);
    }
}

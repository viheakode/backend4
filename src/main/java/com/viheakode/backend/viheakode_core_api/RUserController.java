package com.viheakode.backend.viheakode_core_api;

import com.viheakode.backend.dto.request.ChangePasswordRequest;
import com.viheakode.backend.dto.request.ResetPasswordRequest;
import com.viheakode.backend.dto.request.UserRequest;
import com.viheakode.backend.dto.request.UserRoleRequest;
import com.viheakode.backend.dto.response.UserDTO;
import com.viheakode.backend.dto.response.UserRoleDTO;
import com.viheakode.backend.service.serviceImp.UserServiceImp;
import com.viheakode.backend.util.ApiResponseStructure;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class RUserController {

    private final UserServiceImp userServiceImp;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody UserRequest request){
        UserDTO dto = userServiceImp.create(request);
        return ApiResponseStructure.success("Created", dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<UserDTO> dtoList = userServiceImp.getAll();
        return ApiResponseStructure.success("Ok", dtoList, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getById(@PathVariable Long userId){
        UserDTO dto = userServiceImp.getById(userId);
        return ApiResponseStructure.success("Success", dto, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> update(@PathVariable Long userId, @Valid @RequestBody UserRequest request){
        UserDTO dto = userServiceImp.update(userId, request);
        return ApiResponseStructure.success("Updated", dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> delete(@PathVariable Long userId){
        UserDTO dto = userServiceImp.delete(userId);
        return ApiResponseStructure.success("Deleted", dto, HttpStatus.OK);
    }

    @PostMapping("/{userId}/change_password")
    public ResponseEntity<Object> changePassword(@PathVariable Long userId, @Valid @RequestBody ChangePasswordRequest request){
        UserDTO dto = userServiceImp.changePassword(userId, request);
        return ApiResponseStructure.success("Password changed", dto, HttpStatus.OK);
    }

    @PostMapping("/{userId}/reset_password")
    public ResponseEntity<Object> resetPassword(@PathVariable Long userId, @Valid @RequestBody ResetPasswordRequest request){
        UserDTO dto = userServiceImp.resetPassword(userId, request);
        return ApiResponseStructure.success("Password reset successful", request.getNewPassword(), HttpStatus.OK);
    }

    @PostMapping("/{userId}/roles")
    public ResponseEntity<Object> assignRoleToUser(@PathVariable Long userId, @Valid @RequestBody UserRoleRequest request){
        UserRoleDTO dto = userServiceImp.assignRoleToUser(userId, request);
        return ApiResponseStructure.success("Created", dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}/roles")
    public ResponseEntity<Object> removeRoleFromUser(@PathVariable Long userId, @Valid @RequestBody UserRoleRequest request){
        UserRoleDTO dto = userServiceImp.removeRoleFromUser(userId, request);
        return ApiResponseStructure.success("Ok", dto, HttpStatus.OK);
    }
}

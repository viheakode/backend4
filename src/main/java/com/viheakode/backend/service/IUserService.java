package com.viheakode.backend.service;

import com.viheakode.backend.dto.request.ChangePasswordRequest;
import com.viheakode.backend.dto.request.ResetPasswordRequest;
import com.viheakode.backend.dto.request.UserRequest;
import com.viheakode.backend.dto.request.UserRoleRequest;
import com.viheakode.backend.dto.response.UserDTO;
import com.viheakode.backend.dto.response.UserRoleDTO;

import java.util.List;

public interface IUserService {
    UserDTO create(UserRequest request);
    List<UserDTO> getAll();
    UserDTO getById(Long userId);
    UserDTO update(Long userId, UserRequest request);
    UserDTO delete(Long userId);
    UserDTO changePassword(Long userId, ChangePasswordRequest request);
    UserDTO resetPassword(Long userId, ResetPasswordRequest request);
    UserRoleDTO assignRoleToUser(Long userId, UserRoleRequest request);
    UserRoleDTO removeRoleFromUser(Long userId, UserRoleRequest request);

}

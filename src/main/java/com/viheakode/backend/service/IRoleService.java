package com.viheakode.backend.service;

import com.viheakode.backend.dto.request.RolePermissionRequest;
import com.viheakode.backend.dto.request.RoleRequest;
import com.viheakode.backend.dto.response.RoleDTO;
import com.viheakode.backend.dto.response.RolePermissionDTO;

import java.util.List;

public interface IRoleService {
    RoleDTO create(RoleRequest request);
    List<RoleDTO> getAll();
    RoleDTO getById(Long roleId);
    RoleDTO update(Long roleId, RoleRequest request);
    RoleDTO delete(Long roleId);
    RolePermissionDTO addPermissionToRole(Long roleId, RolePermissionRequest request);
    RolePermissionDTO removePermissionFromRole(Long roleId, RolePermissionRequest request);
}

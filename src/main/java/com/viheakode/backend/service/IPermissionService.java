package com.viheakode.backend.service;

import com.viheakode.backend.dto.request.PermissionRequest;
import com.viheakode.backend.dto.response.PermissionDTO;

import java.util.List;

public interface IPermissionService {
    PermissionDTO create(PermissionRequest request);
    List<PermissionDTO> getAll();
    PermissionDTO getById(Long permissionId);
    PermissionDTO update(Long permissionId, PermissionRequest request);
    PermissionDTO delete(Long permissionId);
}

package com.viheakode.backend.service.serviceImp;

import com.viheakode.backend.dto.request.RolePermissionRequest;
import com.viheakode.backend.dto.response.RolePermissionDTO;
import com.viheakode.backend.mapper.RolePermissionMapper;
import com.viheakode.backend.model.RolePermission;
import com.viheakode.backend.repository.RolePermissionRepository;
import com.viheakode.backend.util.SecurityUtilHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RolePermissionServiceImp {

    private final RolePermissionRepository rolePermissionRepository;
    private final RolePermissionMapper rolePermissionMapper;

    public void create(Long roleId, RolePermissionRequest request){
        RolePermission rolePermission = new RolePermission();
        rolePermission.setUuid(UUID.randomUUID().toString());
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(request.getPermissionId());
        rolePermission.setPublisher(SecurityUtilHelper.getPublisher());
        rolePermissionRepository.save(rolePermission);
    }
}

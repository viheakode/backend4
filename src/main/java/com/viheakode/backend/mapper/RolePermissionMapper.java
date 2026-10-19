package com.viheakode.backend.mapper;

import com.viheakode.backend.dto.response.RolePermissionDTO;
import com.viheakode.backend.model.Permission;
import com.viheakode.backend.model.Role;
import com.viheakode.backend.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RolePermissionMapper {

    private final PermissionRepository permissionRepository;

    public RolePermissionDTO toDTO(Role role){
        RolePermissionDTO dto = new RolePermissionDTO();
        dto.setRoleId(role.getRoleId());
        dto.setUuid(role.getUuid());
        dto.setRoleName(role.getRoleName());
        dto.setDescription(role.getDescription());
        List<Permission> permissionList = permissionRepository.findAllByRoleId(role.getRoleId());
        dto.setPermissions(permissionList);
        dto.setStatus(role.getStatus());
        dto.setPublisher(role.getPublisher());
        dto.setPublishedDate(role.getPublishedDate());
        dto.setModifiedDate(role.getModifiedDate());
        return dto;
    }
}

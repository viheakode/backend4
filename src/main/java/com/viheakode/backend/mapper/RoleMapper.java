package com.viheakode.backend.mapper;

import com.viheakode.backend.dto.response.RoleDTO;
import com.viheakode.backend.model.Role;

public class RoleMapper {
    public static RoleDTO toDTO(Role role){
        RoleDTO dto = new RoleDTO();
        dto.setRoleId(role.getRoleId());
        dto.setUuid(role.getUuid());
        dto.setRoleName(role.getRoleName());
        dto.setDescription(role.getDescription());
        dto.setStatus(role.getStatus());
        dto.setPublisher(role.getPublisher());
        dto.setPublishedDate(role.getPublishedDate());
        dto.setModifiedDate(role.getModifiedDate());
        return dto;
    }
}

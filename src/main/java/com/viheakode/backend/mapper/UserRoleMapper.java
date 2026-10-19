package com.viheakode.backend.mapper;

import com.viheakode.backend.dto.response.UserRoleDTO;
import com.viheakode.backend.model.Role;
import com.viheakode.backend.model.User;
import com.viheakode.backend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserRoleMapper {

    private final RoleRepository repository;

    public UserRoleDTO toDTO(User user){
        UserRoleDTO dto = new UserRoleDTO();
        dto.setUserId(user.getUserId());
        dto.setUuid(user.getUuid());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());

        List<Role> roleList = repository.findAllByUserId(user.getUserId());
        dto.setRoles(roleList);

        dto.setStatus(user.getStatus());
        dto.setPublisher(user.getPublisher());
        dto.setPublishedDate(user.getPublishedDate());
        dto.setModifiedDate(user.getModifiedDate());
        return dto;
    }
}

package com.viheakode.backend.mapper;

import com.viheakode.backend.dto.response.UserDTO;
import com.viheakode.backend.model.User;

public class UserMapper {
    public static UserDTO toDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUuid(user.getUuid());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setStatus(user.getStatus());
        dto.setPublisher(user.getPublisher());
        dto.setPublishedDate(user.getPublishedDate());
        dto.setModifiedDate(user.getModifiedDate());
        return dto;
    }
}

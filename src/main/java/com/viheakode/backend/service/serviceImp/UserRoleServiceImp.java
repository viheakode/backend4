package com.viheakode.backend.service.serviceImp;

import com.viheakode.backend.dto.request.UserRoleRequest;
import com.viheakode.backend.model.UserRole;
import com.viheakode.backend.repository.UserRoleRepository;
import com.viheakode.backend.util.SecurityUtilHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImp {

    private final UserRoleRepository userRoleRepository;

    public void create(Long userId, UserRoleRequest request){
        UserRole userRole = new UserRole();
        userRole.setUuid(UUID.randomUUID().toString());
        userRole.setUserId(userId);
        userRole.setRoleId(request.getRoleId());
        userRole.setPublisher(SecurityUtilHelper.getPublisher());
        userRoleRepository.save(userRole);
    }
}

package com.viheakode.backend.service.serviceImp;

import com.viheakode.backend.dto.request.PermissionRequest;
import com.viheakode.backend.dto.response.PermissionDTO;
import com.viheakode.backend.exection.DuplicateResourceException;
import com.viheakode.backend.exection.ResourceNotFoundException;
import com.viheakode.backend.mapper.PermissionMapper;
import com.viheakode.backend.model.Permission;
import com.viheakode.backend.repository.PermissionRepository;
import com.viheakode.backend.service.IPermissionService;
import com.viheakode.backend.util.SecurityUtilHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PermissionServiceImp implements IPermissionService {

    private final PermissionRepository permissionRepository;

    @PreAuthorize("hasAuthority('CREATE_PERMISSION')")
    @Override
    public PermissionDTO create(PermissionRequest request) {

        String permissionName = request.getPermissionName().trim().toUpperCase(Locale.ROOT);
        if (permissionRepository.existsByPermissionName(permissionName)){
            throw new DuplicateResourceException("Permission already exists");
        }

        Permission permission = new Permission();
        permission.setUuid(UUID.randomUUID().toString());

        permission.setPermissionName(permissionName);
        permission.setDescription(request.getDescription());
        permission.setPublisher(SecurityUtilHelper.getPublisher());
        permissionRepository.save(permission);
        return PermissionMapper.toDTO(permission);
    }

    @PreAuthorize("hasAuthority('READ_PERMISSION')")
    @Override
    public List<PermissionDTO> getAll() {
        List<Permission> permissionList = permissionRepository.findAll();
        return permissionList.stream().map(PermissionMapper::toDTO).toList();
    }

    @PreAuthorize("hasAuthority('READ_PERMISSION')")
    @Override
    public PermissionDTO getById(Long permissionId) {
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));
        return PermissionMapper.toDTO(permission);
    }

    @PreAuthorize("hasAuthority('UPDATE_PERMISSION')")
    @Override
    public PermissionDTO update(Long permissionId, PermissionRequest request) {
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));

        String permissionName = request.getPermissionName().trim().toUpperCase(Locale.ROOT);
        if (permissionRepository.existsByPermissionNameAndPermissionIdNot(permissionName, permissionId)){
            throw new DuplicateResourceException("Permission already exists");
        }
        permission.setPermissionName(permissionName);
        permission.setDescription(request.getDescription());
        permission.setModifiedDate(new Date());
        permissionRepository.save(permission);
        return PermissionMapper.toDTO(permission);
    }

    @PreAuthorize("hasAuthority('DELETE_PERMISSION')")
    @Override
    public PermissionDTO delete(Long permissionId) {
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));
        permissionRepository.delete(permission);
        return PermissionMapper.toDTO(permission);
    }
}

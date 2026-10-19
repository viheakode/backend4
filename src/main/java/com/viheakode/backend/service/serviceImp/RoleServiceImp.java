package com.viheakode.backend.service.serviceImp;

import com.viheakode.backend.dto.request.RolePermissionRequest;
import com.viheakode.backend.dto.request.RoleRequest;
import com.viheakode.backend.dto.response.RoleDTO;
import com.viheakode.backend.dto.response.RolePermissionDTO;
import com.viheakode.backend.exection.DuplicateResourceException;
import com.viheakode.backend.exection.ResourceNotFoundException;
import com.viheakode.backend.mapper.RoleMapper;
import com.viheakode.backend.mapper.RolePermissionMapper;
import com.viheakode.backend.model.Permission;
import com.viheakode.backend.model.Role;
import com.viheakode.backend.model.RolePermission;
import com.viheakode.backend.repository.PermissionRepository;
import com.viheakode.backend.repository.RolePermissionRepository;
import com.viheakode.backend.repository.RoleRepository;
import com.viheakode.backend.service.IRoleService;
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
public class RoleServiceImp implements IRoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final RolePermissionMapper rolePermissionMapper;

    @PreAuthorize("hasAuthority('CREATE_ROLE')")
    @Override
    public RoleDTO create(RoleRequest request) {

        String roleName = request.getRoleName().trim().toUpperCase(Locale.ROOT);
        if (roleRepository.existsByRoleName(roleName)){
            throw new DuplicateResourceException("Role already exists");
        }
        Role role = new Role();
        role.setUuid(UUID.randomUUID().toString());
        role.setRoleName(roleName);
        role.setDescription(request.getDescription());
        role.setPublisher(SecurityUtilHelper.getPublisher());
        roleRepository.save(role);
        return RoleMapper.toDTO(role);
    }

    @PreAuthorize("hasAuthority('READ_ROLE')")
    @Override
    public List<RoleDTO> getAll() {
        List<Role> roleList = roleRepository.findAll();
        return roleList.stream().map(RoleMapper::toDTO).toList();
    }

    @PreAuthorize("hasAuthority('READ_ROLE')")
    @Override
    public RoleDTO getById(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        return RoleMapper.toDTO(role);
    }

    @PreAuthorize("hasAuthority('UPDATE_ROLE')")
    @Override
    public RoleDTO update(Long roleId, RoleRequest request) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        String roleName = request.getRoleName().trim().toUpperCase(Locale.ROOT);
        if (roleRepository.existsByRoleNameAndRoleIdNot(roleName, roleId)){
            throw new DuplicateResourceException("Role already exists");
        }
        role.setRoleName(roleName);
        role.setDescription(request.getDescription());
        role.setModifiedDate(new Date());
        roleRepository.save(role);
        return RoleMapper.toDTO(role);
    }

    @PreAuthorize("hasAuthority('DELETE_ROLE')")
    @Override
    public RoleDTO delete(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        roleRepository.delete(role);
        return RoleMapper.toDTO(role);
    }

    @PreAuthorize("hasAuthority('UPDATE_ROLE')")
    @Override
    public RolePermissionDTO addPermissionToRole(Long roleId, RolePermissionRequest request) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        Permission permission = permissionRepository.findById(request.getPermissionId())
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));

        if (rolePermissionRepository.existsByRoleIdAndPermissionId(roleId, request.getPermissionId())){
            throw new DuplicateResourceException("Permission already assigned to role");
        }

        RolePermission rolePermission = new RolePermission();
        rolePermission.setUuid(UUID.randomUUID().toString());
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(request.getPermissionId());
        rolePermission.setPublisher(SecurityUtilHelper.getPublisher());
        rolePermissionRepository.save(rolePermission);
        return rolePermissionMapper.toDTO(role);
    }

    @PreAuthorize("hasAuthority('UPDATE_ROLE')")
    @Override
    public RolePermissionDTO removePermissionFromRole(Long roleId, RolePermissionRequest request) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        Permission permission = permissionRepository.findById(request.getPermissionId())
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));

        RolePermission rolePermission = rolePermissionRepository.findByRoleIdAndPermissionId(roleId, request.getPermissionId());
        if (rolePermission == null){
            throw new ResourceNotFoundException("Permission is not assigned to role");
        }
        rolePermissionRepository.delete(rolePermission);
        return rolePermissionMapper.toDTO(role);
    }
}

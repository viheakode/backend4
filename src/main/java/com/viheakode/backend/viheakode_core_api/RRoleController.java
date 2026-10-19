package com.viheakode.backend.viheakode_core_api;

import com.viheakode.backend.dto.request.RolePermissionRequest;
import com.viheakode.backend.dto.request.RoleRequest;
import com.viheakode.backend.dto.response.RoleDTO;
import com.viheakode.backend.dto.response.RolePermissionDTO;
import com.viheakode.backend.service.serviceImp.RoleServiceImp;
import com.viheakode.backend.util.ApiResponseStructure;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RRoleController {

    private final RoleServiceImp roleServiceImp;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody RoleRequest request){
        RoleDTO dto = roleServiceImp.create(request);
        return ApiResponseStructure.success("Created", dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<RoleDTO> dtoList = roleServiceImp.getAll();
        return ApiResponseStructure.success("Success", dtoList, HttpStatus.OK);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<Object> getById(@PathVariable Long roleId){
        RoleDTO dto = roleServiceImp.getById(roleId);
        return ApiResponseStructure.success("Success", dto, HttpStatus.OK);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<Object> update(@PathVariable Long roleId, @Valid @RequestBody RoleRequest request){
        RoleDTO dto = roleServiceImp.update(roleId, request);
        return ApiResponseStructure.success("Updated", dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Object> delete(@PathVariable Long roleId){
        RoleDTO dto = roleServiceImp.delete(roleId);
        return ApiResponseStructure.success("Deleted", dto, HttpStatus.OK);
    }

    @PostMapping("/{roleId}/permissions")
    public ResponseEntity<Object> addPermissionToRole(@PathVariable Long roleId, @Valid @RequestBody RolePermissionRequest request){
        RolePermissionDTO dto = roleServiceImp.addPermissionToRole(roleId, request);
        return ApiResponseStructure.success("Created", dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{roleId}/permissions")
    public ResponseEntity<Object> removePermissionToRole(@PathVariable Long roleId, @Valid @RequestBody RolePermissionRequest request){
        RolePermissionDTO dto = roleServiceImp.removePermissionFromRole(roleId, request);
        return ApiResponseStructure.success("Removed", dto, HttpStatus.OK);
    }
}

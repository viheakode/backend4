package com.viheakode.backend.viheakode_core_api;

import com.viheakode.backend.dto.request.PermissionRequest;
import com.viheakode.backend.dto.response.PermissionDTO;
import com.viheakode.backend.service.serviceImp.PermissionServiceImp;
import com.viheakode.backend.util.ApiResponseStructure;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permissions")
@RequiredArgsConstructor
public class RPermissionController {

    private final PermissionServiceImp permissionServiceImp;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody PermissionRequest request){
        PermissionDTO dto = permissionServiceImp.create(request);
        return ApiResponseStructure.success("Created", dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<PermissionDTO> dtoList = permissionServiceImp.getAll();
        return ApiResponseStructure.success("Success", dtoList, HttpStatus.OK);
    }

    @GetMapping("/{permissionId}")
    public ResponseEntity<Object> getById(@PathVariable Long permissionId){
        PermissionDTO dto = permissionServiceImp.getById(permissionId);
        return ApiResponseStructure.success("Success", dto, HttpStatus.OK);
    }

    @PutMapping("/{permissionId}")
    public ResponseEntity<Object> update(@PathVariable Long permissionId, @Valid @RequestBody PermissionRequest request){
        PermissionDTO dto = permissionServiceImp.update(permissionId, request);
        return ApiResponseStructure.success("Updated", dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{permissionId}")
    public ResponseEntity<Object> delete(@PathVariable Long permissionId, @RequestBody PermissionRequest request){
        PermissionDTO dto = permissionServiceImp.delete(permissionId);
        return ApiResponseStructure.success("Deleted", dto, HttpStatus.OK);
    }


}

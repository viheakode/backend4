package com.viheakode.backend.viheakode_core_api;

import com.viheakode.backend.dto.request.*;
import com.viheakode.backend.service.serviceImp.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RHomeController {

    private final PermissionServiceImp permissionServiceImp;
    private final RoleServiceImp roleServiceImp;
    private final UserServiceImp userServiceImp;
    private final RolePermissionServiceImp rolePermissionServiceImp;
    private final UserRoleServiceImp userRoleServiceImp;

    @GetMapping("/start")
    public String start(){
        addPermission();
        addRole();
        addUser();
        addRolePermission();
        addUserRole();
        return "App start successfully";
    }

    @GetMapping("/health")
    public String health(){
        return "App is running";
    }

    private void addPermission(){
        for (PermissionRequest request : permissionRequestList){
            permissionServiceImp.create(request);
        }
    }
    private final List<PermissionRequest> permissionRequestList = new ArrayList<>(List.of(
            new PermissionRequest("CREATE_PERMISSION", "CREATE_PERMISSION"),
            new PermissionRequest("READ_PERMISSION", "READ_PERMISSION"),
            new PermissionRequest("UPDATE_PERMISSION", "UPDATE_PERMISSION"),
            new PermissionRequest("DELETE_PERMISSION", "DELETE_PERMISSION"),

            new PermissionRequest("CREATE_ROLE", "CREATE_ROLE"),
            new PermissionRequest("READ_ROLE", "READ_ROLE"),
            new PermissionRequest("UPDATE_ROLE", "UPDATE_ROLE"),
            new PermissionRequest("DELETE_ROLE", "DELETE_ROLE"),

            new PermissionRequest("CREATE_USER", "CREATE_USER"),
            new PermissionRequest("READ_USER", "READ_USER"),
            new PermissionRequest("UPDATE_USER", "UPDATE_USER"),
            new PermissionRequest("DELETE_USER", "DELETE_USER")

    ));

    private void addRole(){
        for(RoleRequest request : roleRequestList){
            roleServiceImp.create(request);
        }
    }
    private final List<RoleRequest> roleRequestList = new ArrayList<>(List.of(
            new RoleRequest("ADMIN", "ADMIN"),
            new RoleRequest("USER", "USER"),
            new RoleRequest("TEST", "TEST")
    ));

    private void addUser(){
        for (UserRequest request : userRequestList){
            userServiceImp.create(request);
        }
    }
    private final List<UserRequest> userRequestList = new ArrayList<>(List.of(
            new UserRequest("VIHEAKODE", "viheakode787@gmail.com", "viheakode")
    ));

    private void addRolePermission(){
        for (RolePermissionRequest request : rolePermissionRequestList){
            rolePermissionServiceImp.create(request.getRoleId(), request);
        }
    }
    private final List<RolePermissionRequest> rolePermissionRequestList = new ArrayList<>(List.of(
            new RolePermissionRequest(1L, 1L),
            new RolePermissionRequest(1L, 2L),
            new RolePermissionRequest(1L, 3L),
            new RolePermissionRequest(1L, 4L),

            new RolePermissionRequest(1L, 5L),
            new RolePermissionRequest(1L, 6L),
            new RolePermissionRequest(1L, 7L),
            new RolePermissionRequest(1L, 8L),

            new RolePermissionRequest(1L, 9L),
            new RolePermissionRequest(1L, 10L),
            new RolePermissionRequest(1L, 11L),
            new RolePermissionRequest(1L, 12L)

    ));
    private void addUserRole(){
        for (UserRoleRequest request : userRoleRequestList){
            userRoleServiceImp.create(request.getUserId(), request);
        }
    }
    private final List<UserRoleRequest> userRoleRequestList = new ArrayList<>(List.of(
            new UserRoleRequest(1L, 1L)
    ));
}

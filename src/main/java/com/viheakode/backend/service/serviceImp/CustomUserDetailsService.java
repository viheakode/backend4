package com.viheakode.backend.service.serviceImp;

import com.viheakode.backend.exection.ResourceNotFoundException;
import com.viheakode.backend.model.Permission;
import com.viheakode.backend.model.Role;
import com.viheakode.backend.model.User;
import com.viheakode.backend.repository.PermissionRepository;
import com.viheakode.backend.repository.RoleRepository;
import com.viheakode.backend.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        Set<GrantedAuthority> authorities = new HashSet<>();

        List<Role> roles = roleRepository.findAllByUserId(user.getUserId());
        for (Role role : roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            List<Permission> permissions = permissionRepository.findAllByRoleId(role.getRoleId());
            for (Permission permission : permissions){
                authorities.add(new SimpleGrantedAuthority(permission.getPermissionName()));
            }

        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}

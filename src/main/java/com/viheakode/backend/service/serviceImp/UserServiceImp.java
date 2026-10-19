package com.viheakode.backend.service.serviceImp;

import com.viheakode.backend.dto.request.ChangePasswordRequest;
import com.viheakode.backend.dto.request.ResetPasswordRequest;
import com.viheakode.backend.dto.request.UserRequest;
import com.viheakode.backend.dto.request.UserRoleRequest;
import com.viheakode.backend.dto.response.UserDTO;
import com.viheakode.backend.dto.response.UserRoleDTO;
import com.viheakode.backend.exection.DuplicateResourceException;
import com.viheakode.backend.exection.ResourceNotFoundException;
import com.viheakode.backend.mapper.UserMapper;
import com.viheakode.backend.mapper.UserRoleMapper;
import com.viheakode.backend.model.Role;
import com.viheakode.backend.model.User;
import com.viheakode.backend.model.UserRole;
import com.viheakode.backend.repository.RoleRepository;
import com.viheakode.backend.repository.UserRepository;
import com.viheakode.backend.repository.UserRoleRepository;
import com.viheakode.backend.service.IUserService;
import com.viheakode.backend.util.SecurityUtilHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleMapper userRoleMapper;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @PreAuthorize("hasAuthority('CREATE_USER')")
    @Override
    public UserDTO create(UserRequest request) {
        String username = request.getUsername().trim().toUpperCase(Locale.ROOT);
        String email = request.getEmail().trim().toLowerCase(Locale.ROOT);
        if (userRepository.existsByUsername(username)){
            throw new DuplicateResourceException("Username already taken");
        }
        if (userRepository.existsByEmail(email)){
            throw new DuplicateResourceException("Email already taken");
        }

        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPublisher(SecurityUtilHelper.getPublisher());
        userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    @PreAuthorize("hasAuthority('READ_USER')")
    @Override
    public List<UserDTO> getAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(UserMapper::toDTO).toList();
    }

    @PreAuthorize("hasAuthority('READ_USER')")
    @Override
    public UserDTO getById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return UserMapper.toDTO(user);
    }

    @PreAuthorize("hasAuthority('UPDATE_USER')")
    @Override
    public UserDTO update(Long userId, UserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String username = request.getUsername().trim().toUpperCase(Locale.ROOT);
        String email = request.getEmail().trim().toLowerCase(Locale.ROOT);
        if (userRepository.existsByUsernameAndUserIdNot(username, userId)){
            throw new DuplicateResourceException("Username already taken");
        }

        if (userRepository.existsByEmailAndUserIdNot(email, userId)){
            throw new DuplicateResourceException("Email already taken");
        }

        user.setUsername(username);
        user.setEmail(email);
        user.setModifiedDate(new Date());
        userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    @PreAuthorize("hasAuthority('DELETE_USER')")
    @Override
    public UserDTO delete(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO changePassword(Long userId, ChangePasswordRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())){
            throw new RuntimeException("Old password does not math");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setModifiedDate(new Date());
        userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    @PreAuthorize("hasAuthority('UPDATE_USER')")
    @Override
    public UserDTO resetPassword(Long userId, ResetPasswordRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setModifiedDate(new Date());
        userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    @PreAuthorize("hasAuthority('UPDATE_USER')")
    @Override
    public UserRoleDTO assignRoleToUser(Long userId, UserRoleRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        if (userRoleRepository.existsByUserIdAndRoleId(userId, request.getRoleId())){
            throw new DuplicateResourceException("Role already assigned to user");
        }
        UserRole userRole = new UserRole();
        userRole.setUuid(UUID.randomUUID().toString());
        userRole.setUserId(userId);
        userRole.setRoleId(request.getRoleId());
        userRole.setPublisher(SecurityUtilHelper.getPublisher());
        userRoleRepository.save(userRole);
        return userRoleMapper.toDTO(user);
    }

    @PreAuthorize("hasAuthority('UPDATE_USER')")
    @Override
    public UserRoleDTO removeRoleFromUser(Long userId, UserRoleRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        UserRole userRole = userRoleRepository.findByUserIdAndRoleId(userId, request.getRoleId());
        if (userRole == null){
            throw new ResourceNotFoundException("Role is not assigned to user");
        }
        userRoleRepository.delete(userRole);
        return userRoleMapper.toDTO(user);
    }
}

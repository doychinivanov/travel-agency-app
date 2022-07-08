package com.travelapp.service.impl;

import com.travelapp.models.RoleEntity;
import com.travelapp.models.UserEntity;
import com.travelapp.models.enums.UserRoleEnum;
import com.travelapp.repositories.RolesRepository;
import com.travelapp.repositories.UserRepository;
import com.travelapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String DUMMY_PASS = "DUMMY_PASS";

//    What is that
//    @Value("${app.default.admin.password}") String adminPass
    @Autowired
    public RoleServiceImpl(UserRepository userRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void initializeRoles() {
        if (userRepository.count() == 0 && rolesRepository.count() == 0) {
            RoleEntity adminRole = new RoleEntity();
            adminRole.setUserRole(UserRoleEnum.ADMIN);

            RoleEntity moderatorRole = new RoleEntity();
            moderatorRole.setUserRole(UserRoleEnum.MODERATOR);

            RoleEntity standardRole = new RoleEntity();
            standardRole.setUserRole(UserRoleEnum.STANDARD);

            adminRole = this.rolesRepository.save(adminRole);
            moderatorRole = this.rolesRepository.save(moderatorRole);

            initAdmin(List.of(adminRole, moderatorRole));
            initModerator(List.of(moderatorRole));
            initUser(List.of(standardRole));
        }
    }

    private void initAdmin(List<RoleEntity> roles) {
        UserEntity admin = new UserEntity();
        admin.setRoles(new HashSet<>(roles));
        admin.setFullName("Admin Adminov");
        admin.setEmail("admin@example.com");
        admin.setPassword(passwordEncoder.encode(DUMMY_PASS));

        userRepository.save(admin);
    }

    private void initModerator(List<RoleEntity> roles) {
        UserEntity moderator = new UserEntity();
        moderator.setRoles(new HashSet<>(roles));
        moderator.setFullName("Moderator Moderatorov");
        moderator.setEmail("moderator@example.com");
        moderator.setPassword(passwordEncoder.encode(DUMMY_PASS));

        userRepository.save(moderator);
    }

    private void initUser(List<RoleEntity> roles) {
        UserEntity user = new UserEntity();
        user.setRoles(new HashSet<>(roles));
        user.setFullName("User Userov");
        user.setEmail("user@example.com");
        user.setPassword(passwordEncoder.encode(DUMMY_PASS));

        userRepository.save(user);
    }
}

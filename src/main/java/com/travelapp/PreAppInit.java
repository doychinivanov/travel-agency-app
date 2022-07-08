package com.travelapp;

import com.travelapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PreAppInit implements CommandLineRunner {
    private final RoleService roleService;

    @Autowired
    public PreAppInit(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        roleService.initializeRoles();
    }
}

package com.travelapp.models.dto;

import com.travelapp.models.RoleEntity;

import java.util.HashSet;
import java.util.Set;

public class UserTableDTO {

    private long id;

    private String fullName;

    private String email;

    private Set<RoleEntity> roles = new HashSet<>();

    public UserTableDTO() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}

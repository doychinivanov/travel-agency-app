package com.travelapp.models.dto;

import com.travelapp.models.RoleEntity;
import com.travelapp.models.enums.UserRoleEnum;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserTableDTO {

    private long id;

    private String fullName;

    private String email;

    private Set<RoleEntity> roles = new HashSet<>();

    private UserRoleEnum primaryRole;

    public UserTableDTO() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTableDTO that = (UserTableDTO) o;
        return id == that.id && fullName.equals(that.fullName) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, email);
    }

    public void setPrimaryRole(UserRoleEnum primaryRole) {
        this.primaryRole = primaryRole;
    }

    public UserRoleEnum getPrimaryRole() {
        return primaryRole;
    }

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

        roles.forEach(roleEntity -> {
            if (roleEntity.getUserRole().equals(UserRoleEnum.ADMIN)) {
                setPrimaryRole(UserRoleEnum.ADMIN);
            } else if (roleEntity.getUserRole().equals(UserRoleEnum.MODERATOR)) {
                setPrimaryRole(UserRoleEnum.MODERATOR);
            } else {
                setPrimaryRole(UserRoleEnum.STANDARD);
            }
        });
    }
}

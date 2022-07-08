package com.travelapp.models;

import com.travelapp.models.enums.UserRoleEnum;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "roles")
@Entity
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "role")
    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRole;

    public RoleEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return Objects.equals(id, that.id) && userRole == that.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userRole);
    }
}

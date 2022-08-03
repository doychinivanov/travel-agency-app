package com.travelapp.repositories;

import com.travelapp.models.RoleEntity;
import com.travelapp.models.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RoleEntity, Long> {
    @Query("SELECT r FROM RoleEntity r" +
            " WHERE r.userRole = :role")
    RoleEntity getUserRole(@Param("role") UserRoleEnum userRole);
}

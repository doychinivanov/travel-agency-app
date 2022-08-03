package com.travelapp.repositories;

import com.travelapp.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT u FROM UserEntity u" +
            " JOIN u.roles AS r" +
            " ORDER BY r.userRole")
    List<UserEntity> getAllUsersForAdminDashboard();
}

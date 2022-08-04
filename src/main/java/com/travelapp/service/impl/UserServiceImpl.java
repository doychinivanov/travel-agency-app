package com.travelapp.service.impl;

import com.travelapp.models.RoleEntity;
import com.travelapp.models.UserEntity;
import com.travelapp.models.dto.TripCardDTO;
import com.travelapp.models.dto.UserTableDTO;
import com.travelapp.models.enums.UserRoleEnum;
import com.travelapp.repositories.RolesRepository;
import com.travelapp.repositories.UserRepository;
import com.travelapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private ModelMapper modelMapper;

    private RolesRepository rolesRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public List<TripCardDTO> getUserBookings(long userId) {
        UserEntity currUser = this.userRepository.findById(userId).get();

        return currUser
                .getBookings()
                .stream()
                .map(booking -> this.modelMapper.map(booking.getTrip(), TripCardDTO.class)).toList();
    }

    @Override
    public boolean userHasBookedTrip(long userId, long tripId) throws Exception {
        Optional<UserEntity> user = this.userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new Exception("No such user");
        }

        return user
                .get()
                .getBookings()
                .stream()
                .filter(booking -> booking.getTrip().getId() == tripId)
                .toList()
                .size() > 0;
    }

    @Override
    public void addNewlySpentMoneyToTotalAmount(String userEmail, BigDecimal newAmount) {
        UserEntity user = this.userRepository.findByEmail(userEmail).get();

        BigDecimal totalAmountOfMoneySpent = user.getTotalAmountOfMoneySpent() != null ? user.getTotalAmountOfMoneySpent() : BigDecimal.ZERO;
        user.setTotalAmountOfMoneySpent(totalAmountOfMoneySpent.add(newAmount));
        this.userRepository.save(user);
    }

    @Override
    public BigDecimal getTotalAmountUserSpentOnThePlatform(long userid) {
        UserEntity user = this.userRepository.findById(userid).get();
        return user.getTotalAmountOfMoneySpent();
    }

    @Override
    public Set<UserTableDTO> getAllUsers() {
        List<UserEntity> allUsersForAdminTable = this.userRepository.getAllUsersForAdminDashboard();

        return Arrays.stream(this.modelMapper.map(allUsersForAdminTable, UserTableDTO[].class)).collect(Collectors.toSet());
    }

    @Override
    public String changeUserRole(long userId, UserRoleEnum userRole) throws Exception {
        Optional<UserEntity> optionalUser = this.userRepository.findById(userId);
        RoleEntity role = this.rolesRepository.getUserRole(userRole);

        if (optionalUser.isEmpty()) {
            throw new Exception("No such user");
        }

        UserEntity targetUser = optionalUser.get();
        if (targetUser.getRoles().contains(UserRoleEnum.ADMIN)) {
            throw new Exception("The target user has admin role. You can't change their roles,");
        }

        targetUser.setRoles(new HashSet<>(List.of(role)));
        this.userRepository.save(targetUser);
        return targetUser.getFullName();
    }
}

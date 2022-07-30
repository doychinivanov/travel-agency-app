package com.travelapp.web;

import com.travelapp.models.AuthUser;
import com.travelapp.models.dto.TripCardDTO;
import com.travelapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/bookings")
    public String getUserBookings(@AuthenticationPrincipal AuthUser currentAuthUser, Model model) {
        List<TripCardDTO> userBookings = this.userService.getUserBookings(currentAuthUser.getId());
        userBookings.forEach(System.out::println);
        model.addAttribute("userName", currentAuthUser.getFullName());
        model.addAttribute("userBookings", userBookings);
        return "my-trips";
    }
}

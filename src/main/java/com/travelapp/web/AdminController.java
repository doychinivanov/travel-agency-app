package com.travelapp.web;

import com.travelapp.models.AuthUser;
import com.travelapp.models.dto.UserTableDTO;
import com.travelapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String getDashboard(@AuthenticationPrincipal AuthUser currentAuthUser, Model model) {
        Set<UserTableDTO> users = this.userService.getAllUsers();

        model.addAttribute("users", users);
        model.addAttribute("userName", currentAuthUser.getFullName());
        return "admin-dashboard";
    }
}

package com.travelapp.web;

import com.travelapp.models.AuthUser;
import com.travelapp.models.dto.UserTableDTO;
import com.travelapp.models.enums.UserRoleEnum;
import com.travelapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/make/moderator/{id}")
    public String promoteToModerator(
            @AuthenticationPrincipal AuthUser currentAuthUser,
            @PathVariable long id,
            RedirectAttributes redirectAttributes
    ) {

        if (currentAuthUser.getId() == id) {
            redirectAttributes.addFlashAttribute("error", "You can't change your own role!");
            return "redirect:/admin/dashboard";
        }

        try {
            String targetUserFullName = this.userService.changeUserRole(id, UserRoleEnum.MODERATOR);
            redirectAttributes.addFlashAttribute("successMsg", targetUserFullName + "'s role was successfully changed to MODERATOR");
            return "redirect:/admin/dashboard";
        } catch (Exception err) {
            redirectAttributes.addFlashAttribute("error", err.getMessage());
            return "redirect:/admin/dashboard";
        }
    }

    @GetMapping("/make/standard/{id}")
    public String makeStandard(
            @AuthenticationPrincipal AuthUser currentAuthUser,
            @PathVariable long id,
            RedirectAttributes redirectAttributes
    ) {

        if (currentAuthUser.getId() == id) {
            redirectAttributes.addFlashAttribute("error", "You can't change your own role!");
            return "redirect:/admin/dashboard";
        }

        try {
            String targetUserFullName = this.userService.changeUserRole(id, UserRoleEnum.STANDARD);
            redirectAttributes.addFlashAttribute("successMsg", targetUserFullName + "'s role was successfully changed to STANDARD");
            return "redirect:/admin/dashboard";
        } catch (Exception err) {
            redirectAttributes.addFlashAttribute("error", err.getMessage());
            return "redirect:/admin/dashboard";
        }
    }
}

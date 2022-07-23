package com.travelapp.web;

import com.travelapp.models.dto.RegisterDTO;
import com.travelapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("registrationDTO")
    public RegisterDTO initRegistrationDto() {
        return new RegisterDTO();
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }


    @GetMapping("/signup")
    public String getSignup() {
        return "signup";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String userName,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, userName);
        redirectAttributes.addFlashAttribute("bad_credentials", true);

        return "redirect:/auth/login";
    }

    @PostMapping("/signup")
    public String registerUser(@Valid RegisterDTO registrationDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registrationDTO", registrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationDTO", bindingResult);

            if (bindingResult.hasGlobalErrors()) {
                redirectAttributes.addFlashAttribute("confirmPassNotMatching", true);
            }

            return "redirect:/auth/signup";
        }

        try {
            this.authService.signup(registrationDTO);
            return "redirect:/";
        } catch (Exception err) {
            redirectAttributes.addFlashAttribute("registrationDTO", registrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationDTO", bindingResult);
            redirectAttributes.addFlashAttribute("emailTaken", err.getMessage());
            return "redirect:/auth/signup";
        }

    }
}

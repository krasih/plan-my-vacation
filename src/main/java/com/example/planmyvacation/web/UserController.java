package com.example.planmyvacation.web;

import com.example.planmyvacation.model.dto.UserRegisterDTO;
import com.example.planmyvacation.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerData")
    public UserRegisterDTO createEmptyRegisterDTO() {
        return new UserRegisterDTO();
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/register")
    public String viewRegister() {

        return "register";
    }

    @PostMapping("/register")
    public String doRegister(
            @Valid UserRegisterDTO userDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors() /*|| !userService.registerUser(userDTO)*/) {
            redirectAttributes.addFlashAttribute("registerData", userDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerData", bindingResult);
            return "redirect:/users/register";
        }

        boolean success = userService.registerUser(userDTO);

        if (!success) return "redirect:/users/register";

        return "redirect:/users/login";
    }
}

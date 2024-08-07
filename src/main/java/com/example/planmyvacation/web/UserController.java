package com.example.planmyvacation.web;

import com.example.planmyvacation.model.dto.UserRegisterDTO;
import com.example.planmyvacation.model.dto.UserSummaryDTO;
import com.example.planmyvacation.model.enums.UserRole;
import com.example.planmyvacation.service.UserService;
import com.example.planmyvacation.validation.UserRegistrationValidation;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRegistrationValidation validation;
    private final EntityManager entityManager;

    public UserController(UserService userService, UserRegistrationValidation validation, EntityManager entityManager) {
        this.userService = userService;
        this.validation = validation;
        this.entityManager = entityManager;
    }

    @ModelAttribute("registerData")
    public UserRegisterDTO createEmptyRegisterDTO() {
        return new UserRegisterDTO();
    }

    @ModelAttribute("usersData")
    public List<UserSummaryDTO> allCountriesAndCities() {

        return userService.getAll();
    }

    @ModelAttribute("userData")
    public UserSummaryDTO createEmptyUserSummaryDTO() {
        return new UserSummaryDTO();
    }

    @ModelAttribute("allUserRolesData")
    public List<String> allUserRoles() {

        return Arrays.stream(UserRole.values())
                .map(Enum::name)
                .toList();
    }


    @GetMapping("")
    public String getAllUsers(Model model) {

        model.addAttribute("usersData", userService.getAll());

        return "users";
    }

    @GetMapping(value = "", headers = "hx-request=true" )
    public String getAllUsersHTMX(Model model) {

        model.addAttribute("usersData", userService.getAll());

        return "fragments/tables :: users";
    }

    @GetMapping("/{id}")
    public String getUserById(Model model, @PathVariable Long id) {

        List<String> roles = Arrays.stream(UserRole.values())
                .map(Enum::name)
                .toList();

        model.addAttribute("allUserRolesData", roles);
        model.addAttribute("userData", userService.getUserById(id));

        return "fragments/tables :: userFragment";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {

        userService.delete(id);

        return "fragments/modals :: empty_card";
    }

    @PutMapping("/{id}")
    public String updateUser(
            Model model,
            @PathVariable Long id,
            @Valid UserSummaryDTO userSummaryDTO,
            BindingResult result,
            RedirectAttributes rAtt
    ) {

        if(result.hasErrors()){
            rAtt.addFlashAttribute("userData", userSummaryDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.userData", result);

            return "fragments/tables :: users";
        }

        userService.update(userSummaryDTO);

        List<UserSummaryDTO> all = userService.getAll();
        model.addAttribute("usersData", all);

        return "fragments/tables :: users";
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

        validation.confirmPasswordMatchPassword(userDTO, bindingResult);
        validation.usernameExists(userDTO, bindingResult);
        validation.emailExists(userDTO, bindingResult);

        if ( bindingResult.hasErrors() ) {
            redirectAttributes.addFlashAttribute("registerData", userDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);
            return "redirect:/users/register";
        }

        boolean success = userService.registerUser(userDTO);

        if (!success) return "redirect:/users/register";

        return "redirect:/users/login";
    }
}

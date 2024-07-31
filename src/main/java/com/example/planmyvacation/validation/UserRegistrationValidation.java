package com.example.planmyvacation.validation;

import com.example.planmyvacation.model.dto.UserRegisterDTO;
import com.example.planmyvacation.repository.UserRepository;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
public final class UserRegistrationValidation {

    private final MessageSource messageSource;
    private final UserRepository userRepository;

    private UserRegistrationValidation(MessageSource messageSource, UserRepository userRepository) {
        this.messageSource = messageSource;
        this.userRepository = userRepository;
    }

    public BindingResult confirmPasswordMatchPassword(UserRegisterDTO userDTO, BindingResult bindingResult) {

        String password = userDTO.getPassword();
        String confirmPassword = userDTO.getConfirmPassword();

        if ((password != null && confirmPassword != null) && !password.equals(confirmPassword)) {

            FieldError error = getFieldError("msg.confirm.password.not.match", "confirmPassword");
            bindingResult.addError(error);
        }

        return bindingResult;
    }

    public BindingResult usernameExists(UserRegisterDTO userDTO, BindingResult bindingResult) {

        String username = userDTO.getUsername();

        if (username != null && userRepository.findByUsername(username).isPresent()) {

            FieldError error = getFieldError("msg.username.exists", "username", username);
            bindingResult.addError(error);
        }

        return bindingResult;
    }

    public BindingResult emailExists(UserRegisterDTO userDTO, BindingResult bindingResult) {

        String email = userDTO.getEmail();

        if (email != null && userRepository.findByEmail(email).isPresent()) {

            FieldError error = getFieldError("msg.email.exists", "email", email);
            bindingResult.addError(error);
        }

        return bindingResult;
    }

    private FieldError getFieldError(String messageCode, String fieldName) {

        String errorMessageLocalized = messageSource.getMessage(messageCode, null, LocaleContextHolder.getLocale());

        return new FieldError("userRegisterDTO", fieldName, errorMessageLocalized);
    }

    private FieldError getFieldError(String messageCode, String fieldName, String rejectedValue) {

        String errorMessageLocalized = messageSource.getMessage(messageCode, null, LocaleContextHolder.getLocale());

        return new FieldError(
                "userRegisterDTO",
                fieldName,
                rejectedValue,
                false,
                null,
                null,
                errorMessageLocalized);
    }
}

package com.example.planmyvacation.model.convert;

import com.example.planmyvacation.model.dto.UserRegisterDTO;
import com.example.planmyvacation.model.dto.UserSummaryDTO;
import com.example.planmyvacation.model.entity.Role;
import com.example.planmyvacation.model.entity.User;
import com.example.planmyvacation.model.enums.UserRole;
import com.example.planmyvacation.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConverter {

    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTOConverter(
            ModelMapper modelMapper,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {

        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserSummaryDTO mapUserToUserSummaryDTO(User user) {

        return modelMapper.map(user, UserSummaryDTO.class)
                .setRole(user.getRole().getRole().name());
    }

    public User mapUserSummaryDTOToUser(UserSummaryDTO userSummaryDTO) {

        return modelMapper.map(userSummaryDTO, User.class);
    }

    public User mapUserRegisterDTOToUser(UserRegisterDTO userRegisterDTO) {

        return modelMapper.map(userRegisterDTO, User.class)
                .setRole(roleRepository.getByRole(UserRole.USER))
                .setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
    }

}

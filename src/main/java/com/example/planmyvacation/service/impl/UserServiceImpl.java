package com.example.planmyvacation.service.impl;

import com.example.planmyvacation.model.dto.UserRegisterDTO;
import com.example.planmyvacation.model.entity.User;
import com.example.planmyvacation.model.enums.UserRole;
import com.example.planmyvacation.repository.RoleRepository;
import com.example.planmyvacation.repository.UserRepository;
import com.example.planmyvacation.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
//    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(
            ModelMapper modelMapper,
//            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
//        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean registerUser(UserRegisterDTO userDTO) {

        // Check if password and confirmPassword matches
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) return false;

        // Check if user with such username or email exists
        Optional<User> existingUser = userRepository.findByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail());
        if (existingUser.isPresent()) return false;

        User user = mapDtoToEntity(userDTO);

        userRepository.save(user);
        return true;
    }

    private User mapDtoToEntity(UserRegisterDTO dto) {
        User user = modelMapper.map(dto, User.class);
        user.setRole(roleRepository.getByRole(UserRole.USER));
//        user.setPassword(passwordEncoder.encode(dto.password()));

        return user;
    }
}

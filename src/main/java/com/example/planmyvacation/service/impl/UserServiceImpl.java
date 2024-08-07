package com.example.planmyvacation.service.impl;

import com.example.planmyvacation.model.convert.UserDTOConverter;
import com.example.planmyvacation.model.dto.UserRegisterDTO;
import com.example.planmyvacation.model.dto.UserSummaryDTO;
import com.example.planmyvacation.model.entity.User;
import com.example.planmyvacation.repository.UserRepository;
import com.example.planmyvacation.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDTOConverter converter;

    public UserServiceImpl( UserRepository userRepository, UserDTOConverter converter ) {

        this.userRepository = userRepository;
        this.converter = converter;
    }


    @Override
    public boolean registerUser(UserRegisterDTO userDTO) {

        User user = converter.mapUserRegisterDTOToUser(userDTO);

        userRepository.save(user);
        return true;
    }

    @Override
    public List<UserSummaryDTO> getAll() {

        return userRepository.findAll().stream()
                .map(converter::mapUserToUserSummaryDTO)
                .toList();
    }

    @Override
    public UserSummaryDTO getUserById(Long id) {

        return userRepository.findById(id)
                .map(converter::mapUserToUserSummaryDTO)
                .orElse(null);
    }

    @Override
    public void delete(Long id) {

        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void update(UserSummaryDTO userSummaryDTO) {

        userRepository.updateUserById(
                userSummaryDTO.getId(),
                userSummaryDTO.getUsername(),
                userSummaryDTO.getEmail(),
                userSummaryDTO.getRole()
        );
    }
}

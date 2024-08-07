package com.example.planmyvacation.service;

import com.example.planmyvacation.model.dto.UserRegisterDTO;
import com.example.planmyvacation.model.dto.UserSummaryDTO;

import java.util.List;

public interface UserService {

    boolean registerUser(UserRegisterDTO user);

    List<UserSummaryDTO> getAll();

    UserSummaryDTO getUserById(Long id);

    void delete(Long id);

    void update(UserSummaryDTO userSummaryDTO);

}

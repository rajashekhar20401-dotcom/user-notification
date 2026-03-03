package org.mail.userservice.service;

import org.mail.userservice.dto.UserRequestDTO;
import org.mail.userservice.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO dto);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(Long id);

    UserResponseDTO updateUser(Long id, UserRequestDTO request);

    String deleteUser(Long id);
}

package org.mail.userservice.controller;

import org.mail.userservice.dto.UserRequestDTO;
import org.mail.userservice.dto.UserResponseDTO;
import org.mail.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserRequestDTO dto){
        return service.createUser(dto);
    }
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return ResponseEntity.ok(service.getAllUsers());
    }
}

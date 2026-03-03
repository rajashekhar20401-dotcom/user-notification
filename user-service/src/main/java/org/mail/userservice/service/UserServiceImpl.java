package org.mail.userservice.service;

import lombok.RequiredArgsConstructor;
//import org.mail.userservice.dto.NotificationRequestDTO;
import org.mail.userservice.dto.UserRequestDTO;
import org.mail.userservice.dto.UserResponseDTO;
import org.mail.userservice.entity.User;
import org.mail.userservice.event.UserCreatedEvent;
import org.mail.userservice.repository.UserRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository repo;
//    private final RestTemplate rest;
//    private final WebClient.Builder web;
    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;


    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {

        User user=new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setMobileNumber(dto.getMobileNumber());
        user.setEmail(dto.getEmail());
        user.setCity(dto.getCity());
        user.setState(dto.getState());
        user.setPincode(dto.getPincode());

        User saved =  repo.save(user);
//
//        NotificationRequestDTO notificationDTO = new NotificationRequestDTO();
//        notificationDTO.setFirstName(saved.getFirstName());
//        notificationDTO.setEmail(saved.getEmail());
//
//        rest.postForObject(
//                "http://notification-service/send",
//                notificationDTO,
//                String.class);
//
//        String response=web.build().post().
//                uri("http://notification-service:8082/send").
//                bodyValue(notificationDTO).
//                retrieve().
//                bodyToMono(String.class).
//                block();
//        System.out.println(response);
        // Create Kafka Event
        UserCreatedEvent event = new UserCreatedEvent(
                saved.getId(),
                saved.getFirstName(),
                saved.getEmail()
        );

        // Publish to Kafka
        kafkaTemplate.send("user-created-topic", event);

        System.out.println("UserCreatedEvent published to Kafka");
        return UserResponseDTO.builder()
                .id(saved.getId())
                .firstName(saved.getFirstName())
                .email(saved.getEmail())
                .message("User Created Successfully")
                .build();


    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> user=repo.findAll();

        return user.stream().
                map(users -> UserResponseDTO.
                        builder().
                        id(users.getId()).
                        firstName(users.getFirstName()).
                        email(users.getEmail()).
                        build()).toList();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO request) {

        User user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(request.getFirstName());
        user.setEmail(request.getEmail());
        User updatedUser = repo.save(user);

        return UserResponseDTO.builder()
                .id(updatedUser.getId())
                .firstName(updatedUser.getFirstName())
                .email(updatedUser.getEmail())
                .message("User updated successfully")
                .build();
    }

    @Override
    public String deleteUser(Long id) {

        User user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        repo.delete(user);

        return "User deleted successfully";
    }
}

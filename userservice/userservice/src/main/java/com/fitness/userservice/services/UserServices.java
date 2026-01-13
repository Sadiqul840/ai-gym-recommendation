//package com.fitness.userservice.services;
//
//import com.fitness.userservice.Repository.UserRepository;
//import com.fitness.userservice.dto.RegisterRequest;
//import com.fitness.userservice.dto.UserResponse;
//import com.fitness.userservice.models.User;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//@Slf4j
//public class UserServices {
//
//    private final UserRepository repository;
//
//
//
//    public UserResponse register(RegisterRequest request) {
//        // Email check
//        if (repository.existsByEmail(request.getEmail())) {
//            User existingUser = repository.findByEmail(request.getEmail());
//            UserResponse userResponse = new UserResponse();
//            userResponse.setId(existingUser.getId());
//            userResponse.setKeycloakId(existingUser.getKeycloakId());
//            userResponse.setPassword(existingUser.getPassword());
//            userResponse.setEmail(existingUser.getEmail());
//            userResponse.setFirstName(existingUser.getFirstName());
//            userResponse.setLastName(existingUser.getLastName());
//            userResponse.setCreatedAt(existingUser.getCreatedAt());
//            userResponse.setUpdatedAt(existingUser.getUpdatedAt());
//            return userResponse;
//        }
//
//        // User create
//        User user = new User();
//        user.setEmail(request.getEmail());
//        user.setPassword(request.getPassword());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setPassword(request.getPassword()); // TODO: encrypt karna better hai
//
//        // Save user
//        User savedUser = repository.save(user);
//
//        // Convert to Response
//        UserResponse userResponse = new UserResponse();
//        userResponse.setKeycloakId(savedUser.getKeycloakId());
//        userResponse.setId(savedUser.getId());
//        userResponse.setPassword(savedUser.getPassword());
//        userResponse.setEmail(savedUser.getEmail());
//        userResponse.setFirstName(savedUser.getFirstName());
//        userResponse.setLastName(savedUser.getLastName());
//        userResponse.setCreatedAt(savedUser.getCreatedAt());
//        userResponse.setUpdatedAt(savedUser.getUpdatedAt());
//
//        return userResponse;
//    }
//
//    public UserResponse getUserDetails(String id) {
//        User user = repository.findById(id)
//                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
//
//        UserResponse userResponse = new UserResponse();
//        userResponse.setId(user.getId());
//        userResponse.setPassword(user.getPassword());
//        userResponse.setEmail(user.getEmail());
//        userResponse.setFirstName(user.getFirstName());
//        userResponse.setLastName(user.getLastName());
//        userResponse.setCreatedAt(user.getCreatedAt());
//        userResponse.setUpdatedAt(user.getUpdatedAt());
//
//        return userResponse;
//    }
//
//    public Boolean existByUserId(String userId) {
//        log.info("Calling User Service for{}",userId);
//        return repository.existsByKeycloakId(userId);
//    }
//
//
//
//}




package com.fitness.userservice.services;

import com.fitness.userservice.Repository.UserRepository;
import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.models.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserServices {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserResponse register(RegisterRequest request) {

        // 🧩 Step 1: Check by keycloakId (if provided)
        if (request.getKeycloakId() != null && repository.existsByKeycloakId(request.getKeycloakId())) {
            log.info("User already exists with keycloakId: {}", request.getKeycloakId());
            return mapToResponse(repository.findByEmail(request.getEmail()));
        }

        // 🧩 Step 2: Check by email (avoid duplicates)
        if (repository.existsByEmail(request.getEmail())) {
            log.info("User already exists with email: {}", request.getEmail());
            return mapToResponse(repository.findByEmail(request.getEmail()));
        }

        // 🧩 Step 3: Create new user entry
        User user = new User();
        user.setKeycloakId(request.getKeycloakId());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        // Encrypt password if available, else set dummy
        String encodedPassword = (request.getPassword() != null)
                ? passwordEncoder.encode(request.getPassword())
                : passwordEncoder.encode("default@123");
        user.setPassword(encodedPassword);

        User savedUser = repository.save(user);
        log.info("New user created: {} ({})", savedUser.getEmail(), savedUser.getKeycloakId());
        return mapToResponse(savedUser);
    }

    public UserResponse getUserDetails(String id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
        return mapToResponse(user);
    }

    public Boolean existByUserId(String userId) {
        log.info("Checking user existence for Keycloak ID: {}", userId);
        return repository.existsByKeycloakId(userId);
    }

    // Utility
    private UserResponse mapToResponse(User user) {
        UserResponse res = new UserResponse();
        res.setId(user.getId());
        res.setKeycloakId(user.getKeycloakId());
        res.setEmail(user.getEmail());
        res.setFirstName(user.getFirstName());
        res.setLastName(user.getLastName());
        res.setCreatedAt(user.getCreatedAt());
        res.setUpdatedAt(user.getUpdatedAt());
        return res;
    }
}


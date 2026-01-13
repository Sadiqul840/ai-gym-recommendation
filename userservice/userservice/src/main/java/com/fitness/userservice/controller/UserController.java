//package com.fitness.userservice.controller;
//
//import com.fitness.userservice.dto.RegisterRequest;
//import com.fitness.userservice.dto.UserResponse;
//import com.fitness.userservice.services.UserServices;
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/users")
//@AllArgsConstructor
//public class UserController {
//
//    @Autowired
//    private UserServices userServices;
//
//    @PostMapping("/register")
//    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
//        return ResponseEntity.ok(userServices.register(request));
//    }
//
////    @GetMapping("/{userId}")
////    public ResponseEntity<UserResponse> getUserDetails(@PathVariable("userId") String userId) {
////        return ResponseEntity.ok(UserServices.getUserDetails(userId));
////    }
//
//    @GetMapping("/{userId}")
//    public ResponseEntity<UserResponse> getUserDetails(@PathVariable("userId") String id) {
//        return ResponseEntity.ok(userServices.getUserDetails(id));
//    }
//
//    @GetMapping("/{userId}/validate")
//    public ResponseEntity<Boolean> validateUser(@PathVariable String userId) {
//        return ResponseEntity.ok(userServices.existByUserId(userId));
//    }
//
//
//
//
//
//}



package com.fitness.userservice.controller;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.services.UserServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userServices.register(request));
    }

    // Existing GET endpoints
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserDetails(@PathVariable("userId") String id) {
        return ResponseEntity.ok(userServices.getUserDetails(id));
    }

    @GetMapping("/{userId}/validate")
    public ResponseEntity<Boolean> validateUser(@PathVariable String userId) {
        return ResponseEntity.ok(userServices.existByUserId(userId));
    }

    // NEW: Automatic user creation for ActivityService
    @PostMapping
    public ResponseEntity<UserResponse> createUserWithId(@RequestBody Map<String, String> body) {
        String userId = body.get("userId");

        RegisterRequest request = new RegisterRequest();
        request.setEmail("auto" + userId + "@example.com"); // Dummy email
        request.setFirstName("Auto");
        request.setLastName("User");
        request.setPassword("default123"); // Default password

        return ResponseEntity.ok(userServices.register(request));
    }
}


package com.fitness.activityservice.service;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.reactive.function.client.WebClientResponseException;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class UserValidationService {

//    private  final WebClient userServiceWebClient;

//    public boolean validateUser( String userId) {
//        log.info("Calling User Service for{}",userId);
//      try{
//          return userServiceWebClient.get()
//                  .uri("/api/users/{userId}/validate", userId)
//                  .retrieve()
//                  .bodyToMono(Boolean.class)
//                  .block();
//      }
//      catch ( WebClientResponseException e ) {
//          e.printStackTrace();
//      }
//      return false;
//    }
//
//    public boolean validateUser(String userId) {
//        try {
//            return userServiceWebClient.get()
//                    .uri("/api/users/{userId}/validate", userId)
//                    .retrieve()
//                    .bodyToMono(Boolean.class)
//                    .block();
//        } catch (WebClientResponseException e) {
//            log.error("User service returned error for {}: {}", userId, e.getStatusCode());
//            throw new RuntimeException("User Service unavailable");
//        } catch (Exception e) {
//            log.error("Unknown error validating user {}: {}", userId, e.getMessage());
//            throw new RuntimeException("User validation failed");
//        }
//    }
//
//
//}

//
//public class UserValidationService {
//    private final WebClient userServiceWebClient;
//
//    public boolean validateUser(String userId) {
//        log.info("Calling User Validation API for userId: {}", userId);
//        try{
//            return userServiceWebClient.get()
//                    .uri("/api/users/{userId}/validate", userId)
//                    .retrieve()
//                    .bodyToMono(Boolean.class)
//                    .block();
//        } catch (WebClientResponseException e) {
//            if (e.getStatusCode() == HttpStatus.NOT_FOUND)
//                throw new RuntimeException("User Not Found: " + userId);
//            else if (e.getStatusCode() == HttpStatus.BAD_REQUEST)
//                throw new RuntimeException("Invalid Request: " + userId);
//        }
//        return false;
//    }
//}


//package com.fitness.activityservice.service;

//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.reactive.function.client.WebClientResponseException;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class UserValidationService {
//
//    private final WebClient userServiceWebClient;
//
//    public boolean validateUser(String userId) {
//        log.info("Calling User Service for userId: {}", userId);
//        try {
//            Boolean exists = userServiceWebClient.get()
//                    .uri("/api/users/{userId}/validate", userId)
//                    .retrieve()
//                    .bodyToMono(Boolean.class)
//                    .block();
//
//            return exists != null && exists;
//        } catch (WebClientResponseException e) {
//            if (e.getStatusCode() == HttpStatus.NOT_FOUND)
//                throw new RuntimeException("User Not Found: " + userId);
//            else if (e.getStatusCode() == HttpStatus.BAD_REQUEST)
//                throw new RuntimeException("Invalid Request: " + userId);
//            else
//                throw new RuntimeException("User Service error: " + e.getStatusCode());
//        } catch (Exception e) {
//            log.error("Unknown error validating user {}: {}", userId, e.getMessage());
//            throw new RuntimeException("User validation failed for " + userId);
//        }
//    }
//}



//package com.fitness.activityservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserValidationService {

    private final WebClient userServiceWebClient;

    public boolean validateOrCreateUser(String userId) {
        log.info("Validating user: {}", userId);

        try {
            Boolean exists = userServiceWebClient.get()
                    .uri("/api/users/{userId}/validate", userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            if (exists != null && exists) {
                return true;
            }

            log.warn("User not found in UserService. Skipping validation for now.");
            return true;

        } catch (Exception e) {
            log.warn("UserService unavailable. Proceeding without validation.");
            return true;
        }
    }
}

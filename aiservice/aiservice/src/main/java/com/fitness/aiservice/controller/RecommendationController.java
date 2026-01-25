// package com.fitness.aiservice.controller;

// import com.fitness.aiservice.model.Recommendation;
// import com.fitness.aiservice.service.RecommendationService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import java.util.List;

// @RestController
// @RequestMapping("/api/recommendation")
// @RequiredArgsConstructor
// public class RecommendationController {

//     private final RecommendationService recommendationService;

//     @GetMapping("/user/{userId}")
//     ResponseEntity<List<Recommendation>> getRecommendations(@PathVariable String userId) {
//         return ResponseEntity.ok(recommendationService.getUserRecommendation(userId));
//     }

//     @GetMapping("/activity/{activityId}")
//     public ResponseEntity<Recommendation> getActivityRecommendation(@PathVariable String activityId) {

//         return ResponseEntity.ok(recommendationService.getActivityRecommendation(activityId));
//     }


// }


package com.fitness.aiservice.controller;

import com.fitness.aiservice.model.Activity;
import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.RecommendationRepository;
import com.fitness.aiservice.service.ActivityAiService;
import com.fitness.aiservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;
    private final ActivityAiService activityAiService;
    private final RecommendationRepository recommendationRepository;

    // ✅ GET recommendations by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getRecommendations(@PathVariable String userId) {
        return ResponseEntity.ok(recommendationService.getUserRecommendation(userId));
    }

    // ✅ GET recommendation by activity
    @GetMapping("/activity/{activityId}")
    public ResponseEntity<Recommendation> getActivityRecommendation(@PathVariable String activityId) {
        return ResponseEntity.ok(recommendationService.getActivityRecommendation(activityId));
    }

    // ✅ NEW: GENERATE recommendation using Gemini (NO KAFKA)
    @PostMapping("/generate")
    public ResponseEntity<Recommendation> generateRecommendation(
            @RequestBody Activity activity
    ) {
        Recommendation recommendation =
                activityAiService.generateRecommendation(activity);

        return ResponseEntity.ok(
                recommendationRepository.save(recommendation)
        );
    }
}

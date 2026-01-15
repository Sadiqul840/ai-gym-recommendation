package com.fitness.activityservice.controller;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    // ✅ CREATE / TRACK ACTIVITY
    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(
            @RequestBody ActivityRequest request,
            @RequestHeader("X-User-ID") String userId
    ) {
        request.setUserId(userId);
        return ResponseEntity.ok(activityService.trackActivity(request));
    }

    // ✅ GET ALL ACTIVITIES FOR USER
    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getUserActivities(
            @RequestHeader("X-User-ID") String userId
    ) {
        return ResponseEntity.ok(activityService.getUserActivities(userId));
    }

    // ✅ GET SINGLE ACTIVITY
    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> getActivity(
            @PathVariable String activityId
    ) {
        return ResponseEntity.ok(activityService.getActivityById(activityId));
    }
}

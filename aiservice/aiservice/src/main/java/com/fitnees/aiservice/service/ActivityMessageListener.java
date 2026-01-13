package com.fitnees.aiservice.service;

import com.fitnees.aiservice.model.Activity;
import com.fitnees.aiservice.model.Recommendation;
import com.fitnees.aiservice.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    private final ActivityAiService activityAiService;
    private final RecommendationRepository recommendationRepository;
    @KafkaListener(topics ="${kafka.topic.name}", groupId = "activity-processor-group")
    public void process(Activity activity) {
        log.info("received Activity for processing: {} ", activity.getUserId());
        activityAiService.generateRecommendation(activity);
        Recommendation recommendation = activityAiService.generateRecommendation(activity);
        recommendationRepository.save(recommendation);
    }
}

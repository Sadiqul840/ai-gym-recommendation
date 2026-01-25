// package com.fitness.aiservice.service;

// import com.fitness.aiservice.model.Activity;
// import com.fitness.aiservice.model.Recommendation;
// import com.fitness.aiservice.repository.RecommendationRepository;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.kafka.annotation.KafkaListener;
// import org.springframework.stereotype.Service;

// @Service
// @Slf4j
// @RequiredArgsConstructor
// public class ActivityMessageListener {

//     private final ActivityAiService activityAiService;
//     private final RecommendationRepository recommendationRepository;
//     @KafkaListener(topics ="${kafka.topic.name}", groupId = "activity-processor-group")
//     public void process(Activity activity) {
//         log.info("received Activity for processing: {} ", activity.getUserId());
//         activityAiService.generateRecommendation(activity);
//         Recommendation recommendation = activityAiService.generateRecommendation(activity);
//         recommendationRepository.save(recommendation);
//     }
// }


package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Activity;
import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(
        name = "kafka.enabled",
        havingValue = "true",
        matchIfMissing = false
)
public class ActivityMessageListener {

    private final ActivityAiService activityAiService;
    private final RecommendationRepository recommendationRepository;

    @KafkaListener(
            topics = "${kafka.topic.name}",
            groupId = "activity-processor-group"
    )
    public void process(Activity activity) {

        log.info("📩 Activity received from Kafka: {}", activity.getId());

        Recommendation recommendation =
                activityAiService.generateRecommendation(activity);

        recommendationRepository.save(recommendation);

        log.info("✅ Recommendation saved for activityId={}", activity.getId());
    }
}


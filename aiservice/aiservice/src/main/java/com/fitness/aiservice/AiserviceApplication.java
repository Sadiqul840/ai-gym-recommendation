// package com.fitness.aiservice;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.data.mongodb.config.EnableMongoAuditing;

// @SpringBootApplication
// @EnableMongoAuditing
// public class AiserviceApplication {

// 	public static void main(String[] args) {
// 		SpringApplication.run(AiserviceApplication.class, args);
// 	}

// }

package com.fitness.aiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.fitness.aiservice")
@@EnableMongoAuditing
public class AiserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiserviceApplication.class, args);
    }
}

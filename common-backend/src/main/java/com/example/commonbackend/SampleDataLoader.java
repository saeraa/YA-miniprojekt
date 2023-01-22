package com.example.commonbackend;

import com.example.commonbackend.model.Recommendation;
import com.example.commonbackend.service.RecommendationService;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Component
public class SampleDataLoader implements CommandLineRunner {

    private final Faker faker;
    private final RecommendationService recommendationService;

    public SampleDataLoader(Faker faker, RecommendationService recommendationService) {
        this.faker = faker;
        this.recommendationService = recommendationService;
    }

    @Override
    public void run(String... args) throws Exception {

        // List<Recommendation> recommendations = IntStream.rangeClosed(1,30)
        //         .mapToObj(i -> new Recommendation(
        //             faker.internet().emailAddress(),
        //                 faker.chuckNorris().fact(),
        //                 faker.number().numberBetween(1,77),
        //                 faker.number().numberBetween(1,10)
        //         )).collect(Collectors.toList());

        // recommendations.forEach(recommendationService::addRecommendation);
    }
}
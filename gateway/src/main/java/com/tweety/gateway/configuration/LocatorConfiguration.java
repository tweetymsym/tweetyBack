package com.tweety.gateway.configuration;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class LocatorConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(feedServiceRoute)
                .route(tweetServiceRoute)
                .route(userServiceRoute)
                .build();
    }

    Function<PredicateSpec, Buildable<Route>> tweetServiceRoute =
            predicateSpec -> predicateSpec
                    .path("/api/tweets/**")
                    .uri("lb://tweet-service");

    Function<PredicateSpec, Buildable<Route>> userServiceRoute =
            predicateSpec -> predicateSpec
                    .path("/api/users")
                    .uri("lb://user-service");

    Function<PredicateSpec, Buildable<Route>> feedServiceRoute =
            predicateSpec -> predicateSpec
                    .path("/api/feed/**")
                    .uri("lb://feed-service");
}

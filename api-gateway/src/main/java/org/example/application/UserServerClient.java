package org.example.application;

import com.micropos.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserServerClient {
    private final WebClient.Builder webClientBuilder;
    private final String USER_URL = "http://user-server/";

    public Mono<UserDto> getUser(final long userId) {
        return webClientBuilder.build().get()
                .uri(USER_URL + "users/{userId}", userId)
                .retrieve()
                .bodyToMono(UserDto.class);
    }
    public Mono<UserDto> chargeUser(final long userId) {
        return webClientBuilder.build().get()
                .uri(USER_URL + "users/{userId}/charge", userId)
                .retrieve()
                .bodyToMono(UserDto.class);
    }
}

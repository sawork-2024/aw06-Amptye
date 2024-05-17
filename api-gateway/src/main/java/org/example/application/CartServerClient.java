package org.example.application;

import com.micropos.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CartServerClient {
    private final WebClient.Builder webClientBuilder;
    private final String CART_URL = "http://cart-server/";

    public Mono<CartDto> getCart(final long cartId) {
        return webClientBuilder.build().get()
                .uri(CART_URL + "carts/{cartId}", cartId)
                .retrieve()
                .bodyToMono(CartDto.class);
    }
}

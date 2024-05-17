package org.example.application;

import com.micropos.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class OrderServerClient {
    private final WebClient.Builder webClientBuilder;
    private final String ORDER_URL = "http://order-server/";

    public Mono<OrderDto> getOrder(final long orderId) {
        return webClientBuilder.build().get()
                .uri(ORDER_URL + "orders/{orderId}", orderId)
                .retrieve()
                .bodyToMono(OrderDto.class);
    }
}

package org.example.boundary.web;

import com.micropos.dto.CartDto;
import com.micropos.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.example.application.CartServerClient;
import org.example.application.OrderServerClient;
import org.example.application.ProductServerClient;
import org.example.application.UserServerClient;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Maciej Szarlinski
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gateway")
public class ApiGatewayController {

    private final UserServerClient userServerClient;
    private final ProductServerClient productServerClient;
    private final CartServerClient cartServerClient;
    private final OrderServerClient orderServerClient;

    private final ReactiveCircuitBreakerFactory cbFactory;

    @GetMapping(value = "users/{userId}")
    public Mono<UserDto> getUserDetails(final @PathVariable long userId) {
        return userServerClient.getUser(userId);
    }
    @GetMapping(value = "users/{userId}/cart")
    public Mono<CartDto> getUserCartDetails(final @PathVariable long userId) {
        return userServerClient.getUser(userId)
                .flatMap(user ->
                        cartServerClient.getCart(user.getCartId())
                                .transform(it -> {
                                    ReactiveCircuitBreaker cb = cbFactory.create("getUserCartDetails");
                                    return cb.run(it, throwable -> emptyCartForUser());
                                })
                );
    }
    @PutMapping(value = "users/{userId}/charge")
    public Mono<UserDto> chargeUser(final @PathVariable long userId) {
        return userServerClient.chargeUser(userId);
    }

    @GetMapping(value = "products/{productId}")
    public Mono<UserDto> getProductDetails(final @PathVariable long productId) {
        return userServerClient.getUser(productId);
    }

    @GetMapping(value = "carts/{cartId}")
    public Mono<UserDto> getCartDetails(final @PathVariable long cartId) {
        return userServerClient.getUser(cartId);
    }

    @GetMapping(value = "orders/{orderId}")
    public Mono<UserDto> getOrderDetails(final @PathVariable long orderId) {
        return userServerClient.getUser(orderId);
    }

    private Mono<CartDto> emptyCartForUser() {
        return Mono.just(new CartDto());
    }
}

package org.example.application;

import com.micropos.dto.CategoryDto;
import com.micropos.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductServerClient {
    private final WebClient.Builder webClientBuilder;
    private final String PRODUCT_URL = "http://product-server/";

    public Mono<ProductDto> getProduct(final long productId) {
        return webClientBuilder.build().get()
                .uri(PRODUCT_URL + "products/{productId}", productId)
                .retrieve()
                .bodyToMono(ProductDto.class);
    }

    public Mono<CategoryDto> getCategory(final long categoryId) {
        return webClientBuilder.build().get()
                .uri(PRODUCT_URL + "categories/{categoryId}", categoryId)
                .retrieve()
                .bodyToMono(CategoryDto.class);
    }
}

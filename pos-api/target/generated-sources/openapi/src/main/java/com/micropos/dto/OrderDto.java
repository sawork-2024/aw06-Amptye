package com.micropos.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.micropos.dto.CartDto;
import com.micropos.dto.UserDto;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * OrderDto
 */

@JsonTypeName("Order")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-17T18:44:16.626729600+08:00[Asia/Shanghai]")
public class OrderDto {

  @JsonProperty("user")
  private UserDto user;

  @JsonProperty("cart")
  private CartDto cart;

  @JsonProperty("id")
  private Long id = null;

  public OrderDto user(UserDto user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
  */
  @NotNull @Valid 
  @Schema(name = "user", requiredMode = Schema.RequiredMode.REQUIRED)
  public UserDto getUser() {
    return user;
  }

  public void setUser(UserDto user) {
    this.user = user;
  }

  public OrderDto cart(CartDto cart) {
    this.cart = cart;
    return this;
  }

  /**
   * Get cart
   * @return cart
  */
  @NotNull @Valid 
  @Schema(name = "cart", requiredMode = Schema.RequiredMode.REQUIRED)
  public CartDto getCart() {
    return cart;
  }

  public void setCart(CartDto cart) {
    this.cart = cart;
  }

  public OrderDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @NotNull 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderDto order = (OrderDto) o;
    return Objects.equals(this.user, order.user) &&
        Objects.equals(this.cart, order.cart) &&
        Objects.equals(this.id, order.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, cart, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderDto {\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    cart: ").append(toIndentedString(cart)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


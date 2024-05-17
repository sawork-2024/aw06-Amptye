package com.micropos.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ChargeCartById200ResponseDto
 */

@JsonTypeName("chargeCartById_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-17T18:44:16.626729600+08:00[Asia/Shanghai]")
public class ChargeCartById200ResponseDto {

  @JsonProperty("total")
  private Double total = null;

  @JsonProperty("cartId")
  private Long cartId = null;

  public ChargeCartById200ResponseDto total(Double total) {
    this.total = total;
    return this;
  }

  /**
   * Get total
   * @return total
  */
  
  @Schema(name = "total", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public ChargeCartById200ResponseDto cartId(Long cartId) {
    this.cartId = cartId;
    return this;
  }

  /**
   * Get cartId
   * @return cartId
  */
  
  @Schema(name = "cartId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Long getCartId() {
    return cartId;
  }

  public void setCartId(Long cartId) {
    this.cartId = cartId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChargeCartById200ResponseDto chargeCartById200Response = (ChargeCartById200ResponseDto) o;
    return Objects.equals(this.total, chargeCartById200Response.total) &&
        Objects.equals(this.cartId, chargeCartById200Response.cartId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(total, cartId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChargeCartById200ResponseDto {\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    cartId: ").append(toIndentedString(cartId)).append("\n");
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


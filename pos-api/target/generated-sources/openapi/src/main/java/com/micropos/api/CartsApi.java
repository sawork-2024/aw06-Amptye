/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.micropos.api;

import com.micropos.dto.CartDto;
import com.micropos.dto.CartFieldsDto;
import com.micropos.dto.ChargeCartById200ResponseDto;
import com.micropos.dto.ErrorDto;
import com.micropos.dto.ItemDto;
import com.micropos.dto.ItemFieldsDto;
import com.micropos.dto.UserDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-17T18:44:16.626729600+08:00[Asia/Shanghai]")
@Validated
@Tag(name = "carts", description = "the carts API")
public interface CartsApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /carts : Create a cart
     * Creates a cart.
     *
     * @param cartFieldsDto The cart (required)
     * @return Cart created successfully. (status code 201)
     *         or Not modified. (status code 304)
     *         or Bad request. (status code 400)
     *         or User not found. (status code 404)
     *         or Server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "addCart",
        summary = "Create a cart",
        description = "Creates a cart.",
        tags = { "cart" },
        responses = {
            @ApiResponse(responseCode = "201", description = "Cart created successfully.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CartDto.class))
            }),
            @ApiResponse(responseCode = "304", description = "Not modified."),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server error.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/carts",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<CartDto> addCart(
        @Parameter(name = "CartFieldsDto", description = "The cart", required = true) @Valid @RequestBody CartFieldsDto cartFieldsDto
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /carts/{cartId}/items/{itemId}/addone
     *
     * @param cartId The ID of the cart. (required)
     * @param itemId The ID of the item. (required)
     * @return Item details found and returned. (status code 200)
     *         or Not modified. (status code 304)
     *         or Bad request. (status code 400)
     *         or User not found. (status code 404)
     *         or Server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "addCartsItemById",
        tags = { "item" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Item details found and returned.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ItemDto.class))
            }),
            @ApiResponse(responseCode = "304", description = "Not modified."),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server error.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/carts/{cartId}/items/{itemId}/addone",
        produces = { "application/json" }
    )
    default ResponseEntity<ItemDto> addCartsItemById(
        @Parameter(name = "cartId", description = "The ID of the cart.", required = true, in = ParameterIn.PATH) @PathVariable("cartId") Long cartId,
        @Parameter(name = "itemId", description = "The ID of the item.", required = true, in = ParameterIn.PATH) @PathVariable("itemId") Long itemId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /carts/{cartId}/items : Adds a item to a cart
     * Records the details of a new item.
     *
     * @param cartId The ID of the cart. (required)
     * @param itemFieldsDto The details of the new item. (required)
     * @return The item was sucessfully added. (status code 201)
     *         or Bad request. (status code 400)
     *         or Cart not found. (status code 404)
     *         or Server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "addItemToCart",
        summary = "Adds a item to a cart",
        description = "Records the details of a new item.",
        tags = { "item" },
        responses = {
            @ApiResponse(responseCode = "201", description = "The item was sucessfully added.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ItemDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Cart not found.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server error.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/carts/{cartId}/items",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<ItemDto> addItemToCart(
        @Parameter(name = "cartId", description = "The ID of the cart.", required = true, in = ParameterIn.PATH) @PathVariable("cartId") Long cartId,
        @Parameter(name = "ItemFieldsDto", description = "The details of the new item.", required = true) @Valid @RequestBody ItemFieldsDto itemFieldsDto
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /carts/{cartId}/{productId} : Adds a product to a cart
     *
     * @param cartId The ID of the cart. (required)
     * @param productId The ID of the product. (required)
     * @return The item was sucessfully added. (status code 201)
     *         or Bad request. (status code 400)
     *         or Cart not found. (status code 404)
     *         or Server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "addProductToCart",
        summary = "Adds a product to a cart",
        tags = { "item" },
        responses = {
            @ApiResponse(responseCode = "201", description = "The item was sucessfully added.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CartDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Cart not found.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server error.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/carts/{cartId}/{productId}",
        produces = { "application/json" }
    )
    default ResponseEntity<CartDto> addProductToCart(
        @Parameter(name = "cartId", description = "The ID of the cart.", required = true, in = ParameterIn.PATH) @PathVariable("cartId") Long cartId,
        @Parameter(name = "productId", description = "The ID of the product.", required = true, in = ParameterIn.PATH) @PathVariable("productId") Long productId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /carts/{cartId}/charge : charge for the cart
     *
     * @param cartId The id of the cart to retrieve (required)
     * @param userDto The user details to use for the charge. (required)
     * @return Expected response to a valid request (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "chargeCartById",
        summary = "charge for the cart",
        tags = { "cart" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Expected response to a valid request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ChargeCartById200ResponseDto.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/carts/{cartId}/charge",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<ChargeCartById200ResponseDto> chargeCartById(
        @Parameter(name = "cartId", description = "The id of the cart to retrieve", required = true, in = ParameterIn.PATH) @PathVariable("cartId") Long cartId,
        @Parameter(name = "UserDto", description = "The user details to use for the charge.", required = true) @Valid @RequestBody UserDto userDto
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"total\" : \"\", \"cartId\" : \"\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /carts/{cartId} : Clear a cart&#39;s items
     * Clear a cart&#39;s items.
     *
     * @param cartId The ID of the cart. (required)
     * @return Item details found and returned. (status code 200)
     *         or Not modified. (status code 304)
     *         or Bad request. (status code 400)
     *         or Item not found for this cart. (status code 404)
     *         or Server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "clearCart",
        summary = "Clear a cart's items",
        description = "Clear a cart's items.",
        tags = { "cart" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Item details found and returned.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CartDto.class))
            }),
            @ApiResponse(responseCode = "304", description = "Not modified."),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Item not found for this cart.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server error.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/carts/{cartId}",
        produces = { "application/json" }
    )
    default ResponseEntity<CartDto> clearCart(
        @Parameter(name = "cartId", description = "The ID of the cart.", required = true, in = ParameterIn.PATH) @PathVariable("cartId") Long cartId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /carts/{cartId} : Delete an cart by ID
     * Returns the cart or a 404 error.
     *
     * @param cartId The ID of the cart. (required)
     * @return Cart details found and returned. (status code 200)
     *         or Not modified. (status code 304)
     *         or Bad request. (status code 400)
     *         or Owner  not found. (status code 404)
     *         or Server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "deleteCart",
        summary = "Delete an cart by ID",
        description = "Returns the cart or a 404 error.",
        tags = { "cart" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Cart details found and returned.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CartDto.class))
            }),
            @ApiResponse(responseCode = "304", description = "Not modified."),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Owner  not found.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server error.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/carts/{cartId}",
        produces = { "application/json" }
    )
    default ResponseEntity<CartDto> deleteCart(
        @Parameter(name = "cartId", description = "The ID of the cart.", required = true, in = ParameterIn.PATH) @PathVariable("cartId") Long cartId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /carts/{cartId}/items/{itemId} : Delete an item by ID in a Cart
     * Returns the item or a 404 error.
     *
     * @param cartId The ID of the cart. (required)
     * @param itemId The ID of the item. (required)
     * @return Item details found and returned. (status code 200)
     *         or Not modified. (status code 304)
     *         or Bad request. (status code 400)
     *         or Owner  not found. (status code 404)
     *         or Server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "deleteCartsItem",
        summary = "Delete an item by ID in a Cart",
        description = "Returns the item or a 404 error.",
        tags = { "item" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Item details found and returned.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ItemDto.class))
            }),
            @ApiResponse(responseCode = "304", description = "Not modified."),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Owner  not found.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server error.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/carts/{cartId}/items/{itemId}",
        produces = { "application/json" }
    )
    default ResponseEntity<ItemDto> deleteCartsItem(
        @Parameter(name = "cartId", description = "The ID of the cart.", required = true, in = ParameterIn.PATH) @PathVariable("cartId") Long cartId,
        @Parameter(name = "itemId", description = "The ID of the item.", required = true, in = ParameterIn.PATH) @PathVariable("itemId") Long itemId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /carts/{cartId}/items/{itemId} : Get a item by ID
     * Returns the item or a 404 error.
     *
     * @param cartId The ID of the cart. (required)
     * @param itemId The ID of the item. (required)
     * @return Item details found and returned. (status code 200)
     *         or Not modified. (status code 304)
     *         or Bad request. (status code 400)
     *         or Item not found. (status code 404)
     *         or Server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "getCartsItem",
        summary = "Get a item by ID",
        description = "Returns the item or a 404 error.",
        tags = { "item" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Item details found and returned.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ItemDto.class))
            }),
            @ApiResponse(responseCode = "304", description = "Not modified."),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Item not found.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server error.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/carts/{cartId}/items/{itemId}",
        produces = { "application/json" }
    )
    default ResponseEntity<ItemDto> getCartsItem(
        @Parameter(name = "cartId", description = "The ID of the cart.", required = true, in = ParameterIn.PATH) @PathVariable("cartId") Long cartId,
        @Parameter(name = "itemId", description = "The ID of the item.", required = true, in = ParameterIn.PATH) @PathVariable("itemId") Long itemId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /carts : List all carts
     *
     * @return A paged array of carts (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "listCarts",
        summary = "List all carts",
        tags = { "carts" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A paged array of carts", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CartDto.class)))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/carts",
        produces = { "application/json" }
    )
    default ResponseEntity<List<CartDto>> listCarts(
        
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ null, null ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /carts/{cartId}/items : List all items of a cart
     *
     * @param cartId The ID of the cart. (required)
     * @return A paged array of items (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "listCartsItems",
        summary = "List all items of a cart",
        tags = { "items" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A paged array of items", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ItemDto.class)))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/carts/{cartId}/items",
        produces = { "application/json" }
    )
    default ResponseEntity<List<ItemDto>> listCartsItems(
        @Parameter(name = "cartId", description = "The ID of the cart.", required = true, in = ParameterIn.PATH) @PathVariable("cartId") Long cartId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ null, null ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /carts/{cartId} : Info for a specific cart
     *
     * @param cartId The id of the item to retrieve (required)
     * @return Expected response to a valid request (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "showCartById",
        summary = "Info for a specific cart",
        tags = { "cart" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Expected response to a valid request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CartDto.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/carts/{cartId}",
        produces = { "application/json" }
    )
    default ResponseEntity<CartDto> showCartById(
        @Parameter(name = "cartId", description = "The id of the item to retrieve", required = true, in = ParameterIn.PATH) @PathVariable("cartId") Long cartId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /carts/{cartId}/items/{itemId}/subone
     *
     * @param cartId The ID of the cart. (required)
     * @param itemId The ID of the item. (required)
     * @return Item details found and returned. (status code 200)
     *         or Not modified. (status code 304)
     *         or Bad request. (status code 400)
     *         or User not found. (status code 404)
     *         or Server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "subCartsItemById",
        tags = { "item" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Item details found and returned.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ItemDto.class))
            }),
            @ApiResponse(responseCode = "304", description = "Not modified."),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server error.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/carts/{cartId}/items/{itemId}/subone",
        produces = { "application/json" }
    )
    default ResponseEntity<ItemDto> subCartsItemById(
        @Parameter(name = "cartId", description = "The ID of the cart.", required = true, in = ParameterIn.PATH) @PathVariable("cartId") Long cartId,
        @Parameter(name = "itemId", description = "The ID of the item.", required = true, in = ParameterIn.PATH) @PathVariable("itemId") Long itemId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /carts/{cartId}/items/{itemId} : Update a item&#39;s details
     * Updates the item record with the specified details.
     *
     * @param cartId The ID of the cart. (required)
     * @param itemId The ID of the item. (required)
     * @param itemFieldsDto The item details to use for the update. (required)
     * @return Update successful. (status code 204)
     *         or Not modified. (status code 304)
     *         or Bad request. (status code 400)
     *         or Item not found for this cart. (status code 404)
     *         or Server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "updateCartsItem",
        summary = "Update a item's details",
        description = "Updates the item record with the specified details.",
        tags = { "item" },
        responses = {
            @ApiResponse(responseCode = "204", description = "Update successful."),
            @ApiResponse(responseCode = "304", description = "Not modified."),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Item not found for this cart.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server error.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/carts/{cartId}/items/{itemId}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> updateCartsItem(
        @Parameter(name = "cartId", description = "The ID of the cart.", required = true, in = ParameterIn.PATH) @PathVariable("cartId") Long cartId,
        @Parameter(name = "itemId", description = "The ID of the item.", required = true, in = ParameterIn.PATH) @PathVariable("itemId") Long itemId,
        @Parameter(name = "ItemFieldsDto", description = "The item details to use for the update.", required = true) @Valid @RequestBody ItemFieldsDto itemFieldsDto
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}

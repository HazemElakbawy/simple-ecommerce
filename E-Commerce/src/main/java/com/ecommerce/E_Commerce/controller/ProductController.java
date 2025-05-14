package com.ecommerce.E_Commerce.controller;

import com.ecommerce.E_Commerce.config.AppProperties;
import com.ecommerce.E_Commerce.constants.SuccessMessages;
import com.ecommerce.E_Commerce.dto.ApiResponse;
import com.ecommerce.E_Commerce.dto.request.ProductReqDto;
import com.ecommerce.E_Commerce.dto.response.ProductResDto;
import com.ecommerce.E_Commerce.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ecommerce.E_Commerce.helper.SuccessResponseHelper.buildResponse;

@RestController
@RequestMapping("/api/${app.api.version}/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;
  private final AppProperties properties;

  @PostMapping
  public ResponseEntity<ApiResponse<ProductResDto>> createProduct(
      @RequestBody ProductReqDto req,
      HttpServletRequest request) {

    ProductResDto created = productService.create(req);
    return buildResponse(
        properties.api().version(),
        request,
        HttpStatus.CREATED,
        created,
        SuccessMessages.CREATE_PRODUCT_MSG.message
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<ProductResDto>> getProductById(
      @PathVariable Long id,
      HttpServletRequest request) {

    ProductResDto dto = productService.getById(id);
    return buildResponse(
        properties.api().version(),
        request,
        HttpStatus.OK,
        dto,
        SuccessMessages.GET_PRODUCT_MSG.message
    );
  }


  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<ProductResDto>> updateProduct(
      @PathVariable Long id,
      @RequestBody ProductReqDto req,
      HttpServletRequest request) {

    ProductResDto updated = productService.update(id, req);
    return buildResponse(
        properties.api().version(),
        request,
        HttpStatus.OK,
        updated,
        SuccessMessages.UPDATE_PRODUCT_MSG.message
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> deleteProduct(
      @PathVariable Long id,
      HttpServletRequest request) {

    productService.delete(id);
    return buildResponse(
        properties.api().version(),
        request,
        HttpStatus.NO_CONTENT,
        null,
        SuccessMessages.DELETE_PRODUCT_MSG.message
    );
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<ProductResDto>>> filterProducts(
      @RequestParam(required = false) Long categoryId,
      @RequestParam(required = false) String size,
      @RequestParam(required = false) String color,
      HttpServletRequest request) {

    List<ProductResDto> list = productService.filter(categoryId, size, color);
    return buildResponse(
        properties.api().version(),
        request,
        HttpStatus.OK,
        list,
        SuccessMessages.FILTER_PRODUCTS_MSG.message
    );
  }
}

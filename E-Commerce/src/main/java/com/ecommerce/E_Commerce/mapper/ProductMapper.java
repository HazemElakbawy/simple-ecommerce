package com.ecommerce.E_Commerce.mapper;

import com.ecommerce.E_Commerce.dto.request.ProductReqDto;
import com.ecommerce.E_Commerce.dto.response.ProductResDto;
import com.ecommerce.E_Commerce.entity.Category;
import com.ecommerce.E_Commerce.entity.Product;
import com.ecommerce.E_Commerce.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = ComponentModel.SPRING, imports = Category.class)
public interface ProductMapper {

  @Mapping(target = "categoryId", source = "category.id")
  @Mapping(target = "categoryName", source = "category.name")
  @Mapping(target = "price", source = "product.price")
  ProductResDto toProductDto(Product product);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "price", source = "req.price")
  @Mapping(target = "category", expression = "java(fetchCategory(req.categoryName(), categoryRepo))")
  Product toProductEntity(ProductReqDto req, @Context CategoryRepository categoryRepo);

  void updateProductFromDto(ProductReqDto req, @MappingTarget Product entity, @Context CategoryRepository catRepo);

  default Category fetchCategory(String categoryName, @Context CategoryRepository categoryRepo) {
    return categoryRepo.findByName(categoryName)
        .orElseThrow(() -> new EntityNotFoundException("Category not found: " + categoryName));
  }
}

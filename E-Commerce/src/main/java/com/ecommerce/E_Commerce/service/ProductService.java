package com.ecommerce.E_Commerce.service;

import com.ecommerce.E_Commerce.dto.request.ProductReqDto;
import com.ecommerce.E_Commerce.dto.response.ProductResDto;
import com.ecommerce.E_Commerce.entity.Product;
import com.ecommerce.E_Commerce.mapper.ProductMapper;
import com.ecommerce.E_Commerce.repository.CategoryRepository;
import com.ecommerce.E_Commerce.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final ProductMapper productMapper;

  @Transactional
  public ProductResDto create(ProductReqDto req) {
    Product toSave = productMapper.toProductEntity(req, categoryRepository);
    Product saved = productRepository.save(toSave);
    return productMapper.toProductDto(saved);
  }

  public ProductResDto getById(Long id) {
    return productRepository.findById(id).map(productMapper::toProductDto)
        .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));
  }

  @Transactional
  public ProductResDto update(Long id, ProductReqDto req) {
    Product existing = productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));

    productMapper.updateProductFromDto(req, existing, categoryRepository);
    Product updated = productRepository.save(existing);
    return productMapper.toProductDto(updated);
  }

  @Transactional
  public void delete(Long id) {
    if (!productRepository.existsById(id)) {
      throw new EntityNotFoundException("Product not found: " + id);
    }
    productRepository.deleteById(id);
  }

  public List<ProductResDto> filter(Long categoryId, String size, String color) {
    Specification<Product> spec = Specification.where(null);

    if (categoryId != null) {
      spec = spec.and((prod, _, cb) -> cb.equal(prod.get("category").get("id"), categoryId)
      );
    }

    if (size != null && !size.isBlank()) {
      spec = spec.and((prod, _, cb) -> cb.equal(prod.get("size"), size)
      );
    }

    if (color != null && !color.isBlank()) {
      spec = spec.and((prod, _, cb) -> cb.equal(prod.get("color"), color)
      );
    }

    return productRepository.findAll(spec)
        .stream()
        .map(productMapper::toProductDto)
        .collect(Collectors.toList());
  }
}

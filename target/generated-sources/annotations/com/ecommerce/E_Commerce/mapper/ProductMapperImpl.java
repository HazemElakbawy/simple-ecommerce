package com.ecommerce.E_Commerce.mapper;

import com.ecommerce.E_Commerce.dto.request.ProductReqDto;
import com.ecommerce.E_Commerce.dto.response.ProductResDto;
import com.ecommerce.E_Commerce.entity.Category;
import com.ecommerce.E_Commerce.entity.Product;
import com.ecommerce.E_Commerce.repository.CategoryRepository;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-15T14:02:38+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductResDto toProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        Long categoryId = null;
        String categoryName = null;
        Long id = null;
        String name = null;
        String description = null;
        String pictureUrl = null;
        String size = null;
        String color = null;

        categoryId = productCategoryId( product );
        categoryName = productCategoryName( product );
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        pictureUrl = product.getPictureUrl();
        size = product.getSize();
        color = product.getColor();

        ProductResDto productResDto = new ProductResDto( id, name, description, pictureUrl, size, color, categoryId, categoryName );

        return productResDto;
    }

    @Override
    public Product toProductEntity(ProductReqDto req, CategoryRepository categoryRepo) {
        if ( req == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        if ( req.price() != null ) {
            product.price( BigDecimal.valueOf( req.price() ) );
        }
        product.name( req.name() );
        product.description( req.description() );
        product.pictureUrl( req.pictureUrl() );
        product.size( req.size() );
        product.color( req.color() );

        product.category( fetchCategory(req.categoryId(), categoryRepo) );

        return product.build();
    }

    @Override
    public void updateProductFromDto(ProductReqDto req, Product entity, CategoryRepository catRepo) {
        if ( req == null ) {
            return;
        }

        entity.setName( req.name() );
        entity.setDescription( req.description() );
        entity.setPictureUrl( req.pictureUrl() );
        entity.setSize( req.size() );
        entity.setColor( req.color() );
        if ( req.price() != null ) {
            entity.setPrice( BigDecimal.valueOf( req.price() ) );
        }
        else {
            entity.setPrice( null );
        }
    }

    private Long productCategoryId(Product product) {
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getId();
    }

    private String productCategoryName(Product product) {
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getName();
    }
}

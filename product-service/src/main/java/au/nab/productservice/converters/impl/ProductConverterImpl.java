package au.nab.productservice.converters.impl;

import au.nab.productservice.constants.User;
import au.nab.productservice.converters.ProductConverter;
import au.nab.productservice.dtos.ProductDto;
import au.nab.productservice.dtos.http.ProductResponse;
import au.nab.productservice.entities.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class ProductConverterImpl implements ProductConverter {
    @Override
    public Product convertToEntity(ProductDto dto) {
        return Product
                .builder()
                .brand(dto.getBrand())
                .categories(dto.getCategories())
                .colors(dto.getColors())
                .createdAt(LocalDateTime.now(ZoneOffset.UTC))
                .updatedAt(LocalDateTime.now(ZoneOffset.UTC))
                .createdBy(User.OPERATOR)
                .updatedBy(User.OPERATOR)
                .description(dto.getDescription())
                .name(dto.getName())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
    }

    @Override
    public ProductResponse convertToDto(Product entity) {
        return ProductResponse
                .builder()
                .id(entity.getId())
                .brand(entity.getBrand())
                .categories(entity.getCategories())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .colors(entity.getColors())
                .description(entity.getDescription())
                .name(entity.getName())
                .build();
    }
}

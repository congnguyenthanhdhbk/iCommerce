package au.nab.productservice.converters;

import au.nab.productservice.dtos.ProductDto;
import au.nab.productservice.dtos.http.ProductResponse;
import au.nab.productservice.entities.Product;

public interface ProductConverter {
    Product convertToEntity(final ProductDto dto);
    ProductResponse convertToDto(final Product entity);
}

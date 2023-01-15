package ru.geekbrains.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.model.Product;

@Component
public class ProductConverter {
    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getName(), productDto.getCost());
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getCost());
    }
}

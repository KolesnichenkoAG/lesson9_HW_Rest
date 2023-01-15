package ru.geekbrains.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.geekbrains.cart.Cart;
import ru.geekbrains.converters.ProductConverter;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.exceptions.ResourceNotFoundException;
import ru.geekbrains.model.Product;

import java.util.List;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
    private final Cart cart;
    private final ProductService productService;
    private final ProductConverter productConverter;

    public void addProduct(Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        cart.addProduct(productConverter.entityToDto(product));
    }

    public List<ProductDto> showCart() {
        return cart.showCart();
    }

    public void deleteById(Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        cart.delete(productConverter.entityToDto(product));
    }
}

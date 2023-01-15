package ru.geekbrains.cart;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.geekbrains.converters.ProductConverter;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Cart {
    private ProductConverter productConverter;
    private List<ProductDto> purchase;
    private int amount;

    @PostConstruct
    public void createCart() {
        purchase = new ArrayList<>();
    }

    public void addProduct(ProductDto productDto) {
        purchase.add(productDto);
        amount += 1;
    }

    public List<ProductDto> showCart() {
        return Collections.unmodifiableList(purchase);
    }

    public void delete(ProductDto productDto) {
        purchase.remove(productDto);
    }
}

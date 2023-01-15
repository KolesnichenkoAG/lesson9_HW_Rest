package ru.geekbrains.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final CartService cartService;

    @GetMapping
    public List<ProductDto> showCart() {
        return cartService.showCart();
    }

    @GetMapping("/add/{id}")
    public void addProduct(@PathVariable Long id) {
        cartService.addProduct(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteByIndex(@PathVariable Long id) {
        cartService.deleteById(id);
    }
}

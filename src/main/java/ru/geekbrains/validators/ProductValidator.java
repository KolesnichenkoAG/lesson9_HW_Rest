package ru.geekbrains.validators;

import org.springframework.stereotype.Component;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        if (productDto.getCost() < 1) {
            errors.add("Цена продукта не может быть меньше 1");
        }
        if (productDto.getName().isBlank()) {
            errors.add("Продукт не моежет иметь пустое название");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}

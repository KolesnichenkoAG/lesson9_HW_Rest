package ru.geekbrains.service;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.geekbrains.model.Product;
import ru.geekbrains.repository.ProductRepository;

@Component
public class ProductGenerator {

    @Autowired
    private ProductRepository productRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void generateProductOnStartup() {
        Faker faker = new Faker();

        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            product.setName(faker.name().fullName());
            product.setCost(faker.number().numberBetween(300, 1500));

            productRepository.save(product);
        }
    }
}

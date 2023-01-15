package ru.geekbrains.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.exceptions.ResourceNotFoundException;
import ru.geekbrains.model.Product;
import ru.geekbrains.repository.ProductRepository;
import ru.geekbrains.repository.specifications.ProductsSpecifications;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> findAll(Integer minCost, Integer maxCost, String partName, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minCost != null) {
            spec = spec.and(ProductsSpecifications.costGreaterOrEqualsThan(minCost));
        }
        if (maxCost != null) {
            spec = spec.and(ProductsSpecifications.costLessOrEqualsThan(maxCost));
        }
        if (partName != null) {
            spec = spec.and(ProductsSpecifications.nameLike(partName));
        }
        return productRepository.findAll(spec, PageRequest.of(page -1, 25));
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product update(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить продукт, не найден в базе, id: " + productDto));
        product.setCost(productDto.getCost());
        product.setName(productDto.getName());
        return product;
    }
}

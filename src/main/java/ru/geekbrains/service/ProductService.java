package ru.geekbrains.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.model.Product;
import ru.geekbrains.repository.ProductRepository;
import ru.geekbrains.repository.specifications.ProductsSpecifications;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> find(Integer minCost, Integer maxCost, String partName, Integer page) {
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
        return productRepository.findAll(spec, PageRequest.of(page -1, 5));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
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
}

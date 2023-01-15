package ru.geekbrains.repository.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.model.Product;

public class ProductsSpecifications {
    public static Specification<Product> costGreaterOrEqualsThan(Integer cost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), cost);
    }

    public static Specification<Product> costLessOrEqualsThan(Integer cost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), cost);
    }

    public static Specification<Product> nameLike(String namePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%", namePart));
    }
}

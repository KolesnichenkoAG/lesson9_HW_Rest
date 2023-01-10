package ru.geekbrains.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.model.Product;
import ru.geekbrains.service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_cost", required = false) Integer minCost,
            @RequestParam(name = "max_cost", required = false) Integer maxCost,
            @RequestParam(name = "name_part", required = false) String namePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.find(minCost, maxCost, namePart, page).map(
                s -> new ProductDto(s)
        );
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow();
    }

    @PostMapping
    public Product saveNewProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(null);
        product.setName(productDto.getName());
        product.setCost(productDto.getCost());
        return productService.save(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody ProductDto productDto) {
        Optional<Product> product = productService.findById(productDto.getId());
        product.get().setCost(productDto.getCost());
        product.get().setName(productDto.getName());
        return productService.save(product.get());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable  Long id) {
        productService.deleteById(id);
    }
}

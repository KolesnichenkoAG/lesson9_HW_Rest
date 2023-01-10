package ru.geekbrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findAllByCostBetween(Integer minCost, Integer maxCost);

    Optional<Product> findByName(String name);

    void deleteById(Long id);

    @Query("select c from Product c where c.cost = (select max(cc.cost) from Product cc)")
    Product getExpensive();

    @Query("select c from Product c where c.cost = (select min(cc.cost) from Product cc)")
    Product getCheap();
}

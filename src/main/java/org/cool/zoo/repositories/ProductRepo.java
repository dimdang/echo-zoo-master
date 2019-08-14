package org.cool.zoo.repositories;

import org.cool.zoo.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    //@Query("select pro from  Product  pro join")
    List<Product> getAllBy(@Param("dealerId") Long dealerId);

    List<Product> getAllByProductIdIn(@Param("productId") Long[] productId);
}
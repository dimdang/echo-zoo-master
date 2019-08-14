package org.cool.zoo.repositories;

import org.cool.zoo.entities.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query(value = "select cus from Customer cus where  cus.id = :id")
    Customer getById(@Param("id") Long id);
}

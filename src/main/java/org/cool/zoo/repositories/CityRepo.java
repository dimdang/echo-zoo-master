package org.cool.zoo.repositories;

import org.cool.zoo.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City, Long> {
}

package org.cool.zoo.api;


import org.cool.zoo.configure.Routes;
import org.cool.zoo.entities.City;
import org.cool.zoo.repositories.CityRepo;
import org.cool.zoo.util.JResponseEntity;
import org.cool.zoo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Routes.MOBILE)
public class CityController {

    @Autowired
    private CityRepo cityRepo;

    List<City> cities;

    public ResponseEntity addNewCity(@RequestBody City city) {
        City cit = new City();
        cities = new ArrayList<>();
        try {
            if (city != null) {
                cit.setCity(city.getCity());
                cit.setCreatedDate(city.getCreatedDate());
                cityRepo.save(cit);

                cities.add(cit);
            }
        } catch (Exception e) {
            ResponseFactory.build(cities);
        }
        return null;
    }

    @GetMapping(value = "list-cities")
    public JResponseEntity getAllCities() {
        try {
            cities = cityRepo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseFactory.build(cities);
    }
}

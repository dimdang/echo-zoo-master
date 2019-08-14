package org.cool.zoo.api;

import org.cool.zoo.configure.Routes;
import org.cool.zoo.entities.product.Product;
import org.cool.zoo.entities.users.Dealer;
import org.cool.zoo.repositories.DealerRepo;
import org.cool.zoo.repositories.ProductRepo;
import org.cool.zoo.util.JResponseEntity;
import org.cool.zoo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = Routes.MOBILE)
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private DealerRepo dealerRepo;

    List<Product> products = null;

    @GetMapping(value = "/get-products-by-dealer", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JResponseEntity getDealerProducts(@RequestParam ( "dealerId")Long dealerId) {
        Dealer dealer;
        try {
            if (dealerId != null) {
                dealer = dealerRepo.findByDealerId(dealerId);
                if (dealer != null) {
                    products = dealer.getProducts();
                }
            }
        } catch (Exception e) {
            return ResponseFactory.fail();
        }
        return ResponseFactory.build(products);
    }

    @GetMapping(value = "/get-all-products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JResponseEntity getAllProducts() {
        try {
            products = productRepo.findAll();
        } catch (Exception e) {
            return ResponseFactory.fail();
        }
        return ResponseFactory.build(products);
    }

    @PostMapping(value = "/new-product", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JResponseEntity createProduct(@RequestBody Product product) {
        try {
            if (product != null) {
                products = new ArrayList<>();
                Product pro = new Product();
                pro.setProductName(product.getProductName());
                pro.setProductCode(product.getProductCode());
                pro.setCreatedBy(product.getCreatedBy());
                pro.setCreatedDate(product.getCreatedDate());
                productRepo.save(pro);
                products.add(pro);
            }

        } catch (Exception e) {
            return ResponseFactory.fail();
        }

        return ResponseFactory.build(products);
    }

}

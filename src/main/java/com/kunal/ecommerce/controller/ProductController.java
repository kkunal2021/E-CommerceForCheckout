package com.kunal.ecommerce.controller;


import com.kunal.ecommerce.internal.entity.Product;
import com.kunal.ecommerce.internal.repository.OfferRepository;
import com.kunal.ecommerce.internal.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kunal
 * @project ECommerceCheckout
 */

@RestController
@RequestMapping (value = "/product")
public class ProductController {
    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OfferRepository offerRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProduct(@RequestParam(value = "name") String name) {
        return productRepository.getOne(name);
    }

    @GetMapping(value = "/listProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    @PostMapping(value = "/addProducts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> updateProduct(@RequestBody Product product) {
        product.setWatchName(product.getWatchName());
        productRepository.save(product);
        return productRepository.findAll();
    }

}

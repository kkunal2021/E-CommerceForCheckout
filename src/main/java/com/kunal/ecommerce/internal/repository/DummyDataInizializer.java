package com.kunal.ecommerce.internal.repository;

import com.kunal.ecommerce.internal.entity.Offer;
import com.kunal.ecommerce.internal.entity.Product;
import com.kunal.ecommerce.internal.repository.OfferRepository;
import com.kunal.ecommerce.internal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author kunal
 * @project ECommerceCheckout
 */

@Component
public class DummyDataInizializer implements ApplicationRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public void run(ApplicationArguments args) {
        productRepository.save(new Product("001", "Rolex", "", BigDecimal.valueOf(100), 6));
        productRepository.save(new Product("001","Rolex", "", BigDecimal.valueOf(100), 3));
        productRepository.save(new Product("002","Michael Kors", "", BigDecimal.valueOf(80), 2));
        productRepository.save(new Product("003","Swatch","", BigDecimal.valueOf(50), 0));
        productRepository.save(new Product("004", "Casio","", BigDecimal.valueOf(30), 0));

        offerRepository.save(new Offer("Rolex", BigInteger.TEN.intValue()));
        offerRepository.save(new Offer("Rolex", BigInteger.ZERO.intValue()));
        offerRepository.save(new Offer("Michael Kors", BigInteger.TEN.intValue()));
    }
}

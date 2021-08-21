package com.kunal.ecommerce.controller;

import com.kunal.ecommerce.internal.entity.Offer;
import com.kunal.ecommerce.internal.repository.OfferRepository;
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
@RequestMapping (value = "/offer")
public class OfferController {
    private static final Logger LOG = LoggerFactory.getLogger(OfferController.class);

    @Autowired
    private OfferRepository offerRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Offer getProduct(@RequestParam(value = "code") String code) {
        return offerRepository.getOne(code);
    }

    @GetMapping(value = "/listOffers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Offer> listOffers() {
        return offerRepository.findAll();
    }

    @PostMapping(value = "/addOffers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Offer> updateProduct(@RequestBody Offer offer) {
        offerRepository.save(offer);
        return offerRepository.findAll();
    }

}

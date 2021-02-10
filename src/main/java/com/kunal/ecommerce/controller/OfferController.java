/*
 * (C) Copyright ${year} Mauro Mozzarelli.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     ...
 */
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

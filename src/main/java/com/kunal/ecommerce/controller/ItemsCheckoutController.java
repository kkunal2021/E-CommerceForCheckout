package com.kunal.ecommerce.controller;

import com.kunal.ecommerce.internal.entity.Product;
import com.kunal.ecommerce.internal.entity.Offer;
import com.kunal.ecommerce.internal.repository.OfferRepository;
import com.kunal.ecommerce.internal.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author kunal
 * @project ECommerceCheckout
 */

@RestController
@RequestMapping(value = "/checkout")
public class ItemsCheckoutController {

    private static final Logger LOG = LoggerFactory.getLogger(ItemsCheckoutController.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OfferRepository offerRepository;

    @GetMapping(value = "/itemsList", produces = MediaType.APPLICATION_JSON_VALUE)
    public BigDecimal checkoutTotal(@RequestParam("items") List<String> items) {
        BigDecimal eCommerceCartTotal = BigDecimal.valueOf(0);
        BigDecimal shoppingProductPrice;
        for (String item : items) {
            try {
                shoppingProductPrice = productRepository.getOne(item.toLowerCase()).getUnitPrice();
                eCommerceCartTotal = eCommerceCartTotal.add(shoppingProductPrice);
                LOG.debug("Item: {} ", item, shoppingProductPrice, eCommerceCartTotal);
            } catch (EntityNotFoundException entityNotFoundException) {
                LOG.error("Exception {} - ", entityNotFoundException.getMessage(), entityNotFoundException.getCause().getMessage());
            }
        }
        return eCommerceCartTotal;
    }

    @GetMapping(value = "/itemsListOffers", produces = MediaType.APPLICATION_JSON_VALUE)
    public BigDecimal checkoutWithOffers(@RequestHeader(value="Offer-Code") String offerCode,
                                         @RequestParam("items") List<String> items) {

        BigDecimal shoppingCartTotal = BigDecimal.valueOf(0);
        BigDecimal shoppingCartPrice;
        Map<String, Integer> shoppingCartItemsMap = new HashMap<>();

        for (String item : items) {
            try {
                int shoppingProductsCount;
                if (shoppingCartItemsMap.containsKey(item.toLowerCase())) {
                    shoppingProductsCount = shoppingCartItemsMap.get(item.toLowerCase()) + 1;
                }
                else {
                    shoppingProductsCount = 1;
                }

                shoppingCartItemsMap.put(item.toLowerCase(), Integer.valueOf(shoppingProductsCount));
                shoppingCartPrice = productRepository.getOne(item.toLowerCase()).getUnitPrice();
                shoppingCartTotal = shoppingCartTotal.add(shoppingCartPrice);
                LOG.debug("Fetching the Product Item with shoppingCartPrice{} shoppingCartTotal{} ", item, shoppingCartPrice, shoppingCartTotal);
            } catch (EntityNotFoundException entityNotFoundException) {
                LOG.debug("Product Item Not Found {} ", item);
            } catch (Exception e) {
                LOG.error("Exception {} - ", e.getMessage(), e.getCause().getMessage());
            }
        }
        Iterator entryIterator = shoppingCartItemsMap.entrySet().iterator();

        Offer offer;
        Product product;
        int discount;
        while (entryIterator.hasNext()) {
            Map.Entry pair = (Map.Entry)entryIterator.next();
            LOG.debug("Total {} s = {}", pair.getKey(), pair.getValue());
            try {
                product = productRepository.getOne(pair.getKey().toString());
                offer = offerRepository.getOne(product.getOffer());
                discount = ((int) pair.getValue() / offer.getCouponThreshold());
                shoppingCartPrice = product.getUnitPrice();
                shoppingCartTotal = shoppingCartTotal
                        .subtract(shoppingCartPrice.multiply(BigDecimal.valueOf(discount)));
            } catch (EntityNotFoundException e) {
                LOG.debug("Product: {} no offer available.", pair.getKey());
            }
        }
        return shoppingCartTotal;
    }

}

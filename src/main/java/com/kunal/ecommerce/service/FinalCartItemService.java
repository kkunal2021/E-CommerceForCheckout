package com.kunal.ecommerce.service;

import com.kunal.ecommerce.internal.entity.FinalCartItem;
import com.kunal.ecommerce.internal.entity.ItemsCheckout;
import com.kunal.ecommerce.internal.entity.Product;
import com.kunal.ecommerce.internal.repository.FinalCartItemRepository;
import com.kunal.ecommerce.internal.repository.ItemsCheckoutRepository;
import com.kunal.ecommerce.internal.repository.OfferRepository;
import com.kunal.ecommerce.internal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author kunal
 * @project ECommerceCheckout
 */

@Service
public class FinalCartItemService implements  ItemsCheckoutService{

    private static final Logger LOG = LoggerFactory.getLogger(FinalCartItemService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private FinalCartItemRepository finalCartItemRepository;

    @Autowired
    private ItemsCheckoutRepository itemsCheckoutRepository;

    @Override
    public void shoppingCartCheckedOutItems() {
        Product product;
        List<FinalCartItem> trolleyItem = finalCartItemRepository.findAllOrderByOfferPriceAsc();
        for (FinalCartItem item : trolleyItem) {
            Optional<Product> optionalProduct = productRepository.findById(item.getWatchId());
            if (optionalProduct.isPresent()) {
                product = optionalProduct.get();
                itemsCheckoutRepository.save(new ItemsCheckout(item.getWatchId(), item.getWatchName(), product.getUnitPrice(), 0, item.getOffer()));
            }
        }
    }

    @Override
    public BigDecimal shoppingCartGetCheckedOutTotal() {
        BigDecimal checkoutTotal = BigDecimal.valueOf(0);
        List<ItemsCheckout> checkoutItems = itemsCheckoutRepository.findAll();
        for (ItemsCheckout checkoutItem: checkoutItems) {

            checkoutTotal = checkoutTotal.add(BigDecimal.valueOf
                    ((long) checkoutItem.getQuantity() - checkoutItem.getDiscount()).multiply(checkoutItem.getUnitPrice()));
        }
        itemsCheckoutRepository.deleteAll();
        return checkoutTotal;
    }
}

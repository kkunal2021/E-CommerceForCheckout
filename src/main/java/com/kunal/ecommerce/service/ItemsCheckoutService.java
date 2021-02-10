package com.kunal.ecommerce.service;

import java.math.BigDecimal;

/**
 * @author kunal
 * @project ECommerceCheckout
 */


public interface ItemsCheckoutService {

    void shoppingCartCheckedOutItems();

    BigDecimal shoppingCartGetCheckedOutTotal();
}

package com.kunal.ecommerce.service;

import com.kunal.ecommerce.internal.entity.FinalCartItem;
import com.kunal.ecommerce.internal.entity.ItemsCheckout;
import com.kunal.ecommerce.internal.entity.Product;
import com.kunal.ecommerce.internal.repository.FinalCartItemRepository;
import com.kunal.ecommerce.internal.repository.ItemsCheckoutRepository;
import com.kunal.ecommerce.internal.repository.OfferRepository;
import com.kunal.ecommerce.internal.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class FinalCartItemServiceTest {

    @Mock
    private ProductRepository mockProductRepository;
    @Mock
    private OfferRepository mockOfferRepository;
    @Mock
    private FinalCartItemRepository mockFinalCartItemRepository;
    @Mock
    private ItemsCheckoutRepository mockItemsCheckoutRepository;

    @InjectMocks
    private FinalCartItemService finalCartItemServiceUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void testShoppingCartCheckedOutItems() {
        final List<FinalCartItem> finalCartItems = Arrays.asList(new FinalCartItem("001", "Rolex", new BigDecimal("100.00"), 6, ""));
        when(mockFinalCartItemRepository.findAllOrderByOfferPriceAsc()).thenReturn(finalCartItems);
        final Optional<Product> product = Optional.of(new Product("001", "Rolex", "", new BigDecimal("100.00"), 6));
        when(mockProductRepository.findById("001")).thenReturn(product);
        final ItemsCheckout itemsCheckout = new ItemsCheckout("001", "Rolex", new BigDecimal("100.00"), 6, "0", 1);
        when(mockItemsCheckoutRepository.save(new ItemsCheckout("001", "Rolex", new BigDecimal("100.00"), 6, "0", 1))).thenReturn(itemsCheckout);
        finalCartItemServiceUnderTest.shoppingCartCheckedOutItems();
    }

    @Test
    void testShoppingCartCheckedOutItems_ProductRepositoryReturnsNull() {
        final List<FinalCartItem> finalCartItems = Arrays.asList(new FinalCartItem("001", "Rolex", new BigDecimal("100.00"), 6, ""));
        when(mockFinalCartItemRepository.findAllOrderByOfferPriceAsc()).thenReturn(finalCartItems);
        when(mockProductRepository.findById("id")).thenReturn(Optional.empty());
        final ItemsCheckout itemsCheckout = new ItemsCheckout("001", "Rolex", new BigDecimal("100.00"), 6, "0", 1);
        when(mockItemsCheckoutRepository.save(new ItemsCheckout("001", "Rolex", new BigDecimal("100.00"), 6, "0", 1))).thenReturn(itemsCheckout);
        finalCartItemServiceUnderTest.shoppingCartCheckedOutItems();
    }

    @Test
    void testShoppingCartGetCheckedOutTotal() {
        final List<ItemsCheckout> itemsCheckouts = Arrays.asList(new ItemsCheckout("001", "Rolex", new BigDecimal("100.00"), 6, "0", 1));
        when(mockItemsCheckoutRepository.findAll()).thenReturn(itemsCheckouts);
        final BigDecimal result = finalCartItemServiceUnderTest.shoppingCartGetCheckedOutTotal();
        assertThat(result).isNotEqualTo(new BigDecimal("100.00"));
        verify(mockItemsCheckoutRepository).deleteAll();
    }
}

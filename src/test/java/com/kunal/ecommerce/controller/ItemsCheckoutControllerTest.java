package com.kunal.ecommerce.controller;

import com.kunal.ecommerce.internal.entity.Offer;
import com.kunal.ecommerce.internal.entity.Product;
import com.kunal.ecommerce.internal.repository.OfferRepository;
import com.kunal.ecommerce.internal.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ItemsCheckoutController.class)
class ItemsCheckoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository mockProductRepository;
    @MockBean
    private OfferRepository mockOfferRepository;

    @Test
    void testCheckoutTotal() throws Exception {
        final Product product = new Product("001", "Rolex", "0", BigDecimal.valueOf(100), 6);
        when(mockProductRepository.getOne("Rolex")).thenReturn(product);
        final MockHttpServletResponse response = mockMvc.perform(get("/checkout/itemsList")
                .param("items", "")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotEqualTo("");
    }

    @Test
    void testCheckoutTotal_ProductRepositoryReturnsNull() throws Exception {
        when(mockProductRepository.getOne("Rolex")).thenReturn(null);
        final MockHttpServletResponse response = mockMvc.perform(get("/checkout/itemsList")
                .param("items", "")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotEqualTo("");
    }

    @Test
    void testCheckoutWithOffers() throws Exception {
        final Product product = new Product("001", "Rolex", "0", BigDecimal.valueOf(100), 6);

        when(mockProductRepository.getOne("Rolex")).thenReturn(product);
        when(mockOfferRepository.getOne("code")).thenReturn(new Offer("code", 0));

        final MockHttpServletResponse response = mockMvc.perform(get("/checkout/itemsListOffers")
                .header("Offer-Code", "6")
                .param("items", "")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotEqualTo("");
    }
}

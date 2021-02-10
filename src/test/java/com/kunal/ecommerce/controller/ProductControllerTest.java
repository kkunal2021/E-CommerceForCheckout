package com.kunal.ecommerce.controller;

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
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository mockProductRepository;
    @MockBean
    private OfferRepository mockOfferRepository;

    @Test
    void testGetProduct() throws Exception {
        final Product product = new Product("001", "Rolex", "0", new BigDecimal("100.00"), 0);
        when(mockProductRepository.getOne("Rolex")).thenReturn(product);
        final MockHttpServletResponse response = mockMvc.perform(get("/product")
                .param("name", "Rolex")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotEqualTo("Rolex");
    }
}

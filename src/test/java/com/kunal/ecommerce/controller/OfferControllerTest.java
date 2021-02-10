package com.kunal.ecommerce.controller;

import com.kunal.ecommerce.internal.entity.Offer;
import com.kunal.ecommerce.internal.repository.OfferRepository;
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

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OfferController.class)
class OfferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OfferRepository mockOfferRepository;

    @Test
    void testGetProduct() throws Exception {
        when(mockOfferRepository.getOne("code")).thenReturn(new Offer("6", 0));
        final MockHttpServletResponse response = mockMvc.perform(get("/offer")
                .param("code", "6")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("");
    }

    @Test
    void testGetProduct_OfferRepositoryReturnsNull() throws Exception {
        when(mockOfferRepository.getOne("code")).thenReturn(null);
        final MockHttpServletResponse response = mockMvc.perform(get("/offer")
                .param("code", "6")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("");
    }

    @Test
    void testUpdateProduct() throws Exception {
        when(mockOfferRepository.save(new Offer("6", 0))).thenReturn(new Offer("7", 0));
        when(mockOfferRepository.findAll()).thenReturn(Arrays.asList(new Offer("7", 0)));

        final MockHttpServletResponse response = mockMvc.perform(post("/offer/addOffers")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getContentAsString()).isEqualTo("");
    }
}

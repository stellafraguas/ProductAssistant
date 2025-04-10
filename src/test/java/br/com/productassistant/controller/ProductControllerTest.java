package br.com.productassistant.controller;

import br.com.productassistant.dto.response.ProductResponseDTO;
import br.com.productassistant.dto.request.NewProductRequestDTO;
import br.com.productassistant.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@Import(ProductControllerTest.Config.class)
class ProductControllerTest {

    @TestConfiguration
    static class Config {
        @Bean
        public ProductService productService() {
            return mock(ProductService.class);
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private final String endpointURI = "/api/products";

    private ProductResponseDTO validResponse() {
        return ProductResponseDTO.builder()
                .name("Test Product")
                .description("Description")
                .categoryId(1L)
                .categoryDisplayName("Category")
                .price(new BigDecimal("19.99"))
                .active(true)
                .createdBy("tester")
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    void listAll() throws Exception {
        when(productService.getAllProducts()).thenReturn(List.of(validResponse()));
        mockMvc.perform(get(endpointURI))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Product"));
    }

    @Test
    void create() throws Exception {
        NewProductRequestDTO request = validNewRequest();
        mockMvc.perform(post(endpointURI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void createBadRequest() throws Exception {
        NewProductRequestDTO request = NewProductRequestDTO.builder()
                .description("Description")
                .categoryId(1L)
                .categoryDisplayName("Category")
                .price(new BigDecimal("19.99"))
                .active(true)
                .createdBy("tester")
                .createdAt(LocalDateTime.now())
                .build();
        mockMvc.perform(post(endpointURI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    private NewProductRequestDTO validNewRequest() {
        return NewProductRequestDTO.builder()
                .name("Test Product")
                .description("Description")
                .categoryId(1L)
                .categoryDisplayName("Category")
                .price(new BigDecimal("19.99"))
                .active(true)
                .createdBy("tester")
                .createdAt(LocalDateTime.now())
                .build();
    }

}

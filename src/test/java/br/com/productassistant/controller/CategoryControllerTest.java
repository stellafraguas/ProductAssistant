package br.com.productassistant.controller;

import br.com.productassistant.config.SecurityConfig;
import br.com.productassistant.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
@Import({CategoryControllerTest.Config.class, SecurityConfig.class})
class CategoryControllerTest {

    @TestConfiguration
    static class Config {
        @Bean
        public CategoryService categoryService() {
            return mock(CategoryService.class);
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllShouldReturn200() throws Exception {
        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk());
    }
}

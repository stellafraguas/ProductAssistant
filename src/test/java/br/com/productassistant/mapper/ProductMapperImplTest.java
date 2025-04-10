package br.com.productassistant.mapper;

import br.com.productassistant.adapter.resolver.CategoryResolver;
import br.com.productassistant.dto.response.ProductResponseDTO;
import br.com.productassistant.entity.Category;
import br.com.productassistant.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ProductMapperImplTest {

    private CategoryResolver resolver;
    private ProductMapperImpl mapper;

    @BeforeEach
    void init() {
        resolver = mock(CategoryResolver.class);
        mapper = new ProductMapperImpl(resolver);
    }

    @Test
    void productToProductResponseDto_ok() {
        Category c = new Category();
        c.setId(1L);
        c.setName("Test");

        Product p = new Product();
        p.setName("Test");
        p.setDescription("Description");
        p.setCategory(c);
        p.setPrice(new BigDecimal("29.90"));
        p.setActive(true);
        p.setCreatedBy("user");
        p.setLastUpdatedBy("editor");
        p.setCreatedAt(LocalDateTime.of(2025, 4, 7, 10, 0));
        p.setLastUpdatedAt(LocalDateTime.of(2025, 4, 7, 15, 0));
        p.setId(1L);

        when(resolver.resolveDisplayNameById(1L)).thenReturn("Category");

        ProductResponseDTO dto = mapper.productToProductResponseDTO(p);

        assertThat(dto.getName()).isEqualTo("Test");
        assertThat(dto.getDescription()).isEqualTo("Description");
        assertThat(dto.getPrice()).isEqualTo(new BigDecimal("29.90"));
        assertThat(dto.getActive()).isTrue();
        assertThat(dto.getCreatedBy()).isEqualTo("user");
        assertThat(dto.getLastUpdatedBy()).isEqualTo("editor");
        assertThat(dto.getCreatedAt()).isEqualTo(LocalDateTime.of(2025, 4, 7, 10, 0));
        assertThat(dto.getLastUpdatedAt()).isEqualTo(LocalDateTime.of(2025, 4, 7, 15, 0));
        assertThat(dto.getCategoryDisplayName()).isEqualTo("Category");
    }

}

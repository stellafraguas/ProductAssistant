package br.com.productassistant.mapper;

import br.com.productassistant.adapter.resolver.CategoryResolver;
import br.com.productassistant.dto.response.ProductResponseDTO;
import br.com.productassistant.dto.request.NewProductRequestDTO;
import br.com.productassistant.entity.Category;
import br.com.productassistant.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductMapperImplTest {

    private CategoryResolver categoryResolver;
    private ProductMapperImpl productMapper;

    @BeforeEach
    void init() {
        categoryResolver = mock(CategoryResolver.class);
        productMapper = new ProductMapperImpl(categoryResolver);
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

        when(categoryResolver.resolveDisplayNameById(1L)).thenReturn("Category");

        ProductResponseDTO dto = productMapper.productToProductResponseDTO(p);

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

    @Test
    void shouldConvertNewProductRequestDTOToProduct() {
        var category = new Category(1L, "Books", null);
        var now = LocalDateTime.now();

        var dto = NewProductRequestDTO.builder()
                .name("Clean Code")
                .description("A Handbook of Agile Software Craftsmanship")
                .categoryId(1L)
                .price(new BigDecimal("99.90"))
                .active(true)
                .createdBy("admin")
                .createdAt(now)
                .build();

        when(categoryResolver.resolveCategoryById(1L)).thenReturn(category);

        Product product = productMapper.newProductRequestDTOToProduct(dto);

        assertThat(product.getName()).isEqualTo("Clean Code");
        assertThat(product.getDescription()).isEqualTo("A Handbook of Agile Software Craftsmanship");
        assertThat(product.getCategory()).isEqualTo(category);
        assertThat(product.getPrice()).isEqualByComparingTo("99.90");
        assertThat(product.isActive()).isTrue();
        assertThat(product.getCreatedBy()).isEqualTo("admin");
        assertThat(product.getCreatedAt()).isEqualTo(now);
    }

}

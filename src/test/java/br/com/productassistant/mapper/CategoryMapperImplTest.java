package br.com.productassistant.mapper;

import br.com.productassistant.dto.response.CategoryResponseDTO;
import br.com.productassistant.entity.CategoryView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryMapperImplTest {

    private CategoryMapperImpl mapper;

    @BeforeEach
    void setUp() {
        mapper = new CategoryMapperImpl();
    }

    @Test
    void shouldMapCategoryViewToCategoryResponseDTO() {
        CategoryView categoryView = new CategoryView();
        categoryView.setId(1L);
        categoryView.setDisplayName("Books");

        CategoryResponseDTO dto = mapper.toDTO(categoryView);

        assertEquals(1L, dto.getCategoryId());
        assertEquals("Books", dto.getCategoryDisplayName());
    }
}

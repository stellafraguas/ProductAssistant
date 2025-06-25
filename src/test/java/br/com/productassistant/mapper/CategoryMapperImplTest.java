package br.com.productassistant.mapper;

import br.com.productassistant.dto.response.CategoryResponseDTO;
import br.com.productassistant.entity.Category;
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
    void shouldMapCategoryToCategoryResponseDTO() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Books");

        CategoryResponseDTO dto = mapper.toDTO(category);

        assertEquals(1L, dto.getCategoryId());
        assertEquals("Books", dto.getCategoryDisplayName());
    }
}

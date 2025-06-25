package br.com.productassistant.service;

import br.com.productassistant.dto.response.CategoryResponseDTO;
import br.com.productassistant.entity.Category;
import br.com.productassistant.mapper.CategoryMapper;
import br.com.productassistant.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceImplTest {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        categoryRepository = mock(CategoryRepository.class);
        categoryMapper = mock(CategoryMapper.class);
        categoryService = new CategoryServiceImpl(categoryRepository, categoryMapper);
    }

    @Test
    void shouldReturnAllCategories() {
        Category category = new Category();
        category.setId(1L);

        CategoryResponseDTO dto = CategoryResponseDTO.builder()
                .categoryId(1L)
                .categoryDisplayName("Electronics")
                .build();

        when(categoryRepository.findAll()).thenReturn(List.of(category));
        when(categoryMapper.toDTO(category)).thenReturn(dto);

        List<CategoryResponseDTO> result = categoryService.getAllCategories();

        assertEquals(1, result.size());
        assertEquals(dto, result.getFirst());
        verify(categoryRepository, times(1)).findAll();
        verify(categoryMapper, times(1)).toDTO(category);
    }
}

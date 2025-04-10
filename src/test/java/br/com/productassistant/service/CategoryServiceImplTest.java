package br.com.productassistant.service;

import br.com.productassistant.dto.response.CategoryResponseDTO;
import br.com.productassistant.entity.CategoryView;
import br.com.productassistant.mapper.CategoryMapper;
import br.com.productassistant.repository.CategoryViewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceImplTest {

    private CategoryViewRepository categoryViewRepository;
    private CategoryMapper categoryMapper;
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        categoryViewRepository = mock(CategoryViewRepository.class);
        categoryMapper = mock(CategoryMapper.class);
        categoryService = new CategoryServiceImpl(categoryViewRepository, categoryMapper);
    }

    @Test
    void shouldReturnAllCategories() {
        CategoryView categoryView = new CategoryView();
        categoryView.setId(1L);
        categoryView.setDisplayName("Electronics");

        CategoryResponseDTO dto = CategoryResponseDTO.builder()
                .categoryId(1L)
                .categoryDisplayName("Electronics")
                .build();

        when(categoryViewRepository.findAll()).thenReturn(List.of(categoryView));
        when(categoryMapper.toDTO(categoryView)).thenReturn(dto);

        List<CategoryResponseDTO> result = categoryService.getAllCategories();

        assertEquals(1, result.size());
        assertEquals(dto, result.getFirst());
        verify(categoryViewRepository, times(1)).findAll();
        verify(categoryMapper, times(1)).toDTO(categoryView);
    }
}

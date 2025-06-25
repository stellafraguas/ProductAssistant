package br.com.productassistant.mapper;

import br.com.productassistant.dto.response.CategoryResponseDTO;
import br.com.productassistant.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper{

    @Override
    public CategoryResponseDTO toDTO(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("CategoryView must not be null");
        }
        if (category.getId() == null || category.getDisplayName() == null) {
            throw new IllegalArgumentException("CategoryView must have both ID and display name");
        }
        return CategoryResponseDTO.builder()
                .categoryId(category.getId())
                .categoryDisplayName(category.getDisplayName())
                .build();
    }
}

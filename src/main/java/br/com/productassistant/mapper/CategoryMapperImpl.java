package br.com.productassistant.mapper;

import br.com.productassistant.dto.response.CategoryResponseDTO;
import br.com.productassistant.entity.CategoryView;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper{

    @Override
    public CategoryResponseDTO toDTO(CategoryView category) {
        return CategoryResponseDTO.builder()
                .categoryId(category.getId())
                .categoryDisplayName(category.getDisplayName()).build();
    }
}

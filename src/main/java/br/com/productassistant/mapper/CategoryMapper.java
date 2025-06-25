package br.com.productassistant.mapper;


import br.com.productassistant.dto.response.CategoryResponseDTO;
import br.com.productassistant.entity.Category;

public interface CategoryMapper {

    CategoryResponseDTO toDTO(Category category);

}

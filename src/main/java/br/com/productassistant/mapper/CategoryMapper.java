package br.com.productassistant.mapper;


import br.com.productassistant.dto.response.CategoryResponseDTO;
import br.com.productassistant.entity.CategoryView;

public interface CategoryMapper {

    CategoryResponseDTO toDTO(CategoryView category);

}

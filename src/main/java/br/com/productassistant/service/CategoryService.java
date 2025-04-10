package br.com.productassistant.service;

import br.com.productassistant.dto.response.CategoryResponseDTO;
import java.util.List;

public interface CategoryService {

    List<CategoryResponseDTO> getAllCategories();

}

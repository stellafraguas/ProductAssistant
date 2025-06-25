package br.com.productassistant.service;

import br.com.productassistant.dto.response.CategoryResponseDTO;
import br.com.productassistant.entity.Category;
import br.com.productassistant.mapper.CategoryMapper;
import br.com.productassistant.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        try {
            List<Category> allCategories = categoryRepository.findAll();
            return allCategories.stream()
                    .map(categoryMapper::toDTO)
                    .toList();
        } catch (DataAccessException e) {
            LOGGER.error("Database error when retrieving categories: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve categories", e);
        } catch (RuntimeException e) {
            LOGGER.error("Unexpected error when mapping categories: {}", e.getMessage(), e);
            throw e;
        }
    }
}

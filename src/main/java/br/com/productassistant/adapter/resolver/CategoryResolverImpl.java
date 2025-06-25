package br.com.productassistant.adapter.resolver;

import br.com.productassistant.entity.Category;
import br.com.productassistant.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryResolverImpl implements CategoryResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryResolverImpl.class);

    private final CategoryRepository categoryRepository;

    @Override
    public Category resolveCategoryById(Long categoryId) {
        if (categoryId == null) {
            throw new IllegalArgumentException("Category ID must not be null");
        }
        try {
            Category category = categoryRepository.findById(categoryId).orElseThrow(
                    () -> new EntityNotFoundException("Category not found for ID: " + categoryId));
            LOGGER.debug("Resolved category for ID {}: {}", categoryId, category.getName());
            return category;
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            LOGGER.warn("Failed to resolve category: {}", e.getMessage());
            throw e;
        } catch (DataAccessException e) {
            LOGGER.error("Database error when resolving category ID {}: {}", categoryId, e.getMessage());
            throw new RuntimeException("Internal error while resolving category", e);
        }
    }

}

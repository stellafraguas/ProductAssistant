package br.com.productassistant.adapter.resolver;

import br.com.productassistant.entity.Category;
import br.com.productassistant.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryResolverImpl implements CategoryResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryResolverImpl.class);

    private final CategoryRepository categoryRepository;

    @Override
    public Category resolveCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new EntityNotFoundException("Category not found for ID: " + categoryId));
        LOGGER.debug("Resolved category for ID {}: {}", categoryId, category.getName());
        return category;
    }

}

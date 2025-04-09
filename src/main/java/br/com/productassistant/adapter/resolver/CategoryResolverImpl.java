package br.com.productassistant.adapter.resolver;

import br.com.productassistant.entity.CategoryView;
import br.com.productassistant.repository.CategoryViewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CategoryResolverImpl implements CategoryResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryResolverImpl.class);

    private final CategoryViewRepository categoryViewRepository;

    public CategoryResolverImpl(CategoryViewRepository categoryViewRepository) {
        this.categoryViewRepository = categoryViewRepository;
    }

    public String resolveDisplayNameById(Long categoryId) {
        CategoryView categoryView = categoryViewRepository.findById(categoryId).orElseThrow(
                () -> new EntityNotFoundException("Category view not found for ID: " + categoryId)
        );
        LOGGER.debug("Resolved display name for category ID {}: {}", categoryId, categoryView.getDisplayName());
        return categoryView.getDisplayName();
    }

}

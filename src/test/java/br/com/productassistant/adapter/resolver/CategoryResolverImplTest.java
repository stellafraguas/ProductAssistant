package br.com.productassistant.adapter.resolver;

import br.com.productassistant.entity.Category;
import br.com.productassistant.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryResolverImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryResolverImpl resolver;

    @Test
    void resolveCategory_returnsCategory() {
        Category category = new Category();
        category.setName("Fruits");

        when(categoryRepository.findById(3L)).thenReturn(Optional.of(category));

        Category result = resolver.resolveCategoryById(3L);

        assertThat(result.getName()).isEqualTo("Fruits");
    }

    @Test
    void resolveCategory_throwsIfNotFound() {
        when(categoryRepository.findById(4L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> resolver.resolveCategoryById(4L));
    }

}

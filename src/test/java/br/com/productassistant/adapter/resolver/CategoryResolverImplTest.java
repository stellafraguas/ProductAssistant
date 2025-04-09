package br.com.productassistant.adapter.resolver;

import br.com.productassistant.entity.CategoryView;
import br.com.productassistant.repository.CategoryViewRepository;
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
    private CategoryViewRepository categoryViewRepository;

    @InjectMocks
    private CategoryResolverImpl resolver;

    @Test
    void resolveDisplayName_returnsDisplayName() {
        CategoryView view = new CategoryView();
        view.setDisplayName("Test Category");

        when(categoryViewRepository.findById(1L)).thenReturn(Optional.of(view));

        String result = resolver.resolveDisplayNameById(1L);

        assertThat(result).isEqualTo("Test Category");
    }

    @Test
    void resolveDisplayName_throwsIfNotFound() {
        when(categoryViewRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> resolver.resolveDisplayNameById(2L));
    }

}

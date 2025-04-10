package br.com.productassistant.service;

import br.com.productassistant.adapter.resolver.CategoryResolver;
import br.com.productassistant.dto.request.NewProductRequestDTO;
import br.com.productassistant.dto.request.UpdateProductRequestDTO;
import br.com.productassistant.dto.response.ProductResponseDTO;
import br.com.productassistant.entity.Product;
import br.com.productassistant.mapper.ProductMapper;
import br.com.productassistant.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository repository;

    @Mock
    ProductMapper mapper;

    @Mock
    CategoryResolver categoryResolver;

    @InjectMocks
    ProductServiceImpl service;


    ProductResponseDTO validResponseDTO() {
        return ProductResponseDTO.builder()
                .name("Test")
                .description("Product description")
                .categoryId(1L)
                .categoryDisplayName("Category")
                .price(BigDecimal.valueOf(123.45))
                .active(true)
                .createdBy("tester")
                .createdAt(LocalDateTime.now())
                .build();
    }

    NewProductRequestDTO validNewProductRequest() {
        return NewProductRequestDTO.builder()
                .name("Test")
                .description("Product description")
                .categoryId(1L)
                .categoryDisplayName("Category")
                .price(BigDecimal.valueOf(123.45))
                .active(true)
                .createdBy("tester")
                .createdAt(LocalDateTime.now())
                .build();
    }

    UpdateProductRequestDTO validUpdateProductRequest() {
        return UpdateProductRequestDTO.builder()
                .name("Test Updated")
                .description("Updated description")
                .categoryId(2L)
                .categoryDisplayName("New Category")
                .price(BigDecimal.valueOf(543.21))
                .active(false)
                .lastUpdatedBy("tester2")
                .lastUpdatedAt(LocalDateTime.now().minusDays(1))
                .build();
    }

    Product validEntity() {
        Product p = new Product();
        p.setId(1L);
        p.setName("Test");
        return p;
    }

    @Test
    void getAllProducts_shouldReturnMappedDtos() {
        Product product = validEntity();
        ProductResponseDTO dto = validResponseDTO();

        when(repository.findAll()).thenReturn(singletonList(product));
        when(mapper.productToProductResponseDTO(product)).thenReturn(dto);

        List<ProductResponseDTO> result = service.getAllProducts();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Test");
    }

    @Test
    void createProduct_shouldPersistMappedEntity() {
        NewProductRequestDTO dto = validNewProductRequest();

        Product entity = new Product();
        entity.setName(dto.getName());

        when(mapper.newProductRequestDTOToProduct(dto)).thenReturn(entity);
        when(repository.saveAndFlush(entity)).thenReturn(entity); // opcional, mas ajuda no fluxo

        service.createProduct(dto);

        verify(repository).saveAndFlush(entity);
    }


    @Test
    void updateProduct_shouldPersistWithGivenId() {
        UpdateProductRequestDTO dto = validUpdateProductRequest();

        Product existing = new Product();
        existing.setId(1L);
        existing.setName("Original");

        when(repository.findById(1L)).thenReturn(Optional.of(existing));

        service.updateProduct(1L, dto);

        verify(repository).saveAndFlush(existing);
    }

    @Test
    void deleteProduct_shouldDeleteIfExists() {
        when(repository.existsById(1L)).thenReturn(true);

        service.deleteProductById(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void deleteProduct_shouldThrowIfNotExists() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> service.deleteProductById(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Product with id 1 not found");

        verify(repository, never()).deleteById(any());
    }
}

package br.com.productassistant.service;

import br.com.productassistant.adapter.resolver.CategoryResolver;
import br.com.productassistant.dto.response.ProductResponseDTO;
import br.com.productassistant.entity.Product;
import br.com.productassistant.mapper.ProductMapper;
import br.com.productassistant.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository repository;

    @Mock
    ProductMapper mapper;

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

    Product validEntity() {
        Product p = new Product();
        p.setId(1L);
        p.setName("Test");
        return p;
    }

    @Test
    void getAllProducts_shouldReturnMappedDTOs() {
        Product product = validEntity();
        ProductResponseDTO dto = validResponseDTO();

        when(repository.findAll()).thenReturn(singletonList(product));
        when(mapper.productToProductResponseDTO(product)).thenReturn(dto);

        List<ProductResponseDTO> result = service.getAllProducts();

        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getName()).isEqualTo("Test");
    }

}

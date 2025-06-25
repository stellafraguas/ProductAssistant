package br.com.productassistant.mapper;

import br.com.productassistant.adapter.resolver.CategoryResolver;
import br.com.productassistant.dto.response.ProductResponseDTO;
import br.com.productassistant.dto.request.NewProductRequestDTO;
import br.com.productassistant.entity.Category;
import br.com.productassistant.entity.Product;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapperImpl implements ProductMapper{

    private final CategoryResolver categoryResolver;

    @Override
    public ProductResponseDTO productToProductResponseDTO(Product product){
        if (product == null) {
            throw new IllegalArgumentException("Product must not be null");
        }
        Category category = product.getCategory();
        if (category == null) {
            throw new IllegalArgumentException("Product must have a category");
        }
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .categoryId(category.getId())
                .categoryDisplayName(category.getDisplayName())
                .price(product.getPrice())
                .active(product.isActive())
                .createdBy(product.getCreatedBy())
                .lastUpdatedBy(product.getLastUpdatedBy())
                .createdAt(product.getCreatedAt())
                .lastUpdatedAt(product.getLastUpdatedAt())
                .build();
    }

    @Override
    public Product newProductRequestDTOToProduct(NewProductRequestDTO newProductRequestDTO) {
        if (newProductRequestDTO == null) {
            throw new IllegalArgumentException("NewProductRequestDTO must not be null");
        }
        if (newProductRequestDTO.getCategoryId() == null) {
            throw new IllegalArgumentException("CategoryId must not be null");
        }
        Product product = new Product();
        try {
            product.setCategory(categoryResolver.resolveCategoryById(newProductRequestDTO.getCategoryId()));
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Category not found for ID: " + newProductRequestDTO.getCategoryId());
        } catch (DataAccessException e) {
            throw new RuntimeException("Database error while resolving category", e);
        }
        product.setName(newProductRequestDTO.getName());
        product.setDescription(newProductRequestDTO.getDescription());
        product.setPrice(newProductRequestDTO.getPrice());
        product.setActive(newProductRequestDTO.getActive());
        product.setCreatedBy(newProductRequestDTO.getCreatedBy());
        product.setCreatedAt(newProductRequestDTO.getCreatedAt());
        return product;
    }

}

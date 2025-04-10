package br.com.productassistant.mapper;

import br.com.productassistant.adapter.resolver.CategoryResolver;
import br.com.productassistant.dto.response.ProductResponseDTO;
import br.com.productassistant.dto.request.NewProductRequestDTO;
import br.com.productassistant.entity.Category;
import br.com.productassistant.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperImpl implements ProductMapper{

    private final CategoryResolver categoryResolver;

    public ProductMapperImpl(CategoryResolver categoryResolver) {
        this.categoryResolver = categoryResolver;
    }

    public ProductResponseDTO productToProductResponseDTO(Product product){
        Category category = product.getCategory();
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .categoryId(category.getId())
                .categoryDisplayName(categoryResolver.resolveDisplayNameById(product.getCategory().getId()))
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
        Product product = new Product();
        product.setName(newProductRequestDTO.getName());
        product.setDescription(newProductRequestDTO.getDescription());
        product.setCategory(categoryResolver.resolveCategoryById(newProductRequestDTO.getCategoryId()));
        product.setPrice(newProductRequestDTO.getPrice());
        product.setActive(newProductRequestDTO.getActive());
        product.setCreatedBy(newProductRequestDTO.getCreatedBy());
        product.setCreatedAt(newProductRequestDTO.getCreatedAt());
        return product;
    }

}

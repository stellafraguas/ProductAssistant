package br.com.productassistant.service;

import br.com.productassistant.adapter.resolver.CategoryResolver;
import br.com.productassistant.dto.request.UpdateProductRequestDTO;
import br.com.productassistant.dto.response.ProductResponseDTO;
import br.com.productassistant.dto.request.NewProductRequestDTO;
import br.com.productassistant.entity.Product;
import br.com.productassistant.mapper.ProductMapper;
import br.com.productassistant.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryResolver categoryResolver;

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        LOGGER.info("Retrieving all products");
        try {
            List<Product> allProducts = productRepository.findAll();
            LOGGER.info("{} products retrieved", allProducts.size());
            return allProducts.stream().map(productMapper::productToProductResponseDTO).toList();
        } catch (DataAccessException e) {
            LOGGER.error("Database error when retrieving products: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve products", e);
        } catch (RuntimeException e) {
            LOGGER.error("Unexpected error when mapping products: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void createProduct(NewProductRequestDTO newProductRequestDTO) {
        if (newProductRequestDTO == null) {
            throw new IllegalArgumentException("NewProductRequestDTO must not be null");
        }
        try {
            Product product = productMapper.newProductRequestDTOToProduct(newProductRequestDTO);
            Product persistedProduct = persistProduct(product);
            LOGGER.info("Product created: {}", persistedProduct.getId());
        } catch (EntityNotFoundException e) {
            LOGGER.warn("Category not found: {}", e.getMessage());
            throw e;
        } catch (DataAccessException e) {
            LOGGER.error("Database error while creating product: {}", e.getMessage(), e);
            throw e;
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Invalid product data: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            LOGGER.error("Unexpected error while creating product: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create product", e);
        }
    }

    @Override
    public void updateProduct(Long productId, UpdateProductRequestDTO updateProductRequestDTO) {
        if (productId == null) {
            throw new IllegalArgumentException("Product ID must not be null");
        }
        if (updateProductRequestDTO == null) {
            throw new IllegalArgumentException("UpdateProductRequestDTO must not be null");
        }
        LOGGER.info("Updating product with id {}", productId);
        try {
            Product product = updateProductFromProductRequestDTO(productId, updateProductRequestDTO);
            persistProduct(product);
            LOGGER.info("Product updated: {}", productId);
        } catch (EntityNotFoundException e) {
            LOGGER.warn("Product not found for update: {}", e.getMessage());
            throw e;
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Invalid update data: {}", e.getMessage());
            throw e;
        } catch (DataAccessException e) {
            LOGGER.error("Database error while updating product: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            LOGGER.error("Unexpected error while updating product: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to update product", e);
        }
    }

    private Product persistProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product must not be null");
        }
        LOGGER.debug("Persisting product name: {}", product.getName());
        try {
            productRepository.saveAndFlush(product);
        } catch (DataAccessException e) {
            LOGGER.error("Database error while persisting product: {}", e.getMessage(), e);
            throw e;
        }
        return product;
    }

    private Product updateProductFromProductRequestDTO(Long productId, UpdateProductRequestDTO updateProductRequestDTO){
        if (updateProductRequestDTO == null) {
            throw new IllegalArgumentException("UpdateProductRequestDTO must not be null");
        }
        LOGGER.debug("Updating product with id {} with user input", productId);
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new EntityNotFoundException("Product not found for ID: " + productId)
        );
        product.setName(updateProductRequestDTO.getName());
        product.setDescription(updateProductRequestDTO.getDescription());
        Long categoryId = updateProductRequestDTO.getCategoryId();
        if (categoryId == null) {
            throw new IllegalArgumentException("CategoryId must not be null");
        }
        product.setCategory(categoryResolver.resolveCategoryById(categoryId));
        product.setPrice(updateProductRequestDTO.getPrice());
        product.setActive(updateProductRequestDTO.getActive());
        product.setLastUpdatedBy(updateProductRequestDTO.getLastUpdatedBy());
        product.setLastUpdatedAt(updateProductRequestDTO.getLastUpdatedAt());
        return product;
    }

    @Override
    public void deleteProductById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Product ID must not be null");
        }
        LOGGER.info("Attempting to delete product with id: {}", id);
        try {
            if (!productRepository.existsById(id)) {
                LOGGER.warn("Product not found for deletion: {}", id);
                throw new EntityNotFoundException("Product with id " + id + " not found");
            }
            productRepository.deleteById(id);
            LOGGER.info("Product deleted: {}", id);
        } catch (DataAccessException e) {
            LOGGER.error("Database error during product deletion: {}", e.getMessage(), e);
            throw e;
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Unexpected error during product deletion: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to delete product", e);
        }
    }
}

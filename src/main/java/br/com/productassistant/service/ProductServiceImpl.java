package br.com.productassistant.service;

import br.com.productassistant.adapter.resolver.CategoryResolver;
import br.com.productassistant.dto.request.UpdateProductRequestDTO;
import br.com.productassistant.dto.response.ProductResponseDTO;
import br.com.productassistant.dto.request.NewProductRequestDTO;
import br.com.productassistant.entity.Product;
import br.com.productassistant.mapper.ProductMapper;
import br.com.productassistant.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryResolver categoryResolver;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, CategoryResolver categoryResolver) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryResolver = categoryResolver;
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        LOGGER.info("Retrieving all products");
        List<Product> allProducts = productRepository.findAll();
        LOGGER.info("{} products retrieved", allProducts.size());
        return allProducts.stream().map(productMapper::productToProductResponseDTO).toList();
    }

    @Override
    public void createProduct(NewProductRequestDTO newProductRequestDTO) {
        Product product = productMapper.newProductRequestDTOToProduct(newProductRequestDTO);
        Product persistedProduct = persistProduct(product);
        LOGGER.info("Product created: {}", persistedProduct.getId());
    }

    @Override
    public void updateProduct(Long productId, UpdateProductRequestDTO updateProductRequestDTO) {
        LOGGER.info("Updating product with id {}", productId);
        Product product = updateProductFromProductRequestDTO(productId, updateProductRequestDTO);
        persistProduct(product);
        LOGGER.info("Product updated: {}", productId);
    }

    private Product persistProduct(Product product) {
        LOGGER.debug("Persisting product name: {}", product.getName());
        productRepository.saveAndFlush(product);
        return product;
    }

    private Product updateProductFromProductRequestDTO(Long productId, UpdateProductRequestDTO updateProductRequestDTO){
        LOGGER.debug("Updating product with id {} with user input", productId);
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new EntityNotFoundException("Product not found for ID: " + productId)
        );
        product.setName(updateProductRequestDTO.getName());
        product.setDescription(updateProductRequestDTO.getDescription());
        product.setCategory(categoryResolver.resolveCategoryById(updateProductRequestDTO.getCategoryId()));
        product.setPrice(updateProductRequestDTO.getPrice());
        product.setActive(updateProductRequestDTO.getActive());
        product.setLastUpdatedBy(updateProductRequestDTO.getLastUpdatedBy());
        product.setLastUpdatedAt(updateProductRequestDTO.getLastUpdatedAt());
        return product;
    }

}

package br.com.productassistant.service;

import br.com.productassistant.dto.response.ProductResponseDTO;
import br.com.productassistant.dto.request.NewProductRequestDTO;
import br.com.productassistant.entity.Product;
import br.com.productassistant.mapper.ProductMapper;
import br.com.productassistant.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
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

    private Product persistProduct(Product product) {
        LOGGER.debug("Persisting product name: {}", product.getName());
        productRepository.saveAndFlush(product);
        return product;
    }

}

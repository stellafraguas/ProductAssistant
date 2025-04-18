package br.com.productassistant.service;

import br.com.productassistant.dto.request.UpdateProductRequestDTO;
import br.com.productassistant.dto.response.ProductResponseDTO;
import br.com.productassistant.dto.request.NewProductRequestDTO;

import java.util.List;

public interface ProductService {

    List<ProductResponseDTO> getAllProducts();

    void createProduct(NewProductRequestDTO newProductRequestDTO);

    void updateProduct(Long productId, UpdateProductRequestDTO updateProductRequestDTO);

    void deleteProductById(Long id);

}

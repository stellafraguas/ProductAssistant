package br.com.productassistant.mapper;

import br.com.productassistant.dto.response.ProductResponseDTO;
import br.com.productassistant.entity.Product;

public interface ProductMapper {

    ProductResponseDTO productToProductResponseDTO(Product product);

}

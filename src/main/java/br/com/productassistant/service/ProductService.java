package br.com.productassistant.service;

import br.com.productassistant.dto.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    List<ProductResponseDTO> getAllProducts();

}

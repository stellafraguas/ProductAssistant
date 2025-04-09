package br.com.productassistant.controller;

import br.com.productassistant.dto.response.ProductResponseDTO;
import br.com.productassistant.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        LOGGER.info("Retrieving all products");
        List<ProductResponseDTO> allProducts = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(allProducts);
    }
}
package org.globant.restaurant.controller;

import org.globant.restaurant.model.ProductDTO;
import org.globant.restaurant.service.Product.IProductService;
import org.globant.restaurant.service.Product.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController { //TODO: Add bonus track implementation (search by fantasyName).

    IProductService productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    // Create a new product
    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    // Consult products by ID
    @GetMapping("/{uuid}")
    public Optional<?> findByUUID(@PathVariable ProductDTO productDTO) {
        return productService.findByUUID(productDTO);
    }


    @PutMapping("/{uuid}")
    public ResponseEntity<Void> updateProduct(@PathVariable UUID uuid, @RequestBody ProductDTO productDTO) {
        productService.updateByUuid(uuid, productDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID uuid) {
        productService.deleteByUuid(uuid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

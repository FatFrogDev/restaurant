package org.globant.restaurant.controller;

import org.globant.restaurant.model.ProductDTO;
import org.globant.restaurant.service.Product.IProductService;
import org.springframework.http.HttpStatus;
import org.globant.restaurant.service.Product.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;


@RestController
@RequestMapping("/products")
public class ProductController { //TODO: Add bonus track implementation (search by fantasyName).

    private final IProductService productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productDTO));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ProductDTO> getProductByUuid(@PathVariable UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByUuid(uuid));
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


    @GetMapping("/search")
    public ResponseEntity<ProductDTO> findProductByFantasyName(@RequestParam("q")String productFantasyName) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findProductByFantasyName(productFantasyName));
    }
}

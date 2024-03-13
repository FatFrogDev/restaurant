package org.globant.restaurant.controller;

import org.globant.restaurant.commons.constans.endPoints.product.IProductEndPoint;
import org.globant.restaurant.model.ProductDTO;
import org.globant.restaurant.service.Product.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@SuppressWarnings("ALL")
@RestController
@RequestMapping(IProductEndPoint.BASE_URL_PRODUCT)
public class ProductController { //TODO: Add bonus track implementation (search by fantasyName).
    private final IProductService productService;
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }
    // Create a new product
    @PostMapping(IProductEndPoint.CREATE_PRODUCT)
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productDTO));
    }
    @GetMapping(IProductEndPoint.GET_PRODUCT)
    public ResponseEntity<ProductDTO> findProductByUuid(@PathVariable String uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByUuid(uuid));
    }
    @PutMapping(IProductEndPoint.UPDATE_PRODUCT)
    public ResponseEntity updateProduct(@PathVariable String uuid, @RequestBody ProductDTO productDTO) {
        productService.updateByUuid(uuid, productDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(IProductEndPoint.DELETE_PRODUCT)
    public ResponseEntity deleteProduct(@PathVariable String uuid) {
        productService.deleteByUuid(uuid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping(IProductEndPoint.FIND_PRODUCT_NAME)
    public ResponseEntity<ProductDTO> findProductByFantasyName(@RequestParam("q")String productFantasyName) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByFantasyName(productFantasyName));
    }
}
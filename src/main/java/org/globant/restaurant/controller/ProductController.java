package org.globant.restaurant.controller;

import org.globant.restaurant.model.ProductDTO;
import org.globant.restaurant.service.Product.IProductService;
import org.globant.restaurant.service.Product.ProductServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

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
    RestTemplate restTemplate = new RestTemplate();
    @GetMapping("/products-python")
    public ResponseEntity<?> getAllProducts() {
        String url = "http://localhost:8000/restaurant/products/";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.GET, null, Object.class);
        return response;
    }


    @GetMapping("/{idProduct}")
    public ProductDTO getProduct(@PathVariable("idProduct") UUID idProduct){
        return productService.getProductService(idProduct);
    }
    @GetMapping(value = "/all")
    public List<ProductDTO> getAllProduct(){
        return productService.getAllProductService();
    }
    @PutMapping(value = "/{idProduct}")
    public ProductDTO updateProduct(@PathVariable("idProduct") UUID idProduct, @RequestBody ProductDTO productDTO) throws Exception{
        return productService.updateProduct(idProduct, productDTO);
    }

}

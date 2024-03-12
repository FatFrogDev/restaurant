package org.globant.restaurant.controller;

import org.globant.restaurant.commons.constans.endPoints.product.IProductEndPoint;
import org.globant.restaurant.model.ProductDTO;
import org.globant.restaurant.service.Product.IProductService;
import org.globant.restaurant.service.Product.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping(IProductEndPoint.BASE_URL_PRODUCT)
public class ProductController {

    IProductService productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    // Create a new product
    @PostMapping(IProductEndPoint.CREATE_PRODUCT)
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    // Consult products by ID
    @GetMapping(IProductEndPoint.FIND_PRODUCT_UUID)
    public Optional<?> findByUUID(@PathVariable ProductDTO productDTO) {
        return productService.findByUUID(productDTO);
    }


    @GetMapping(IProductEndPoint.GET_PRODUCT)
    public ProductDTO getProduct(@PathVariable("idProduct") UUID idProduct){
        return productService.getProductService(idProduct);
    }
    @GetMapping(IProductEndPoint.GET_ALL_PRODUCT)
    public List<ProductDTO> getAllProduct(){
        return productService.getAllProductService();
    }
    @PutMapping(IProductEndPoint.UPDATE_PRODUCT)
    public ProductDTO updateProduct(@PathVariable("idProduct") UUID idProduct, @RequestBody ProductDTO productDTO) throws Exception{
        return productService.updateProduct(idProduct, productDTO);
    }

}

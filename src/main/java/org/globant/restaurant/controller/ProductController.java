package org.globant.restaurant.controller;

import org.globant.restaurant.model.ProductDTO;
import org.globant.restaurant.service.Product.IProductService;
import org.globant.restaurant.service.Product.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    IProductService productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/{uuid}")
    public String findByUUID() { return productService.findByUUID(); }

    @PostMapping("/create/{uuid}")
    public String save(@PathVariable String uuid) {
        return productService.save(uuid); }


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

package org.globant.restaurant.controller;

import org.globant.restaurant.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @GetMapping("/{uuid}")
    public String readProductByUUID() { return productService.readProductByUUID(); }

    @PostMapping("/j/{uuid}")
    public String createProduct(@PathVariable String uuid) { return productService.createProduct(uuid); }


}

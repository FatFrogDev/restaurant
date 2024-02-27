package org.globant.restaurant.controller;

import org.globant.restaurant.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @PostMapping("/")
    public String createProduct(@PathVariable String uuid) { return productService.createProduct(uuid); }

    @GetMapping("/{uuid}")
    public String readProductByUUID(@PathVariable String uuid) { return productService.readProductByUUID(uuid); }
}

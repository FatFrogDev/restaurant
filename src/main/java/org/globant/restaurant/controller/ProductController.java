package org.globant.restaurant.controller;

import org.globant.restaurant.service.Product.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @GetMapping("/{uuid}")
    public String findByUUID() { return productService.findByUUID(); }

    @PostMapping("/create/{uuid}")
    public String save(@PathVariable String uuid) { return productService.save(uuid); }


}

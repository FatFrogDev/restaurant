package org.globant.restaurant.controller;

import org.globant.restaurant.service.RestaurantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class RestaurantController {

    @Autowired
    RestaurantServiceImpl restaurantService;

    @GetMapping("/{document}")
    public String findClientByDocument() {
        return restaurantService.findClientByDocument();
    }

}

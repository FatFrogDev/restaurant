package org.globant.restaurant.service;

import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements IRestaurantService{

    @Override
    public String findClientByDocument() {
        return "Deyby Ariza";
    }
}

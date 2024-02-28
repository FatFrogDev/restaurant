package org.globant.restaurant.service.Restaurant;

import org.globant.restaurant.service.Restaurant.IRestaurantService;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements IRestaurantService {

    @Override
    public String findClientByDocument() {
        return "Deyby Ariza";
    }
}

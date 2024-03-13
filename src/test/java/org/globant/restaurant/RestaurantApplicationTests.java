package org.globant.restaurant;


import org.globant.restaurant.repository.Client.IClientRepository;
import org.globant.restaurant.repository.Product.IProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestaurantApplicationTests {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IClientRepository clientRepository;

    @Test
    void contextLoads() {
        System.out.println("Query to find product where fantasy name like 'parameter' with JPA:");
        System.out.println(productRepository.findAllByFantasyNameContainingAndAvailableIsTrue("hel" ) );

        System.out.println("Query to find product where fantasy name like 'parameter': ");
        System.out.println(productRepository.findAllByFantasyNameContainingJPQL("hel" ) );

        System.out.println("Query to find product where fantasy name like 'parameter': ");
        System.out.println(productRepository.findAllByFantasyNameContainingRawSQL("hel" ));

        System.out.println("Query to find product by custom field and order: ");
        System.out.println(clientRepository.findByCustomFieldAndOrder("name", "asc"));
    }
}

package org.globant.restaurant.validators;

import org.springframework.stereotype.Component;

@Component
public class ProductValidators {

    public final boolean productFantasyNameIsValid(String fantasyName){
        return fantasyName.matches("^[^0-9]*$")
                && fantasyName.length()>=2;
    }
}

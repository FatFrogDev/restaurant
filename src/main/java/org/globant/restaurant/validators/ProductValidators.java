package org.globant.restaurant.validators;

import jakarta.validation.Valid;
import org.springframework.stereotype.Component;
import org.globant.restaurant.model.ProductDTO;
import org.globant.restaurant.entity.ProductEntity;
import org.globant.restaurant.commons.constans.response.product.IProductResponse;
import org.globant.restaurant.exceptions.EntityHasNoDifferentDataException;

@Component
public class ProductValidators {
    public final boolean productHasChanges(@Valid ProductDTO productDTO, ProductEntity entity) {
        if (productDTO.getFantasyName() != null && !productDTO.getFantasyName().equalsIgnoreCase(entity.getFantasyName())) {
            return true;
        }

        if (productDTO.getCategory() != null && !productDTO.getCategory().equals(entity.getCategory().name())) {
            return true;
        }

        if (productDTO.getDescription() != null && !productDTO.getDescription().equals(entity.getDescription())) {
            return true;
        }

        if (productDTO.getPrice() >=0 && !(productDTO.getPrice()==entity.getPrice())) {
            return true;
        }

        if (productDTO.getAvailable() != null && productDTO.getAvailable() != entity.isAvailable()) {
            return true;
        }

        throw new EntityHasNoDifferentDataException(IProductResponse.PRODUCT_HAS_NOT_CHANGES);
    }

    public boolean productIsUpdatable(@Valid ProductDTO productDTO, ProductEntity entity) {
        return productHasChanges(productDTO, entity);
    }
    public final boolean productFantasyNameIsValid(String fantasyName){
        return fantasyName.matches("^[^0-9]*$")
                && fantasyName.length()>=2;
    }
}

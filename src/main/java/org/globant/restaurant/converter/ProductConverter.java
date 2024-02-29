package org.globant.restaurant.converter;

import lombok.extern.log4j.Log4j2;
import org.globant.restaurant.entity.ProductEntity;
import org.globant.restaurant.helpers.HelperMapper;
import org.globant.restaurant.model.ProductDto;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ProductConverter {
    public ProductDto convertProductEntityToProductDTO(ProductEntity productEntity) {
        ProductDto productDto = new ProductDto();
        try {
            productDto= HelperMapper.modelMapper().map(productEntity, ProductDto.class);
        } catch (Exception e) {
            log.error("Error");
        }
        return productDto;
    }
}

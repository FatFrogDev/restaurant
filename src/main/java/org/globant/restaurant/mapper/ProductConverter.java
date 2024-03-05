package org.globant.restaurant.mapper;

import lombok.extern.log4j.Log4j2;
import org.globant.restaurant.entity.ProductEntity;
import org.globant.restaurant.helpers.HelperMapper;
import org.globant.restaurant.model.ProductDTO;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ProductConverter {
    public ProductDTO convertProductEntityToProductDTO(ProductEntity productEntity) {
        ProductDTO productDTO = new ProductDTO();
        try {
            productDTO= HelperMapper.modelMapper().map(productEntity, ProductDTO.class);
        } catch (Exception e) {
            log.error("Error");
        }
        return productDTO;
    }
}

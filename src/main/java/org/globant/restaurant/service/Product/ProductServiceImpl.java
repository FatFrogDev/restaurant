package org.globant.restaurant.service.Product;

import org.globant.restaurant.entity.ProductEntity;
import org.globant.restaurant.mapper.ProductConverter;
import org.globant.restaurant.model.ProductDTO;
import org.globant.restaurant.repository.Product.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {

    ProductRepository productRepository;
    ProductConverter productConverter;

    public ProductServiceImpl(ProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    @Override
    public ResponseEntity<?> save(ProductDTO productDTO) {
        // productDTO.setCategory(productDTO.getCategory().toUpperCase());
        ProductEntity productEntity = productConverter.covertProductDtoToProductEntity(productDTO);

        // productEntity.setUuid(UUID.randomUUID());
        System.out.println(productEntity.getUuid());
        System.out.println(productEntity.toString());
        try {
            productRepository.save(productEntity);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(productEntity);
        } catch (Exception e){
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }

    @Override
    public Optional<?> findByUUID(ProductDTO productDTO) {
/**/
        return null;
    }

    @Override
    public ProductDTO getProductService(UUID idProduct) {
        return null;
        //return productRepository.getProductRepository(idProduct);
    }

    @Override
    public List<ProductDTO> getAllProductService() {
        return null;
        //return productRepository.getAllProductService();
    }

    @Override
    public ProductDTO updateProduct(UUID idProduct, ProductDTO productDTO) throws Exception{
        return null;
        /*ProductDTO productFound = getProductService(idProduct);
        if(productFound == null){
            throw new Exception("Product doesn't exist");
        }
        productFound.setFantasyName(productDTO.getFantasyName());
        productFound.setDescription(productDTO.getDescription());
        productFound.setPrice(productDTO.getPrice());
        productFound.setCategory(productDTO.getCategory());
        productFound.setAvailable(productDTO.getAvailable());

        return productRepository.update(productFound);*/
    }
}

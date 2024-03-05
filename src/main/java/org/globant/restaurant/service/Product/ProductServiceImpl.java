package org.globant.restaurant.service.Product;

import org.globant.restaurant.model.ProductDTO;
import org.globant.restaurant.repository.Product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {

    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String save(String uuid) {
        return productRepository.saveProduct(uuid);
    }

    @Override
    public String findByUUID() {
        return "Productos: ";
    }

    @Override
    public ProductDTO getProductService(UUID idProduct) {
        return productRepository.getProductRepository(idProduct);
    }

    @Override
    public List<ProductDTO> getAllProductService() {
        return productRepository.getAllProductService();
    }

    @Override
    public ProductDTO updateProduct(UUID idProduct, ProductDTO productDTO) throws Exception{
        ProductDTO productFound = getProductService(idProduct);
        if(productFound == null){
            throw new Exception("Product doesn't exist");
        }
        productFound.setFantasyName(productDTO.getFantasyName());
        productFound.setDescription(productDTO.getDescription());
        productFound.setPrice(productDTO.getPrice());
        productFound.setCategory(productDTO.getCategory());
        productFound.setAvailable(productDTO.getAvailable());

        return productRepository.update(productFound);
    }
}

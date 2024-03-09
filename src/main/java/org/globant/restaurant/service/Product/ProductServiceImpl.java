package org.globant.restaurant.service.Product;

import org.globant.restaurant.entity.ProductEntity;
import org.globant.restaurant.exceptions.EntityAlreadyExistsException;
import org.globant.restaurant.exceptions.EntityNotFoundException;
import org.globant.restaurant.mapper.ProductConverter;
import org.globant.restaurant.model.ProductDTO;
import org.globant.restaurant.repository.Product.IProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    IProductRepository productRepository;
    ProductConverter productConverter;

    public ProductServiceImpl(IProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Optional<ProductEntity> existingProduct = productRepository.findByFantasyName(productDTO.getFantasyName().toUpperCase());

        if (existingProduct.isPresent()) {
            throw new EntityAlreadyExistsException("Product with fantasy name already exists");
        }

        ProductEntity productEntity = productConverter.convertProductDtoToProductEntity(productDTO);
        UUID uuid = UUID.randomUUID();
        productEntity.setUuid(uuid);
        productEntity.setFantasyName(productEntity.getFantasyName().toUpperCase());

        ProductEntity savedProduct = productRepository.save(productEntity);

        return productConverter.convertProductEntityToProductDTO(savedProduct);
    }

    @Override
    public ProductDTO findByUuid(UUID uuid) {
        Optional<ProductEntity> productOptional = productRepository.findByUuid(uuid);

        return productOptional.map(productConverter::convertProductEntityToProductDTO)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    @Override
    public void updateByUuid(UUID uuid, ProductDTO productDTO) {
        Optional<ProductEntity> existingProductOptional = productRepository.findByUuid(uuid);

        ProductEntity existingProduct = existingProductOptional.orElseThrow(() -> new EntityNotFoundException("Product not found"));

        boolean hasChanges = false;

        if (productDTO.getFantasyName() != null && !productDTO.getFantasyName().equalsIgnoreCase(existingProduct.getFantasyName())) {
            existingProduct.setFantasyName(productDTO.getFantasyName().toUpperCase());
            hasChanges = true;
        }

        if (productDTO.getCategory() != null && !productDTO.getCategory().equals(existingProduct.getCategory().name())) {
            existingProduct.setCategory(ProductEntity.Category.valueOf(productDTO.getCategory()));
            hasChanges = true;
        }

        if (productDTO.getDescription() != null && !productDTO.getDescription().equals(existingProduct.getDescription())) {
            existingProduct.setDescription(productDTO.getDescription());
            hasChanges = true;
        }

        if (productDTO.getPrice() != null && !productDTO.getPrice().equals(String.valueOf(existingProduct.getPrice()))) {
            existingProduct.setPrice(Double.parseDouble(productDTO.getPrice()));
            hasChanges = true;
        }

        if (productDTO.getAvailable() != null && productDTO.getAvailable() != existingProduct.isAvailable()) {
            existingProduct.setAvailable(productDTO.getAvailable());
            hasChanges = true;
        }

        if (!hasChanges) {
            throw new EntityNotFoundException("No changes detected in the request");
        }

        productRepository.save(existingProduct);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        Optional<ProductEntity> productOptional = productRepository.findByUuid(uuid);

        productOptional.ifPresentOrElse(
                product -> productRepository.deleteByUuid(uuid),
                () -> {
                    throw new EntityNotFoundException("Product not found");
                }
        );
    }
}

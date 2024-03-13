package org.globant.restaurant.service.Product;

import org.globant.restaurant.commons.constans.response.product.IProductResponse;
import org.globant.restaurant.controller.ProductController;
import org.globant.restaurant.entity.ProductEntity;
import org.globant.restaurant.exceptions.EntityAlreadyExistsException;
import org.globant.restaurant.exceptions.EntityNotFoundException;
import org.globant.restaurant.exceptions.ProductInvalidFantasyName;
import org.globant.restaurant.mapper.ProductConverter;
import org.globant.restaurant.model.ProductDTO;
import org.globant.restaurant.repository.Product.IProductRepository;
import org.globant.restaurant.validators.ProductValidators;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;

    private final ProductConverter productConverter;

    private final ProductValidators validator;

    public ProductServiceImpl(IProductRepository productRepository, ProductConverter productConverter, ProductValidators validator) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
        this.validator = validator;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Optional<ProductEntity> existingProduct = productRepository.findByFantasyName(productDTO.getFantasyName().toUpperCase());

        if (existingProduct.isPresent()) {
            throw new EntityAlreadyExistsException(IProductResponse.PRODUCT_EXIST);
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
                .orElseThrow(() -> new EntityNotFoundException(IProductResponse.PRODUCT_NOT_FOUND));
    }

    @Override
    public void updateByUuid(UUID uuid, ProductDTO productDTO) {
        Optional<ProductEntity> existingProductOptional = productRepository.findByUuid(uuid);

        ProductEntity existingProduct = existingProductOptional.orElseThrow(() -> new EntityNotFoundException(IProductResponse.PRODUCT_NOT_FOUND));

        if (validator.productIsUpdatable(productDTO, existingProduct)) {
            if (productDTO.getFantasyName() != null) {
                existingProduct.setFantasyName(productDTO.getFantasyName().toUpperCase());
            }

            if (productDTO.getCategory() != null) {
                existingProduct.setCategory(ProductEntity.Category.valueOf(productDTO.getCategory()));
            }

            if (productDTO.getDescription() != null) {
                existingProduct.setDescription(productDTO.getDescription());
            }

            if (productDTO.getPrice() != null) {
                existingProduct.setPrice(Double.parseDouble(productDTO.getPrice()));
            }

            if (productDTO.getAvailable() != null) {
                existingProduct.setAvailable(productDTO.getAvailable());
            }

            productRepository.save(existingProduct);
        }
    }

    @Override
    @Transactional
    public void deleteByUuid(UUID uuid) {
        Optional<ProductEntity> productOptional = productRepository.findByUuid(uuid);

        productOptional.ifPresentOrElse(
                product -> productRepository.deleteByUuid(uuid),
                () -> {
                    throw new EntityNotFoundException(IProductResponse.PRODUCT_NOT_FOUND);
                }
        );
    }

    @Override
    public ProductDTO findByFantasyName(String fantasyName) {
        if (validator.productFantasyNameIsValid(fantasyName.toUpperCase())) {
            Optional<ProductEntity> optionalProductEntity = productRepository.findByFantasyName(fantasyName);

            if (optionalProductEntity.isPresent()) {
                return productConverter.
                        convertProductEntityToProductDTO(optionalProductEntity.get());
            }
            throw new EntityNotFoundException(IProductResponse.PRODUCT_NOT_EXIST + fantasyName);
        }
        throw new ProductInvalidFantasyName(IProductResponse.PRODUCT_FORMAT_INVALID);
    }
}

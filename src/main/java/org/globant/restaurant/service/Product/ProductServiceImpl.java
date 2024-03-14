package org.globant.restaurant.service.Product;

import lombok.AllArgsConstructor;
import org.globant.restaurant.commons.constans.response.product.IProductResponse;
import org.globant.restaurant.entity.ProductEntity;
import org.globant.restaurant.exceptions.EntityAlreadyExistsException;
import org.globant.restaurant.exceptions.EntityNotFoundException;
import org.globant.restaurant.exceptions.ProductInvalidFantasyName;
import org.globant.restaurant.mapper.ProductConverter;
import org.globant.restaurant.model.ProductDTO;
import org.globant.restaurant.repository.Product.IProductRepository;
import org.globant.restaurant.validators.ProductValidators;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;

    private final ProductConverter productConverter;

    private final ProductValidators validator;

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Optional<ProductEntity> existingProduct = productRepository.findByFantasyNameAndAvailableIsTrue(productDTO.getFantasyName().toUpperCase());

        if (existingProduct.isPresent()) {
            throw new EntityAlreadyExistsException(IProductResponse.PRODUCT_EXIST);
        }

        ProductEntity productEntity = productConverter.convertProductDtoToProductEntity(productDTO);
        productEntity.setUuid(UUID.randomUUID().toString());
        productEntity.setFantasyName(productEntity.getFantasyName().toUpperCase());


        return productConverter.convertProductEntityToProductDTO(productRepository.save(productEntity));
    }

    @Override
    public ProductDTO findByUuid(String uuid) {
        Optional<ProductEntity> productOptional = productRepository.findByUuidAndAvailableIsTrue(uuid);

        return productOptional.map(productConverter::convertProductEntityToProductDTO)
                .orElseThrow(() -> new EntityNotFoundException(IProductResponse.PRODUCT_NOT_FOUND));
    }

    @Override
    public void updateByUuid(String uuid, ProductDTO productDTO) { //TODO: check this method
        Optional<ProductEntity> existingProductOptional = productRepository.findByUuidAndAvailableIsTrue(uuid);

        ProductEntity existingProduct = existingProductOptional.orElseThrow(() -> new EntityNotFoundException(IProductResponse.PRODUCT_NOT_FOUND));

        if (validator.productIsUpdatable(productDTO, existingProduct)) {
            productRepository.save(existingProduct);
        }
    }

    @Override
    public void deleteByUuid(String uuid) {
        Optional<ProductEntity> productOptional = productRepository.findByUuidAndAvailableIsTrue(uuid);

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
            Optional<ProductEntity> optionalProductEntity = productRepository.findByFantasyNameAndAvailableIsTrue(fantasyName);

            if (optionalProductEntity.isPresent()) {
                return productConverter.
                        convertProductEntityToProductDTO(optionalProductEntity.get());
            }
            throw new EntityNotFoundException(IProductResponse.PRODUCT_NOT_EXISTS + " " + fantasyName);
        }
        throw new ProductInvalidFantasyName(IProductResponse.FANTASY_NAME_FORMAT_IS_INVALID);
    }

    @Override
    public boolean existsByUuid(String uuid) {
         // TODO: Add uuid validator format before returning a result.
        return productRepository.existsByUuid(uuid);
    }
}
package org.globant.restaurant.repository.Product;

import org.globant.restaurant.model.ProductDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public class ProductRepository implements IProductRepository{
    private List<ProductDTO> products = List.of(
            new ProductDTO(UUID.randomUUID(),"COCACOLA","BEVERAGES","Refresco azúcarado","2.50",true),
            new ProductDTO(UUID.randomUUID(),"COMBO POLLO FRITO","CHICKEN","6 piezas de pollo frito, papas fritas y gaseosa de 350ml","18.99",false),
            new ProductDTO(UUID.randomUUID(),"COMBO SUPER CHEESE BURGER DOBLE","HAMBURGERS_AND_HOTDOGS","Hamburguesa pan de ajonjolí, doble carne de 200gr, queso cheddar, papas fritas y gaseosa de 350ml","25.55",true)
    );
    @Override
    public String saveProduct(String uuid){
        return "Product " + uuid + " created successfully!";
    }

    @Override
    public String findProductByUUID() {
        return  "Product found successfully!";
    }

    @Override
    public ProductDTO getProductRepository(UUID idProduct) {
        return products.stream()
                .filter(product -> product.getUuid().equals(idProduct))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<ProductDTO> getAllProductService() {
        return products;
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) {
        ProductDTO productoEncontrado = null;
        for (ProductDTO product : products){
            if(product.getUuid() == productDTO.getUuid()){
                productoEncontrado = product;
            }
        }
        productoEncontrado.setDescription((productDTO.getDescription()));
        productoEncontrado.setFantasyName(productDTO.getFantasyName());
        return productoEncontrado;
    }
}

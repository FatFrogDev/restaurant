package org.globant.restaurant.commons.constans.endPoints.product;

public interface IProductEndPoint {
    String BASE_URL_PRODUCT = "/products";
    String CREATE_PRODUCT = "";
    String GET_PRODUCT = "/{idProduct}";
    String UPDATE_PRODUCT = "/{document}";
    String DELETE_PRODUCT = "/{document}";
    String FIND_PRODUCT_NAME = "/search";
}

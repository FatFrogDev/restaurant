package org.globant.restaurant.commons.constans.endPoints.product;

public interface IProductEndPoint {
    String BASE_URL_PRODUCT = "/products";
    String FIND_PRODUCT_UUID = "/{uuid}";
    String CREATE_PRODUCT = "";
    String GET_PRODUCT = "/{idProduct}";
    String GET_ALL_PRODUCT ="/all";
    String UPDATE_PRODUCT = "/{document}";
    String DELETE_PRODUCT = "/{document}";
}

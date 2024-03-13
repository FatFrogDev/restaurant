package org.globant.restaurant.commons.constans.endPoints.product;

public interface IProductEndPoint {
    String BASE_URL_PRODUCT = "/products";
    String CREATE_PRODUCT = "";
    String GET_PRODUCT = "/{uuid}";
    String UPDATE_PRODUCT = "/{uuid}";
    String DELETE_PRODUCT = "/{uuid}";
    String FIND_PRODUCT_NAME = "/search";
}

package org.globant.restaurant.commons.constans.endPoints.order;

public interface IOrderEndPoint {
    String BASE_URL = "/orders";
    String CREATE_ORDER = "";
    String UPDATE_BY_UUID = "/{uuid}/delivered/{timestamp}";
}

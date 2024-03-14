package org.globant.restaurant.validators;

import lombok.AllArgsConstructor;
import org.globant.restaurant.commons.constans.response.client.IClientResponse;
import org.globant.restaurant.commons.constans.response.order.IOrderResponse;
import org.globant.restaurant.exceptions.ClientInvalidDocumentFormatException;
import org.globant.restaurant.exceptions.EntityValidationException;
import org.globant.restaurant.model.ClientDto;
import org.globant.restaurant.model.request.OrderRequest;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@AllArgsConstructor
public class OrderValidators {

    ClientValidators clientValidators;

    public final boolean isSavableOrder(OrderRequest order){

        if((order.getExtraInformation().length() <= 511)
                && clientValidators.clientDocumentIsValid(order.getClientDocument())
                && (order.getQuantity() >= 1 && order.getQuantity() <= 100)){
            return true;
        } throw new EntityValidationException(IOrderResponse.ORDER_NOT_SAVABLE);
    }


}
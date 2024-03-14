package org.globant.restaurant.validators;

import org.globant.restaurant.commons.constans.response.client.IClientResponse;
import org.globant.restaurant.exceptions.ClientInvalidDocumentFormatException;
import org.globant.restaurant.model.ClientDto;
import org.globant.restaurant.model.request.OrderRequest;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class OrderValidators {
    public final boolean isSavableOrder(OrderRequest order){

        return (order.getExtraInformation().length() <= 511)
                && clientDocumentIsValid(order.getClientDocument())
                && (order.getQuantity() >= 1 && order.getQuantity() <= 100);
    };

    public final boolean clientDocumentIsValid(String document){
        Pattern documentPattern = Pattern.compile("^(CC|P|CE)-[0-9]{5,12}$");
        Matcher matcher = documentPattern.matcher(document);
        if (!document.isEmpty()
                && (!(document.isBlank()) && (document.length()<=20))
                && matcher.matches()){
            return true;
        } throw new ClientInvalidDocumentFormatException(IClientResponse.CLIENT_INVALID_FORMAT_DOCUMENT);
    };
}

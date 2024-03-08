package org.globant.restaurant.validators;

import org.springframework.stereotype.Component;


@Component
public class ClientValidators {

    /**
     * Validate custom field and order query boolean.
     *
     * @param customField the custom field
     * @param customOrder the custom order
     * @return the boolean
     */
    public final boolean validateCustomFieldAndOrderQuery(String customField, String customOrder) {
        return
        (
            customField.equalsIgnoreCase("document")
                || customField.equalsIgnoreCase ("name")
                || customField.equalsIgnoreCase("address")
        )
        &&
        (
            customOrder == null
                    ||  customOrder.equalsIgnoreCase("asc")
                    || customOrder.equalsIgnoreCase("desc")
        );
    }

    /**
     * Validate custom field string.
     *
     * @param customField the custom field
     * @return the string
     */
    public final String validateCustomField(String customField){
        return customField.equalsIgnoreCase("name") ? "fullName"
                : customField.equalsIgnoreCase("address") ? "deliveryAddress"
                : customField;

    }
}

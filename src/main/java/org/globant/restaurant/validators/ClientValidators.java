package org.globant.restaurant.validators;

import org.springframework.stereotype.Component;


@Component
public class ClientValidators {

    /**
     * Validate custom if a custom field is valid to be searched in the db.
     *
     * @param customField the custom field to be validated.
     * @param customOrder the custom order field to be validated.
     * @return boolean Weather the custom field and order are valid or not.
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
     * Transforms the parameter given in a declared "valid"  String. <br>
     * Transform "name" to "fullName" and "address" to "deliveryAddress" otherwise return the same string.
     *
     * @param customField the custom field
     * @return String the transformed parameter which is a custom field or the same given string.
     */
    public final String transformCustomField(String customField){
        return customField.equalsIgnoreCase("name") ? "fullName"
                : customField.equalsIgnoreCase("address") ? "deliveryAddress"
                : customField;

    }
}

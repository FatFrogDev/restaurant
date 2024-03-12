package org.globant.restaurant.validators;

import jakarta.validation.Valid;
import org.globant.restaurant.entity.ClientEntity;
import org.globant.restaurant.exceptions.ClientHasDifferentDocumentException;
import org.globant.restaurant.exceptions.ClientInvalidDocumentFormatException;
import org.globant.restaurant.exceptions.EntityHasNoDifferentDataException;
import org.globant.restaurant.model.ClientDto;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains all the client validations that are used or required in the program.
 * <br>
 * Note that it could be also used a {@link Valid} annotation through the Dto's or Entities.
 */
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

    /**
     * States if a given ClientDto object is able to get converted into a clientEntity and be saved into the database.
     *
     * @param clientDto the ClientDto object to be evaluated
     * @return boolean telling weather the client is savable or not.
     */
    public final boolean isSavableClient(ClientDto clientDto){

        if (clientDocumentIsValid(clientDto.getDocument())){
            return true;
        };

        if(clientDto.getPhone().length()>10){
            return false;
        }

        if (clientDto.getDeliveryAddress().length()>500){
            return  false;
        }
        return true;
    };


    /**
     * States if a ClientDto object has changes compared to its database entity.
     *
     * @param clientDto the ClientDto the object that retrieves the data to be updated.
     * @param entity the ClientEntity object to be compared.
     * @return Weather the Client has changes to be updated or not.
     */
    public final boolean clientHasChanges(@Valid ClientDto clientDto, ClientEntity entity){

        if(clientDto.getFullName() != null && !clientDto.getFullName().equals(entity.getFullName())){
            return true;
        }

        // Return weather the email is not null and is not different from the entity email or not.
        if(clientDto.getEmail() != null && clientDto.getEmail().equals(entity.getEmail())){
            return true;
        }

        if(clientDto.getPhone() != null && !clientDto.getPhone().equals(entity.getPhone())){
            return true;
        }

        if(clientDto.getDeliveryAddress() != null && !clientDto.getDeliveryAddress().equals(entity.getDeliveryAddress())){
            return true;
        }

        throw new EntityHasNoDifferentDataException("The client has no changes to be updated.");
    };

    /**
     * States if a client is updatable or not through validations.
     * Returns false if it has any validation error or true when has valid changes to be applied into the database.
     *
     * @param clientDto the ClientDto to be evaluated.
     * @param entity the ClientEntity object to be compared.
     * @return True when a client is updatable into the database or not.
     * @throws ClientHasDifferentDocumentException when ClientDto object has a different document that the entity one.
     * @throws ClientInvalidDocumentFormatException when client document format is invalid.
     */
    public boolean clientIsUpdatable(@Valid ClientDto clientDto, ClientEntity entity){
        if (clientDocumentIsValid(clientDto.getDocument())) {
            if (clientDto.getDocument().equals(entity.getDocument())) {
                return clientHasChanges(clientDto, entity);
            } throw new ClientHasDifferentDocumentException("The document of the client cannot be updated.");
        }
        throw new ClientInvalidDocumentFormatException("The document has an incorrect format.");
    };

    /**
     * States if a string given as a document has a valid client document format or not through a regular expression.
     *
     * @param document the document to be evaluated.
     * @return True when the client document has a valid format.
     * @throws ClientInvalidDocumentFormatException when client document format is invalid.
     */
    public final boolean clientDocumentIsValid(String document){
        Pattern documentPattern = Pattern.compile("^(CC|P|CE)-[0-9]{5,12}$");
        Matcher matcher = documentPattern.matcher(document);
        if (!document.isEmpty()
                && (!(document.isBlank()) && (document.length()<=20))
                && matcher.matches()){
                return true;
        } throw new ClientInvalidDocumentFormatException("The document has an incorrect format.");
    };
}

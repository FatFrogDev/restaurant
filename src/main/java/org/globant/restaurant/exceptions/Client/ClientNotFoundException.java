package org.globant.restaurant.exceptions.Client;

public class ClientNotFoundException extends Error{
   public ClientNotFoundException(String message){
       super(message);
   };
}

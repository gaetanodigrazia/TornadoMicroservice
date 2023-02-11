/**
* Auto-generated Bean class using Tornado Library
*
*
* @author  Gaetano Di Grazia
* @version 1.0
* @since   05-08-2021
*
* 
*/
package com.tornado.exceptions;

public class ApiKeyNotFoundException extends RuntimeException { 

public ApiKeyNotFoundException(Long id) {

super("Could not find apikey " + id);

}

}


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
package com.tornado.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tornado.exceptions.ApiKeyNotFoundException;

@ControllerAdvice
class ApiKeyNotFoundAdvice {

@ResponseBody
@ExceptionHandler(ApiKeyNotFoundException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
String apikeyNotFoundHandler(ApiKeyNotFoundException ex) {
return ex.getMessage();
}
}

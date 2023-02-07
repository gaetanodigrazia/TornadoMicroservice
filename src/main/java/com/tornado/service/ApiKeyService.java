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
package com.tornado.service;

		import java.util.List;

import com.tornado.models.ApiKey;
		/**
* The Interface ApiKeyService.
*/

public interface ApiKeyService<T> {
public T create(ApiKey element);

public List<ApiKey> getAll();

public List<ApiKey> getBySerialKey(Long serialKey);

public List<ApiKey> getByAvailableRequests(Integer availableRequests);

public List<ApiKey> getByIsActive(Boolean isActive);

public List<ApiKey> getByUserId(Long userId);

}

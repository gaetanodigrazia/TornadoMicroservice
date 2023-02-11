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
package com.tornado.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tornado.models.ApiKey;
import com.tornado.repository.ApiKeyRepository;
import com.tornado.service.ApiKeyService;

/**
 * The Class ApiKeyServiceImpl.
 */
@Service
public class ApiKeyServiceImpl implements ApiKeyService<ApiKey> {
	private static final Logger logger = LoggerFactory.getLogger(ApiKeyServiceImpl.class);
	
	@Autowired
	ApiKeyRepository<ApiKey> apikeyRepo;

	public ApiKeyServiceImpl() {
	}

	public ApiKey create(ApiKey element) {
		logger.info("-------------- Added apikey ------------------", element.toString());
		return (ApiKey) apikeyRepo.save(element);
	}

	public ApiKey update(ApiKey data, String id) {
		return (ApiKey) apikeyRepo.save(data);
	}

	@Override
	public List<ApiKey> getAll() {
		return apikeyRepo.findAll();
	}

	@Override
	public List<ApiKey> getBySerialKey(Long serialKey) {

		return apikeyRepo.findAll().stream().filter(x -> x.getSerialKey() == serialKey).collect(Collectors.toList());
	}

	@Override
	public List<ApiKey> getByAvailableRequests(Integer availableRequests) {

		return apikeyRepo.findAll().stream().filter(x -> x.getAvailableRequests() == availableRequests)
				.collect(Collectors.toList());
	}

	@Override
	public List<ApiKey> getByIsActive(Boolean isActive) {

		return apikeyRepo.findAll().stream().filter(x -> x.getIsActive() == isActive).collect(Collectors.toList());
	}

	@Override
	public List<ApiKey> getByUserId(Long userId) {

		return apikeyRepo.findAll().stream().filter(x -> x.getUserId() == userId).collect(Collectors.toList());
	}

}

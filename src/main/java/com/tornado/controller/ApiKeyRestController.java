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
package com.tornado.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tornado.exceptions.ApiKeyNotFoundException;
import com.tornado.models.ApiKey;
import com.tornado.repository.ApiKeyRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/apikey")
public class ApiKeyRestController {
	@Autowired
	private ApiKeyRepository<Long> repository;

	ApiKeyRestController() {
	}

	@GetMapping
	List<ApiKey> all() {
		return repository.findAll();
	}

	@PostMapping
	ApiKey newEmployee(@RequestBody ApiKey newApiKey) {
		return (ApiKey) repository.save(newApiKey);
	}

	@GetMapping("/{id}")
	ApiKey getApiKey(@PathVariable Long id) throws Throwable {
		return (ApiKey) repository.findById(id).orElseThrow(() -> new ApiKeyNotFoundException(id));
	}

	@PutMapping("/{id}")
	ApiKey replaceApiKey(@RequestBody ApiKey newApiKey, @PathVariable Long id) throws Throwable {
		return (ApiKey) repository.findById(id).map(apikey -> {
			return repository.save(apikey);
		}).orElseThrow(() -> new ApiKeyNotFoundException(id));
	}

	@DeleteMapping("/apikey/{id}")
	void deleteApiKey(@PathVariable Long id) {
		repository.deleteById(id);
	}
}

package com.tornado.models;

import java.io.IOException;
import java.util.List;

import com.tornado.init.InitApi;

public class RestController {
	private Class<?> clazz;
	
	public RestController (Class<?> clazz) {
		this.clazz = clazz;
	}

	
	public List<String> getRestController() throws IOException {
		InitApi initApi = new InitApi();
		return initApi.printController(null, null, null);
	}
}

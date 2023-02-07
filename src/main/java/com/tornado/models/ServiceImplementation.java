package com.tornado.models;

import java.io.IOException;
import java.util.List;

import com.tornado.init.InitApi;

public class ServiceImplementation {
	private Class<?> clazz;
	
	public ServiceImplementation (Class<?> clazz) {
		this.clazz = clazz;
	}
	
	public List<String> getServiceImplementation() throws IOException {
		InitApi initApi = new InitApi();
		return initApi.printServiceImpl(null, null, null, clazz);
	}
}

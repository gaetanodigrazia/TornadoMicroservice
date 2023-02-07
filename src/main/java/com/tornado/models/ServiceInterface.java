package com.tornado.models;

import java.io.IOException;
import java.util.List;

import com.tornado.init.InitApi;

public class ServiceInterface {
	private Class<?> clazz;
	
	public ServiceInterface (Class<?> clazz) {
		this.clazz = clazz;
	}
	
	public List<String> getServiceInterface() throws IOException {
		InitApi initApi = new InitApi();
		return initApi.printService(null, null, null, clazz);
	}
}

package com.tornado.models;

import java.io.IOException;
import java.util.List;

import com.tornado.init.InitApi;

public class Repository {
	private Class<?> clazz;
	
	public Repository (Class<?> clazz) {
		this.clazz = clazz;
	}
	public List<String> getRepository() throws IOException {
		InitApi initApi = new InitApi();
		return initApi.printRepositoryClass(null, null, null);
	}

}

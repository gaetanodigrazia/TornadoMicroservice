package com.tornado.models;

import java.io.IOException;
import java.util.List;

import com.tornado.init.InitApi;

public class ControllerAdvice {
	private Class<?> clazz;
	
	public ControllerAdvice (Class<?> clazz) {
		setClazz(clazz);
	}

	/**
	 * @return the clazz
	 */
	public Class<?> getClazz() {
		return clazz;
	}

	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	public List<String> getRestController() throws IOException {
		InitApi initApi = new InitApi();
		return initApi.printControllerAdvice(null, null, null);
	}
}

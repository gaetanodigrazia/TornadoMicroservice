package com.tornado.models;

import java.io.IOException;
import java.util.List;

import com.tornado.init.InitApi;

public class Exceptions {
	private Class<?> clazz;
	
	public Exceptions (Class<?> clazz) {
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
	
	public List<String> getExceptions() throws IOException {
		InitApi initApi = new InitApi();
		return initApi.printExceptionClass(null, null, null);
	}
}

package com.tornado.models;

public class Repository {
	private Class<?> clazz;
	
	public Repository (Class<?> clazz) {
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

}

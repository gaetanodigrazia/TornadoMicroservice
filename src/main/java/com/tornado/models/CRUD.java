package com.tornado.models;

import com.tornado.init.InitApi;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class CRUD {
	private String stringPackage;
	private Class<?> restController;
	private Class<?> serviceInterface;
	private Class<?> serviceImplementation;
	private Class<?> repository;
	private Class<?> filter;
	private Class<?> exceptions;
	private Class<?> controllerAdvice;



	public CRUD(CRUDBuilder crudBuilder) {
		this.stringPackage = crudBuilder.stringPackage;
		this.restController = crudBuilder.restController;
		this.serviceInterface = crudBuilder.serviceInterface;
		this.serviceImplementation = crudBuilder.serviceImplementation;
		this.repository = crudBuilder.repository;
		this.filter = crudBuilder.filter;
		this.exceptions = crudBuilder.exceptions;
		this.controllerAdvice = crudBuilder.controllerAdvice;
	}

	/**
	 * @return the stringPackage
	 */
	public String getStringPackage() {
		return stringPackage;
	}

	/**
	 * @return the restController
	 */
	public Class<?> getRestController() {
		return restController;
	}

	public Class<?> getServiceInterface() {
		return serviceInterface;
	}

	public Class<?> getServiceImplementation() {
		return serviceImplementation;
	}

	public Class<?> getRepository() {
		return repository;
	}

	public Class<?> getFilter() {
		return filter;
	}

	public Class<?> getExceptions() {
		return exceptions;
	}

	public Class<?> getControllerAdvice() {
		return controllerAdvice;
	}

	public static class CRUDBuilder {
		private String stringPackage;
		private Class<?> restController;
		private Class<?> serviceInterface;
		private Class<?> serviceImplementation;
		private Class<?> repository;
		private Class<?> filter;
		private Class<?> exceptions;
		private Class<?> controllerAdvice;

		public CRUDBuilder() {

		}
		
		public CRUDBuilder withStringPackage(String stringPackage) {
			this.stringPackage= stringPackage;
			return this;
		}
		
		public CRUDBuilder withRestController(Class<?> beanClass) {
			this.restController = beanClass;
			return this;
		}
		public CRUDBuilder withControllerAdvice(Class<?> beanClass) {
			this.controllerAdvice = beanClass;
			return this;
		}
		public CRUDBuilder withServiceInterface(Class<?> beanClass) {
			this.serviceInterface = beanClass;
			return this;
		}
		public CRUDBuilder withServiceImplementation(Class<?> beanClass) {
			this.serviceImplementation = beanClass;
			return this;
		}
		public CRUDBuilder withRepository(Class<?> beanClass) {
			this.repository = beanClass;
			return this;
		}
		public CRUDBuilder withFilter(Class<?> beanClass) {
			this.filter = beanClass;
			return this;
		}
		public CRUDBuilder withExceptions(Class<?> beanClass) {
			this.exceptions= beanClass;
			return this;
		}
		public CRUD build() throws IOException {
			InitApi initApi = new InitApi();
			initApi.setStringRootPackage(this.stringPackage); 
			initApi.initCrud(this.controllerAdvice);
			return new CRUD(this);
		}
	}

}

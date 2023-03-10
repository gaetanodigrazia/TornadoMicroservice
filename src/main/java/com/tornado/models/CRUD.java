package com.tornado.models;

import com.tornado.init.InitApi;

import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class CRUD {
	private String basePackage;
	private Class<?> restController;
	private Class<?> serviceInterface;
	private Class<?> serviceImplementation;
	private Class<?> repository;
	private Class<?> filter;
	private Class<?> exceptions;
	private Class<?> controllerAdvice;
	private Documentation documentation;



	public CRUD(CRUDBuilder crudBuilder) {
		this.basePackage = crudBuilder.basePackage;
		this.restController = crudBuilder.restController;
		this.serviceInterface = crudBuilder.serviceInterface;
		this.serviceImplementation = crudBuilder.serviceImplementation;
		this.repository = crudBuilder.repository;
		this.filter = crudBuilder.filter;
		this.exceptions = crudBuilder.exceptions;
		this.controllerAdvice = crudBuilder.controllerAdvice;
		this.documentation = crudBuilder.documentation;

	}

	/**
	 * @return the stringPackage
	 */
	public String getBasePackage() {
		return basePackage;
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

	/**
	 * @return the documentation
	 */
	public Documentation getDocumentation() {
		return documentation;
	}


	public static class CRUDBuilder {
		private String basePackage;
		private Class<?> restController;
		private Class<?> serviceInterface;
		private Class<?> serviceImplementation;
		private Class<?> repository;
		private Class<?> filter;
		private Class<?> exceptions;
		private Class<?> controllerAdvice;
		private Documentation documentation;

		public CRUDBuilder() {

		}
		
		public CRUDBuilder withBasePackage(String basePackage) {
			this.basePackage= basePackage;
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
		public CRUDBuilder withApiDocumentation(Documentation documentationConfig) {
			this.documentation = documentationConfig;
			return this;
		}
		public CRUD build() throws IOException {
			InitApi initApi = new InitApi();
			initApi.setStringRootPackage(this.basePackage); 
			initApi.initCrud(this.controllerAdvice, this.documentation);
			return new CRUD(this);
		}
	}

}

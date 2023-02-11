package com.tornado.models;

import org.springframework.stereotype.Component;

@Component
public class Microservice {
	  private Keycloak keycloak;
	  private MySql mysql;
	  private PostgreSql postgresql;
	  private MongoDb mongodb;
	  private RestController restController;
	  private ServiceInterface serviceInterface;
	  private ServiceImplementation serviceImplementation;
	  private Repository repository;
	  private Filter filter;
	  private Exceptions exceptions;
	  private ControllerAdvice controllerAdvice;

	  public Microservice() {
		  
	  }
	  
	  private Microservice(Builder builder) {
	    this.keycloak = builder.keycloak;
	    this.mysql = builder.mysql;
	    this.postgresql = builder.postgresql;
	    this.mongodb = builder.mongodb;
	    this.restController = builder.restController;
	    this.serviceInterface = builder.serviceInterface;
	    this.serviceImplementation = builder.serviceImplementation;
	    this.repository = builder.repository;
	    this.filter = builder.filter;
	    this.exceptions = builder.exceptions;
	    this.controllerAdvice = builder.controllerAdvice;
	  }

	  public static Builder builder() {
	    return new Builder();
	  }

	  public static final class Builder {
			  private Keycloak keycloak;
			  private MySql mysql;
			  private PostgreSql postgresql;
			  private MongoDb mongodb;
			  private RestController restController;
			  private ServiceInterface serviceInterface;
			  private ServiceImplementation serviceImplementation;
			  private Repository repository;
			  private Filter filter;
			  private Exceptions exceptions;
			  private ControllerAdvice controllerAdvice;
			  
			  public Builder() {
	    }

	    public Builder withKeycloak(Keycloak keycloak) {
	      this.keycloak = keycloak;
	      return this;
	    }

	    public Builder withMySql(MySql mysql) {
	      this.mysql = mysql;
	      return this;
	    }

	    public Builder withPostgresql(PostgreSql postgresql) {
	      this.postgresql = postgresql;
	      return this;
	    }

	    public Builder withMongodb(MongoDb mongodb) {
		      this.mongodb = mongodb;
		      return this;
		    }
	    public Builder withRestController(RestController restController) {
	    	this.restController = restController;
	    	return this;
	    }
	    public Builder withRepository(Repository repository) {
	    	this.repository = repository;
	    	return this;
	    }
	    public Builder withServiceInterface(ServiceInterface serviceInterface) {
	    	this.serviceInterface = serviceInterface;
	    	return this;
	    }
	    public Builder withServiceImplementation(ServiceImplementation serviceImplementation) {
	    	this.serviceImplementation = serviceImplementation;
	    	return this;
	    }
	    public Builder withFilter(Filter filter) {
	    	this.filter = filter;
	    	return this;
	    }
	    public Builder withExceptions(Exceptions exceptions) {
	    	this.exceptions = exceptions;
	    	return this;
	    }
	    public Builder withControllerAdvice(ControllerAdvice controllerAdvice) {
	    	this.controllerAdvice = controllerAdvice;
	    	return this;
	    }

	    public Keycloak getKeycloak() {
			return keycloak;
		}

		public MySql getMysql() {
			return mysql;
		}

		public PostgreSql getPostgresql() {
			return postgresql;
		}

		public MongoDb getMongodb() {
			return mongodb;
		}

		public RestController getRestController() {
			return restController;
		}

		public ServiceInterface getServiceInterface() {
			return serviceInterface;
		}

		public ServiceImplementation getServiceImplementation() {
			return serviceImplementation;
		}

		public Repository getRepository() {
			return repository;
		}

		public Filter getFilter() {
			return filter;
		}

		public Exceptions getExceptions() {
			return exceptions;
		}

		public ControllerAdvice getControllerAdvice() {
			return controllerAdvice;
		}

		public Microservice build() {
	      return new Microservice(this);
	    }
	  }
}

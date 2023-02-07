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

	  
	  private Microservice(Builder builder) {
	    this.keycloak = builder.keycloak;
	    this.mysql = builder.mysql;
	    this.postgresql = builder.postgresql;
	    this.mongodb = builder.mongodb;
	    
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
	    public Builder witRepository(Repository repository) {
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

	    public Microservice build() {
	      return new Microservice(this);
	    }
	  }
}

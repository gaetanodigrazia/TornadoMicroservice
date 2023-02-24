package com.tornado.models;

import org.springframework.stereotype.Component;

@Component
public class Microservice {
	  private Keycloak keycloak;
	  private MySql mysql;
	  private PostgreSql postgresql;
	  private MongoDb mongodb;
	  private Docker docker;
	  private HttpRequestInterceptor httpRequestInterceptor;
	  
	  public Microservice() {
		  
	  }
	  
	  private Microservice(MicroserviceBuilder builder) {
	    this.keycloak = builder.keycloak;
	    this.mysql = builder.mysql;
	    this.postgresql = builder.postgresql;
	    this.mongodb = builder.mongodb;
	    this.docker = builder.docker;
	    this.httpRequestInterceptor = builder.httpRequestInterceptor;
	  }

	  public static MicroserviceBuilder builder() {
	    return new MicroserviceBuilder();
	  }

	  public static final class MicroserviceBuilder {
		  private Keycloak keycloak;
		  private MySql mysql;
		  private PostgreSql postgresql;
		  private MongoDb mongodb;
		  private Docker docker;
		  private HttpRequestInterceptor httpRequestInterceptor;

			  public MicroserviceBuilder() {
	    }

	    public MicroserviceBuilder withKeycloak(Keycloak keycloak) {
	      this.keycloak = keycloak;
	      return this;
	    }

	    public MicroserviceBuilder withMySql(MySql mysql) {
	      this.mysql = mysql;
	      return this;
	    }

	    public MicroserviceBuilder withPostgresql(PostgreSql postgresql) {
	      this.postgresql = postgresql;
	      return this;
	    }

	    public MicroserviceBuilder withMongodb(MongoDb mongodb) {
		      this.mongodb = mongodb;
		      return this;
		    }
	    public MicroserviceBuilder withHttpRequestInterceptor() {
	    	this.httpRequestInterceptor = new HttpRequestInterceptor(this.getClass());
	    	return this;
	    }
	    public MicroserviceBuilder withDocker(Docker docker) {
	    	this.docker = docker;
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

		/**
		 * @return the docker
		 */
		public Docker getDocker() {
			return docker;
		}

		public Microservice build() {
	      return new Microservice(this);
	    }
	  }
}

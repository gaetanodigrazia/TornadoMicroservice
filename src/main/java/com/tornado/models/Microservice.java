package com.tornado.models;

import org.springframework.stereotype.Component;

@Component
public class Microservice {
	  private Keycloak keycloak;
	  private MySql mysql;
	  private PostgreSql postgresql;
	  private MongoDb mongodb;
	  private Boolean wantToFillDatabase;
	  private Docker docker;
	  
	  public Microservice() {
		  
	  }
	  
	  private Microservice(Builder builder) {
	    this.keycloak = builder.keycloak;
	    this.mysql = builder.mysql;
	    this.postgresql = builder.postgresql;
	    this.mongodb = builder.mongodb;
	    this.wantToFillDatabase = builder.wantToFillDatabase;
	    this.docker = builder.docker;
	  }

	  public static Builder builder() {
	    return new Builder();
	  }

	  public static final class Builder {
		  private Keycloak keycloak;
		  private MySql mysql;
		  private PostgreSql postgresql;
		  private MongoDb mongodb;
		  private Boolean wantToFillDatabase;
		  private Docker docker;
			  
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
	    public Builder wantToFillDatabase(Boolean wantToFillDatabase) {
	    	this.wantToFillDatabase = wantToFillDatabase;
	    	return this;
	    }
	    public Builder withDocker(Docker docker) {
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
		 * @return the databaseFiller
		 */
		public Boolean getDatabaseFiller() {
			return wantToFillDatabase;
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

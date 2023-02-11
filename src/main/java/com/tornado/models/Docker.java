package com.tornado.models;

public class Docker {
		  private String keycloak;
		  private String mysql;
		  private String postgresql;
		  private String mongodb;
		  private String network;

		  private Docker(Builder builder) {
		    this.keycloak = builder.keycloak;
		    this.mysql = builder.mysql;
		    this.postgresql = builder.postgresql;
		    this.mongodb = builder.mongodb;
		    this.network = builder.network;
		  }

		  public static Builder builder() {
		    return new Builder();
		  }

		  public static final class Builder {
			  private String keycloak;
			  private String mysql;
			  private String postgresql;
			  private String mongodb;
			  private String network;

		    private Builder() {
		    }

		    public Builder withKeycloak(String keycloak) {
		      this.keycloak = keycloak;
		      return this;
		    }

		    public Builder withMySql(String mysql) {
		      this.mysql = mysql;
		      return this;
		    }

		    public Builder withPostgresql(String postgresql) {
		      this.postgresql = postgresql;
		      return this;
		    }

		    public Builder withMongodb(String mongodb) {
			      this.mongodb = mongodb;
			      return this;
			    }
		    public Builder withNetwork(String network) {
			      this.network = network;
			      return this;
			    }

		    public Docker build() {
		      return new Docker(this);
		    }
		  }

		  // getters and setters
	
}

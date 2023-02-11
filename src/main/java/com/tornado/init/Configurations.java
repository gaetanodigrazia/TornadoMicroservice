package com.tornado.init;

public class Configurations {
		  private String keycloak;
		  private String mysql;
		  private String postgresql;
		  private String mongodb;

		  private Configurations(Builder builder) {
		    this.keycloak = builder.keycloak;
		    this.mysql = builder.mysql;
		    this.postgresql = builder.postgresql;
		    this.mongodb = builder.mongodb;
		  }

		  public static Builder builder() {
		    return new Builder();
		  }

		  public static final class Builder {
			  private String keycloak;
			  private String mysql;
			  private String postgresql;
			  private String mongodb;

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

		    public Configurations build() {
		      return new Configurations(this);
		    }
		  }

		  // getters and setters
	
}

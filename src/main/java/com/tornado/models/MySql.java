package com.tornado.models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

import com.tornado.models.CRUDComponent.CRUDBuilder;
import com.tornado.models.enumerations.DDLAuto;
import com.tornado.models.enumerations.DatabasePlatforms;

//spring.datasource.url=jdbc:mysql://${DB_HOST:127.0.0.1}:${DB_PORT:3307}/${DB_NAME:filterProject}
//spring.datasource.username=${DB_USERNAME:root}
//spring.datasource.password=${DB_PASSWORD:root}
//		spring.jpa.hibernate.ddl-auto = create-drop
//		spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
//		spring.jpa.database-platform = org.hibernate.dialect.MySQL8Dialect
//		spring.jpa.generate-ddl=true
//		spring.jpa.hibernate.ddl-auto = update

public class MySql {
	private String databaseUrl;
	private String databasePort;
	private String databaseUsername;
	private String databasePassword;
	private String databasePlatform;
	private Boolean generateDdl;
	private String ddlAuto;
	private Boolean isYml;

	private Map<DatabasePlatforms, String> databasePlatformsMap;
	private Map<DDLAuto, String> ddlAutoMap;

	public MySql(MySqlBuilder mySqlBuilder) {
		this.databaseUrl = mySqlBuilder.databaseUrl;
		this.databasePort = mySqlBuilder.databasePort;
		this.databaseUsername = mySqlBuilder.databaseUsername;
		this.databasePassword = mySqlBuilder.databasePassword;
		this.databasePlatform = mySqlBuilder.databasePlatform;
		this.generateDdl = mySqlBuilder.generateDdl;
		this.ddlAuto = mySqlBuilder.ddlAuto;
		this.isYml = mySqlBuilder.isYml;
	}

	public static class MySqlBuilder {
		private String databaseUrl;
		private String databasePort;
		private String databaseUsername;
		private String databasePassword;
		private String databasePlatform;
		private Boolean generateDdl;
		private String ddlAuto;
		private Boolean isYml;


		public MySqlBuilder() {

		}

		public MySqlBuilder withDatabaseUrl(String databaseUrl) {
			this.databaseUrl = databaseUrl;
			return this;
		}

		public MySqlBuilder withDatabasePort(String databasePort) {
			this.databaseUrl = databaseUrl;
			return this;
		}

		public MySqlBuilder withDatabaseUsername(String databaseUsername) {
			this.databaseUrl = databaseUrl;
			return this;
		}

		public MySqlBuilder withDatabasePassword(String databasePassword) {
			this.databaseUrl = databaseUrl;
			return this;
		}

		public MySqlBuilder withDatabasePlatform(DatabasePlatforms databasePlatform) {
			this.databaseUrl = databaseUrl;
			return this;
		}

		public MySqlBuilder wantToGenerateDDL(Boolean generateDdl) {
			this.databaseUrl = databaseUrl;
			return this;
		}

		public MySqlBuilder withDDL(DDLAuto ddlAuto) {
			this.databaseUrl = databaseUrl;
			return this;
		}

		public MySqlBuilder withPropertiesInYml(Boolean isYml) {
			this.databaseUrl = databaseUrl;
			return this;
		}
		
		public MySql build() throws IOException {
			Properties prop = new Properties();
			InputStream in = new FileInputStream("src/main/resources/application.properties");
			prop.load(in);
			prop.setProperty("spring.datasource.url", "newvalue");
			prop.setProperty("spring.datasource.username", "newvalue");
			prop.setProperty("spring.datasource.password", "newvalue");
			prop.setProperty("spring.jpa.database-platform", "newvalue");
			prop.setProperty("spring.jpa.generate-ddl", "newvalue");
			prop.setProperty("spring.jpa.hibernate.ddl-auto", "newvalue");
			prop.setProperty("spring.datasource.driver-class-name", "newvalue");
			prop.store(new FileOutputStream("src/main/resources/application.properties"), null);	        
			return new MySql(this);
		}
	}
}

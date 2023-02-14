package com.tornado.models;

import com.tornado.init.InitApi;

import io.swagger.v3.oas.annotations.info.Contact;

import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class Documentation {
	private String basePackage;
	private String title;
	private String description;
	private String version;
	private String serverUrl;
	private String serverDescription;
	private String contactName;
	private String contactEmail;
	private String contactUrl;
	
	
	/**
	 * @return the stringPackage
	 */
	public String getBasePackage() {
		return basePackage;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @return the serverUrl
	 */
	public String getServerUrl() {
		return serverUrl;
	}
	/**
	 * @return the serverDescription
	 */
	public String getServerDescription() {
		return serverDescription;
	}
	
	
	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * @return the contactEmail
	 */
	public String getContactEmail() {
		return contactEmail;
	}
	/**
	 * @return the contactUrl
	 */
	public String getContactUrl() {
		return contactUrl;
	}
	public Documentation(DocumentationBuilder documentationBuilder) {
		this.basePackage = documentationBuilder.basePackage;
		this.title = documentationBuilder.title;
		this.description = documentationBuilder.description;
		this.version = documentationBuilder.version;
		this.serverUrl = documentationBuilder.serverUrl;
		this.serverDescription = documentationBuilder.serverDescription;
		this.contactName = documentationBuilder.contactName;
		this.contactEmail = documentationBuilder.contactEmail;
		this.contactUrl = documentationBuilder.contactUrl;
	}




	public static class DocumentationBuilder {
		private String basePackage;
		private String title;
		private String description;
		private String version;
		private String serverUrl;
		private String serverDescription;
		private String contactName;
		private String contactEmail;
		private String contactUrl;
		
		public DocumentationBuilder() {

		}
		
		public DocumentationBuilder withBasePackage(String basePackage) {
			this.basePackage= basePackage;
			return this;
		}
		
		public DocumentationBuilder withTitle(String title) {
			this.title = title;
			return this;
		}
		public DocumentationBuilder withDescription(String description) {
			this.description = description;
			return this;
		}
		
		public DocumentationBuilder withVersion(String version) {
			this.version = version;
			return this;
		}
		public DocumentationBuilder withServerUrl(String serverUrl) {
			this.serverUrl = serverUrl;
			return this;
		}
		
		public DocumentationBuilder withServerDescription(String serverDescription) {
			this.serverDescription = serverDescription;
			return this;
		}
		public DocumentationBuilder withContactName(String contactName) {
			this.contactName = contactName;
			return this;
		}
		public DocumentationBuilder withContactEmail(String contactEmail) {
			this.contactEmail = contactEmail;
			return this;
		}
		public DocumentationBuilder withContactUrl(String contactUrl) {
			this.contactUrl = contactUrl;
			return this;
		}
		
		public Documentation build() throws IOException {
			return new Documentation(this);
		}
	}

}

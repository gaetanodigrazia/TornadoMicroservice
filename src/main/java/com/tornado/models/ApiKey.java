package com.tornado.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ApiKey {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long serialKey;
	
	private Integer availableRequests;
	
	private Boolean isActive;
	
	private Long userId;
	
	public Long getSerialKey() {
		return this.serialKey;
	}
	
	public Integer getAvailableRequests() {
		return availableRequests;
	}

	public void setAvailableRequests(Integer availableRequests) {
		this.availableRequests = availableRequests;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setSerialKey(Long serialKey) {
		this.serialKey = serialKey;
	}
	
}

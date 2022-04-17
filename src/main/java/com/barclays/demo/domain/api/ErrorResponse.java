package com.barclays.demo.domain.api;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse implements Serializable {
	
	public ErrorResponse() {
		super();
	}

	private static final long serialVersionUID = 1L;

	private String message;

	private String type;

	private String invavlidValue;

	private int statusCode;

	@Override
	public String toString() {
		return "ErrorResponse [message=" + message + ", type=" + type + ", invavlidValue=" + invavlidValue
				+ ", statusCode=" + statusCode + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInvavlidValue() {
		return invavlidValue;
	}

	public void setInvavlidValue(String invavlidValue) {
		this.invavlidValue = invavlidValue;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ErrorResponse(String message, String type, String invavlidValue, int statusCode) {
		super();
		this.message = message;
		this.type = type;
		this.invavlidValue = invavlidValue;
		this.statusCode = statusCode;
	}
}

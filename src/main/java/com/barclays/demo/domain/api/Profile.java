package com.barclays.demo.domain.api;

import java.util.List;

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
public class Profile {

	private List<Users> users;
	 
	private List<ErrorResponse> errors;

	public List<Users> getUsers() {
		return users;
	}

	public Profile() {
		super();
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	public List<ErrorResponse> getErrors() {
		return errors;
	}

	public Profile(List<Users> users, List<ErrorResponse> errors) {
		super();
		this.users = users;
		this.errors = errors;
	}

	public void setErrors(List<ErrorResponse> errors) {
		this.errors = errors;
	}
	
}

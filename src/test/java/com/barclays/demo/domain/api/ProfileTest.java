package com.barclays.demo.domain.api;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ProfileTest {

	Profile profile;
	Profile profile1;
	
	@Test
	public void setUp() {
		profile = new Profile();
		profile1 = new Profile(getProfile().getUsers(), getProfile().getErrors());
	}
	
	@Test
	public void testGetUsers() {
		profile = new Profile();
		profile.setUsers(getProfile().getUsers());
		assertNotNull(profile.getUsers());
	}
	
	@Test
	public void testGetErrors() {
		profile = new Profile();
		profile.setErrors(getProfile().getErrors());
		assertNotNull(profile.getErrors());
	}

	private Profile getProfile() {
		Profile profile = new Profile();
		List<Users> users= new ArrayList<>();
		List<ErrorResponse> list = new ArrayList<>();
		ErrorResponse error = new ErrorResponse("Test", "TEst", "Test", 500);
		list.add(error);
		Users user = new Users();
		user.setVersion("3");
		user.setMaturityDate("16-07-2022");
		user.setExpired("N");
		users.add(user);
		profile.setUsers(users);
		profile.setErrors(list);
		return profile;
	}
}

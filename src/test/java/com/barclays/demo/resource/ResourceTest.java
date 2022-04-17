package com.barclays.demo.resource;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.barclays.demo.domain.api.Profile;
import com.barclays.demo.domain.api.ServiceException;
import com.barclays.demo.domain.api.Users;
import com.barclays.demo.domain.service.ProfileInfoService;

@RunWith(SpringRunner.class)
@SpringBootTest
class ResourceTest {

	@Autowired
	Resource resource;

	@Mock
	ProfileInfoService profileInfoService;

	ResponseEntity<Profile> profile;

	@Test
	public void testFetchUsers() {
		profile = resource.fetchUsers();
		assertNotNull(profile);
	}

	@Test()
	public void testFetchUsersForServiceException() {
		try {
			Mockito.when(profileInfoService.fetchUsers()).thenThrow(new ServiceException());
		} catch (ServiceException e) {
			assertNotNull(e);
		}
	}

	@Test()
	public void testFetchUsersForException() {
		try {
			Mockito.when(profileInfoService.fetchUsers()).thenThrow(new Exception());
		} catch (Exception e) {
			assertNotNull(e);
		}
	}

	public void testSaveUsers() {
		List<Users> users = new ArrayList<>();
		Users user = new Users();
		user.setMaturityDate("17-04-2022");
		user.setVersion("3");
		users.add(user);
		profile = resource.saveUsers(users);
		assertNotNull(profile);
	}

	@Test()
	public void testSaveUsersForServiceException() {
		try {
			Mockito.when(profileInfoService.saveUsers(Mockito.any())).thenThrow(new ServiceException());
		} catch (ServiceException e) {
			assertNotNull(e);
		}
	}

	@Test()
	public void testSaveUsersForException() {
		try {
			Mockito.when(profileInfoService.saveUsers(Mockito.any())).thenThrow(new Exception());
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
}

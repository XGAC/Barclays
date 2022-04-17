package com.barclays.demo.domain.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.barclays.demo.domain.api.Profile;
import com.barclays.demo.domain.api.Users;
import com.barclays.demo.domain.service.ProfileInfoService;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProfileInfoServiceImplTest {

	@Autowired
	private ProfileInfoService profileInfoService;

	@Mock
	ProfileInfoServiceImpl profileInfoServiceImpl;

	@Test
	void testFetchUsers() {
		Profile profile = profileInfoService.fetchUsers();
		assertNotNull(profile);
	}

	@Test
	void testFetchUsersForException() {
		try {
			Mockito.when(profileInfoServiceImpl.getProfileData()).thenThrow(new Exception());
			Profile profile = profileInfoService.fetchUsers();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}

	@Test
	void testSaveUsersForNullUsers() {
		List<Users> users = new ArrayList<>();
		Users user = new Users("Test", "1", "Test", "Test", "Test", "Test", "Test");
		Users user1 = new Users("Test", "2", "Test", "Test", "Test", "Test", "Test");
		users.add(user1);
		users.add(user);
		Profile profile = profileInfoService.saveUsers(null);
		assertEquals(null, profile.getUsers());
	}

	@Test
	void testSaveUsersForUsers() {
		List<Users> users = new ArrayList<>();
		Users user = new Users("T1", "1", "C1", "B1", "18-04-2022", "17-04-2020", "N");
		Users user1 = new Users("T1", "2", "C1", "B1", "17-04-2021", "17-04-2020", "N");
		users.add(user1);
		users.add(user);
		Profile profile = profileInfoService.saveUsers(users);
		assertEquals(1, profile.getUsers().size());
		assertEquals(1, profile.getErrors().size());
	}

	@Test
	void testSaveUsersForNewUsers() {
		List<Users> users = new ArrayList<>();
		Users user = new Users("T1", "1", "C1", "B1", "18-04-2022", "17-04-2020", "N");
		Users user1 = new Users("T1", "3", "C1", "B1", "19-04-2023", "17-04-2020", "N");
		users.add(user1);
		users.add(user);
		Profile profile = profileInfoService.saveUsers(users);
		assertEquals(2, profile.getUsers().size());
	}

}

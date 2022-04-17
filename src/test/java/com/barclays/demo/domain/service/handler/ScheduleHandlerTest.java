package com.barclays.demo.domain.service.handler;

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
import com.barclays.demo.domain.service.impl.ProfileInfoServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class ScheduleHandlerTest {
	
	@Autowired
	ScheduleHandler scheduleHandler;
	
	@Mock
	ProfileInfoServiceImpl profileInfoServiceImpl;

	@Test
	void testSchedulerForOldDate() throws Exception {
		Mockito.when(profileInfoServiceImpl.getProfileData()).thenReturn(getProfile());
		scheduleHandler.scheduler();
	}
	
	@Test
	void testSchedulerForValidDate() throws Exception {
		Profile temp = getProfile();
		temp.getUsers().get(0).setMaturityDate("17-04-2023");
		Mockito.when(profileInfoServiceImpl.getProfileData()).thenReturn(temp);
		scheduleHandler.scheduler();
	}
	
	private Profile getProfile() {
		Profile profile = new Profile();
		List<Users> users= new ArrayList<>();
		Users user = new Users();
		user.setVersion("3");
		user.setMaturityDate("16-07-2022");
		user.setExpired("N");
		users.add(user);
		profile.setUsers(users);
		return profile;
	}

}

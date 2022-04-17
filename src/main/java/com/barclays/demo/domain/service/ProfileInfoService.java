package com.barclays.demo.domain.service;

import java.util.List;

import com.barclays.demo.domain.api.Profile;
import com.barclays.demo.domain.api.Users;

public interface ProfileInfoService {

	Profile fetchUsers();
	
	Profile saveUsers(List<Users> users);
}

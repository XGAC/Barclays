package com.barclays.demo.domain.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.inject.Named;

import org.springframework.core.io.ClassPathResource;

import com.barclays.demo.domain.api.ErrorResponse;
import com.barclays.demo.domain.api.Profile;
import com.barclays.demo.domain.api.ServiceException;
import com.barclays.demo.domain.api.Users;
import com.barclays.demo.domain.service.ProfileInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Named
public class ProfileInfoServiceImpl implements ProfileInfoService {

	public static final String FILE_NAME = "mock/mockData.json";

	@Override
	public Profile fetchUsers() {
		Profile profile = null;
		try {
			profile = getProfileData();
		} catch (Exception e) {
		}
		return profile;
	}

	@Override
	public Profile saveUsers(List<Users> users) throws ServiceException {
		Profile profile = getProfileData();
		Profile output = new Profile();
		if (Objects.nonNull(users)) {
			List<Users> resultUser = new ArrayList<>();
			for (Users tempUser : users) {
				try {
//					/boolean validUser = isValidUser(tempUser);
					System.out.println("profile before::" + profile.getUsers().size());
					storeUser(tempUser, profile.getUsers(), resultUser);
					saveDataInFile(profile);
					System.out.println("profile after::" + profile.getUsers().size());
				} catch (ServiceException e) {
					List<ErrorResponse> errors = output.getErrors();
					if (errors == null) {
						errors = new ArrayList<>();
					}
					errors.add(e.getErrorResponse());
					output.setErrors(errors);
				} catch (Exception e) {
					List<ErrorResponse> errors = output.getErrors();
					if (errors != null) {
						errors = new ArrayList<>();
					}
					errors.add(new ErrorResponse(e.getMessage(), "Internal_Server_Error", "", 500));
					output.setErrors(errors);
				}
			}
			output.setUsers(resultUser);
		}
		return output;
	}

	private void storeUser(Users tempUser, List<Users> list, List<Users> resultUser) throws Exception {
		// boolean flag= ;
		if (!isValidUser(tempUser)) {
			Users output = list.stream().filter(user -> user.getVersion().equals(tempUser.getVersion())).findAny()
					.orElse(null);
			if (output == null) {
				list.add(tempUser);
			} else {
				list.stream().filter(user -> user.getVersion().equals(tempUser.getVersion())).forEach(temp -> {
					temp.setBookId(tempUser.getBookId());
					temp.setCounterPartyId(tempUser.getCounterPartyId());
					temp.setCreatedDate(tempUser.getCreatedDate());
					temp.setMaturityDate(tempUser.getMaturityDate());
					temp.setTradeId(tempUser.getTradeId());
				});
			}
			resultUser.add(list.stream().filter(user -> user.getVersion().equals(tempUser.getVersion())).findAny()
					.orElse(null));
		} else {
			ErrorResponse error = new ErrorResponse("Maturity Date is Expired for Version " + tempUser.getVersion(), "Partial Error", "Maturity_Date", 207);
			throw new ServiceException(error, "Maturity Date is Expired");
		}
	}

	private boolean isValidUser(Users user) throws Exception {
		boolean flag = false;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = sdf.parse(user.getMaturityDate());
		LocalDateTime current = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String currentDate = current.format(format);
		Date todayDate = sdf.parse(currentDate);
		if (date.before(todayDate)) {
			flag = true;
		}
		System.out.println("date ::" + flag);
		return flag;
	}

	public Profile getProfileData() {
		try (InputStream stream = new ClassPathResource(FILE_NAME).getInputStream()) {
			System.out.println("ProfileInfoServiceImpl::extracing stream" + stream);
			Profile profile = new ObjectMapper().readValue(stream, Profile.class);
			System.out.println("ProfileInfoServiceImpl::extracing profile" + profile + stream);
			return profile;
		} catch (Exception e) {
			ErrorResponse error = new ErrorResponse("Data doesn't Exist", "Internal Server Error", "", 500);
			throw new ServiceException(error, e.getMessage());
		}
	}

	public void saveDataInFile(Profile profile) throws IOException {
		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = Obj.writeValueAsString(profile);
		System.out.println(jsonStr);
		FileWriter file = new FileWriter("./src/main/resources/mock/mockData.json");
		file.write(jsonStr);
		file.close();
	}
}

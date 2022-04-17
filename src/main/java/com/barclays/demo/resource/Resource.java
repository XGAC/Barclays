package com.barclays.demo.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.demo.domain.api.Profile;
import com.barclays.demo.domain.api.ServiceException;
import com.barclays.demo.domain.api.Users;
import com.barclays.demo.domain.service.ProfileInfoService;

@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@RestController
public class Resource {

	private ProfileInfoService profileInfoService;

	@Context
	private ContainerRequestContext containerRequestContext;

	@Inject
	public Resource(ProfileInfoService profileInfoService) {
		this.profileInfoService = profileInfoService;
	}

	@GetMapping("/listAllUsers")
	public ResponseEntity<Profile> fetchUsers() {
		Profile profile = null;
		try {
			profile = profileInfoService.fetchUsers();
		} catch (ServiceException exception) {

		} catch (Exception exception) {

		}
		return ResponseEntity.ok().body(profile);
	}

	@PutMapping("/")
	public ResponseEntity saveUsers(@RequestBody List<Users> users) {
		Profile output = null;
		try {
			System.out.println("inside function");
			output = profileInfoService.saveUsers(users);
			System.out.println("function complete: " + output.getUsers());
			;
		} catch (ServiceException exception) {

		} catch (Exception exception) {

		}
		return new ResponseEntity<>(output, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

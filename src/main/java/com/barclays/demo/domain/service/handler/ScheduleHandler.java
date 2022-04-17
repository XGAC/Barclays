package com.barclays.demo.domain.service.handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.barclays.demo.domain.api.Profile;
import com.barclays.demo.domain.service.impl.ProfileInfoServiceImpl;

@Component
@EnableScheduling
@EnableAsync
public class ScheduleHandler {

	@Inject
	private ProfileInfoServiceImpl profileInfoService;

	@Async
	@Scheduled(cron = "0 0 0 * * *", zone = "Indian/Maldives")
	public void scheduler() throws Exception {
		Profile profile = profileInfoService.getProfileData();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		LocalDateTime current = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String currentDate = current.format(format);
		Date todayDate = sdf.parse(currentDate);
		profile.getUsers().stream().forEach(user -> {
			Date date;
			try {
				date = sdf.parse(user.getMaturityDate());
				if (date.before(todayDate)) {
					user.setExpired("Y");
				}
			} catch (ParseException e) {
			}
		});
		profileInfoService.saveDataInFile(profile);
	}

}

package com.barclays.demo.domain.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UsersTest {

	Users user;
	Users user1;
	
	@Test
	public void test() {
		user = new Users();
		user1 = new Users("Test", "Test", "Test", "Test", "Test", "Test", "Test");
	}
	
	@Test
	public void getTradeId() {
		user = new Users();
		user.setTradeId("Test");
		assertEquals("Test", user.getTradeId());
	}

	@Test
	public void getVersion() {
		user = new Users();
		user.setVersion("Test");
		assertEquals("Test", user.getVersion());
	}

	@Test
	public void getCounterPartyId() {
		user = new Users();
		user.setCounterPartyId("Test");
		assertEquals("Test", user.getCounterPartyId());
	}

	@Test
	public void getBookId() {
		user = new Users();
		user.setBookId("Test");
		assertEquals("Test", user.getBookId());
	}

	@Test
	public void getMaturityDate() {
		user = new Users();
		user.setMaturityDate("Test");
		assertEquals("Test", user.getMaturityDate());
	}

	@Test
	public void getCreatedDate() {
		user = new Users();
		user.setCreatedDate("Test");
		assertEquals("Test", user.getCreatedDate());
	}

	@Test
	public void getExpired() {
		user = new Users();
		user.setExpired("Test");
		assertEquals("Test", user.getExpired());
	}
}

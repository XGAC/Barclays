package com.barclays.demo.domain.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ErrorResponseTest {

	ErrorResponse error;
	ErrorResponse error1;
	
	@Test
	public void test() {
		error = new ErrorResponse();
		error1 = new ErrorResponse("Test", "TEst", "Test", 500);
	}
	
	@Test
	public void testGetMessage() {
		error = new ErrorResponse();
		error.setMessage("Test");
		assertEquals("Test", error.getMessage());
	}
	
	@Test
	public void testGetType() {
		error = new ErrorResponse();
		error.setInvavlidValue("Test");
		assertEquals("Test", error.getInvavlidValue());
	}
	
	@Test
	public void testGetInvalidValue() {
		error = new ErrorResponse();
		error.setType("Test");
		assertEquals("Test", error.getType());
	}
	
	@Test
	public void testGetStatus() {
		error = new ErrorResponse();
		error.setStatusCode(500);
		assertEquals(500, error.getStatusCode());
	}

}

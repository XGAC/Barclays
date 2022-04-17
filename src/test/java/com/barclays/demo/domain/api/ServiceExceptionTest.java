package com.barclays.demo.domain.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ServiceExceptionTest {

	ServiceException error;
	ServiceException error1;
	ServiceException error2;
	ServiceException error3;
	
	@Test
	public void test() {
		error = new ServiceException();
		error1 = new ServiceException(new Exception());
		error2 = new ServiceException("Test");
		error3 = new ServiceException(new ErrorResponse(), "Test");
	}
	
	@Test
	public void testGetErrorResponse() {
		ErrorResponse errorResponse = new ErrorResponse("Test", "TEst", "Test", 500);
		error = new ServiceException(errorResponse, "Test");
		assertEquals(errorResponse, error.getErrorResponse());
	}

}

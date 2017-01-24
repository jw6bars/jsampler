package com.jsampler.pwvalidator.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PasswordValidatorTest {

	@Autowired
	private PasswordValidator passwordValidator;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidatePassword_Valid() {
		String[] validTestCases = {
				"a1234",
				"a12345",
				"a123456",
				"a123x1234",
				"a1234567890",
				"a12345678901"
			};
			for (String testCase : validTestCases) {
				PasswordValidationResponse resp = passwordValidator.validatePassword(testCase);
				System.out.format("valid   case: %25s -> %b %s\n", testCase, resp.isValid(), resp.getReason());
				assertTrue(testCase, resp.isValid());
				assertNull(resp.getReason());
			}
	}

	@Test
	public void testValidatePassword_NotValid() {
		String[] validTestCases = {
				"a12",
				"a123",
				"12345678",
				"abcdefgh",
				"a123456789012",
				"12345678+",
				" a1234 ",
				" ",
				"",
				null
			};
			for (String testCase : validTestCases) {
				PasswordValidationResponse resp = passwordValidator.validatePassword(testCase);
				System.out.format("valid   case: %25s -> %b %s\n", testCase, resp.isValid(), resp.getReason());
				assertFalse(testCase, resp.isValid());
				assertNotNull(resp.getReason());
			}
	}

}

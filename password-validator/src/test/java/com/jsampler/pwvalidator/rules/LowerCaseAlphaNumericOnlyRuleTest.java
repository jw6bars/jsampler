package com.jsampler.pwvalidator.rules;

import static org.junit.Assert.*;

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
public class LowerCaseAlphaNumericOnlyRuleTest {
	
	@Autowired
	private LowerCaseAlphaNumericOnlyRule rule;

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
	public void testIsCompliant_Valid() {
		String[] validTestCases = {
			"abcdef0",
			"0abcdef",
			"abc0def",
			"a123456",
			"123456a",
			"123a456",
			"aaaaaa0aaaaaa",
			"123456a789012"
		};
		for (String testCase : validTestCases) {
			boolean result = rule.isCompliant(testCase);
//			System.out.format("valid   case: %25s = %b\n", testCase, result);
			assertTrue(testCase, result);
		}
	}

	@Test
	public void testIsCompliant_InValid() {
		String[] invalidTestCases = {
			"",
			"abcdef",
			"123456",
			"aaaaaaaaaaaa",
			"123456789012",
			" a1234",
			"a1234 ",
			"a12 456",
			"$a1234",
			"a1234$",
			"a12$456",
			null
		};
		for (String testCase : invalidTestCases) {
			boolean result = rule.isCompliant(testCase);
//			System.out.format("invalid case: %25s = %b\n", testCase, result);
			assertFalse(testCase, result);
		}
	}
	
	@Test
	public void testRuleDescription() {
		assertEquals(LowerCaseAlphaNumericOnlyRule.ruleDescription, rule.getRuleDescription());
	}

}

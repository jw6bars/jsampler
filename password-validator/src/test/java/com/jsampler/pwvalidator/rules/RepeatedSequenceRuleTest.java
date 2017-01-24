package com.jsampler.pwvalidator.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
public class RepeatedSequenceRuleTest {

	@Autowired
	private RepeatedSequenceRule rule;

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
			"012abcdeXabcde567",
			"",
			" ",
			"a",
			"01234abcdefg56789"
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
			"01234abcdeabcde56789",
			"aa",
			"abcdeff",
			"01234aa56789",
			"01234abcdeabcde56789",
			"abaa",
			"12abaa34",
			"12abcaXYZabab34",
			"12abcaXYZabcabc34",
			"  ",
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
		assertEquals(RepeatedSequenceRule.ruleDescription, rule.getRuleDescription());
	}

}

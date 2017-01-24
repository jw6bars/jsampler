package com.jsampler.pwvalidator;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hamcrest.core.IsNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.jsampler.pwvalidator.model.Password;
import com.jsampler.pwvalidator.service.PasswordValidatorService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PasswordControllerTest {
	
	@Autowired
	private PasswordController passwordController;
	
	private MockMvc mockMvc;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("x"); // avoid circular reference
        mockMvc = MockMvcBuilders.standaloneSetup(passwordController)
                                 .setViewResolvers(viewResolver)
                                 .build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPasswordController_GET() throws Exception {
        mockMvc.perform(get("/password"))
        .andExpect(status().isOk())
        .andExpect(view().name("password"))
        .andExpect(model().attribute("password", hasProperty("password", IsNull.nullValue())))
        .andExpect(model().attribute("password", hasProperty("valid", is(false))))
        .andExpect(forwardedUrl("xpassword"))
        ;
	}
	
	@Test
	public void testPasswordController_POST_isCompliant() throws Exception {
		String validTestPassword = "a123456";
		Password mockPassword = mock(Password.class);
		 mockMvc.perform(post("/password")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .contentType(MediaType.APPLICATION_JSON)
                .sessionAttr("password", mockPassword)
                .param("password", validTestPassword)
	        )
            .andExpect(status().isOk())
            .andExpect(view().name("validation"))
            .andExpect(forwardedUrl("xvalidation"))
            .andExpect(model().attribute("password", hasProperty("valid", is(true))))
            .andExpect(model().attribute("password", hasProperty("password", is(validTestPassword))))
            ;
	}
	
	@Test
	public void testPasswordController_POST_nonCompliant_LengthRule() throws Exception {
		String tooShortPassword = "a12";
		Password mockPassword = mock(Password.class);
		 mockMvc.perform(post("/password")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .contentType(MediaType.APPLICATION_JSON)
                .sessionAttr("password", mockPassword)
                .param("password", tooShortPassword)
	        )
            .andExpect(status().isOk())
            .andExpect(view().name("validation"))
            .andExpect(forwardedUrl("xvalidation"))
            .andExpect(model().attribute("password", hasProperty("password", is(tooShortPassword))))
            .andExpect(model().attribute("password", hasProperty("valid", is(false))))
            ;
	}
	
	@Test
	public void testPasswordController_POST_nonCompliant_AlphaNumericRule() throws Exception {
		String noNumericPassword = "abcdefg";
		Password mockPassword = mock(Password.class);
		 mockMvc.perform(post("/password")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .contentType(MediaType.APPLICATION_JSON)
                .sessionAttr("password", mockPassword)
                .param("password", noNumericPassword)
	        )
            .andExpect(status().isOk())
            .andExpect(view().name("validation"))
            .andExpect(forwardedUrl("xvalidation"))
            .andExpect(model().attribute("password", hasProperty("password", is(noNumericPassword))))
            .andExpect(model().attribute("password", hasProperty("valid", is(false))))
            ;
	}
	
	@Test
	public void testPasswordController_POST_nonCompliant_SequenceRule() throws Exception {
		String containsSequencePassword = "a12BadBad34";
		Password mockPassword = mock(Password.class);
		 mockMvc.perform(post("/password")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .contentType(MediaType.APPLICATION_JSON)
                .sessionAttr("password", mockPassword)
                .param("password", containsSequencePassword)
	        )
            .andExpect(status().isOk())
            .andExpect(view().name("validation"))
            .andExpect(forwardedUrl("xvalidation"))
            .andExpect(model().attribute("password", hasProperty("password", is(containsSequencePassword))))
            .andExpect(model().attribute("password", hasProperty("valid", is(false))))
            ;
	}
	
}

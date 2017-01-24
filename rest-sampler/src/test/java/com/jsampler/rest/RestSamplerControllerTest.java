package com.jsampler.rest;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.jsampler.rest.Echo;
import com.jsampler.rest.RestSamplerController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestSamplerControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
		mockMvc = null;
	}

	// Test for Not Found
	@Test
	public void testEcho_404() throws Exception {
		String input = "something";
        this.mockMvc
        	.perform(get("/not-there" + input))
        	.andDo(print())
        	.andExpect(status().is4xxClientError())
        	.andExpect(status().isNotFound())
            ;
	}

	// Test GET echo for plain text output - without passing a text parameter
	@Test
	public void testEcho_Usage() throws Exception {
        this.mockMvc
        	.perform(get("/echo"))
        	.andDo(print())
        	.andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().string(RestSamplerController.USAGE_PLAIN))
            ;
	}

	// Test GET echo for plain text output - passing in the text parameter
	@Test
	public void testEcho_Text() throws Exception {
		String input = "something";
        this.mockMvc
        	.perform(get("/echo?text=" + input))
        	.andDo(print())
        	.andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().string(input))
            ;
	}

	// Test GET echo for JSON output - without passing a text parameter
	@Test
	public void testEchoJson_Usage() throws Exception {
        this.mockMvc
        	.perform(get("/echo-json"))
        	.andDo(print())
        	.andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(-1))
            .andExpect(jsonPath("$.text").value(RestSamplerController.USAGE_JSON))
            ;
	}

	// Test GET echo for JSON output - passing in the text parameter
	@Test
	public void testEchoJson_Text() throws Exception {
		String input = "something%20else";
        this.mockMvc
        	.perform(get("/echo-json?text=" + input))
        	.andDo(print())
        	.andExpect(status().isOk())
            .andExpect(jsonPath("$.text").value(input))
            ;
	}

	@Test
	public void testEchoJsonPost_Text() throws Exception {
		int id = 11;
		String input = "something posted";
		Echo echo = new Echo(id,input);
		System.out.println("echo = " + echo.toString());
        this.mockMvc
            .perform(post("/echo-json-post").contentType(MediaType.APPLICATION_JSON).content(echo.toString().getBytes()))
        	.andDo(print())
        	.andExpect(status().isOk())
            .andExpect(jsonPath("$.text").value(input))
            .andExpect(jsonPath("$.id").value(id))
            ;
	}

}

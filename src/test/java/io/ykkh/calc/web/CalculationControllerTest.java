package io.ykkh.calc.web;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import io.ykkh.calc.common.CalcAppConstants;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculationControllerTest {

	@Autowired
	MockMvc mockMvc;

	void testHappyPath(String op) throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v2/calc?a=3&b=2&op="+op))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);
	}
	
	@Test
	void testAdd() throws Exception  {
		testHappyPath(CalcAppConstants.ADDITION);
	}
	
	@Test
	void testSub() throws Exception {
		testHappyPath(CalcAppConstants.SUBSTRACTION);
	}
	
	@Test
	void testMul() throws Exception {
		testHappyPath(CalcAppConstants.MULTIPLICATION);
	}
	
	@Test
	void testDivl() throws Exception {
		testHappyPath(CalcAppConstants.DIVISION);
	}
	
	/** Validation Test **/

	@Test
	void testMissingA() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v2/calc?b=2&op=mul"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);
	}
	
	@Test
	void testMissingB() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v2/calc?a=2&op=mul"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);
	}

	@Test
	void testMissingOperator() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v2/calc?a=2&b=2"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);
	}
	
	@Test
	void testMissingAll() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v2/calc"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);
	}
	
	@Test
	void testWrongDataType() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v2/calc?a=asdf&b=23&op=mul"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);
	}
	
	@Test
	void testWrongOperator() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v2/calc?a=1&b=3&op=test"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);
	}
	
	@Test
	void testDivisionByZero() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v2/calc?a=1&b=0&op=div"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);
	}


}

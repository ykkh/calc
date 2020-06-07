package io.ykkh.calc.web;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.ykkh.calc.common.CalcAppConstants;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculationControllerTest {

	@Autowired
	MockMvc mockMvc;

	void testHappyPath(String op) throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v2/calc?a=3&b=2&op="+op))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK")).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);
		String jsonString = asJsonString(new CalculationRequest("1", "1", CalcAppConstants.ADDITION));
		result  =	 mockMvc.perform(MockMvcRequestBuilders.post("/v2/calc").contentType(MediaType.APPLICATION_JSON).content(jsonString).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
	
	System.out.println(result.getResponse().getContentAsString());
	}

	@Test
	void testAdd() throws Exception {
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
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST")).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);
	}

	@Test
	void testMissingB() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v2/calc?a=2&op=mul"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST")).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);
	}

	@Test
	void testMissingOperator() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v2/calc?a=2&b=2"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST")).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);
	}

	@Test
	void testMissingAll() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v2/calc"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST")).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);
	}

	@Test
	void testWrongDataType() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v2/calc?a=asdf&b=23&op=mul"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST")).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);
	}

	@Test
	void testWrongOperator() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v2/calc?a=1&b=3&op=test"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST")).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);
	}

	@Test
	void testDivisionByZero() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v2/calc?a=1&b=0&op=div"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST")).andReturn();
		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);
	}
	
	public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            System.out.println(jsonContent);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

package com.capgemini.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;

import com.capgemini.controller.MechanicsController;
import com.capgemini.entities.Mechanics;
import com.capgemini.service.IMechanicsService;
import com.capgemini.service.MechanicsServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MechanicsController.class)
class MechanicsControllerTest {
	@Autowired
     private MockMvc mockMvc;
	@MockBean
	private MechanicsServiceImpl mechanicsService;
	@Test
	void MechanicsControllerGetTest() throws Exception {
		Mechanics mechanics = new Mechanics();
		mechanics.setMechanicsName("santhosh reddy");
		mechanics.setMechanicsMobile("7542290617");
		mechanics.setMechanicsAge(30);
		
		// LEARNING 2
		// providing the implementation of findById method.
		// mocking the findById method on MockObject employeeService
		Mockito.when(mechanicsService.findMechanicsbyId(Mockito.anyInt())).thenReturn(mechanics);
		
		mockMvc.perform(get("/api/mechanics/1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.mechanicsName").value("santhosh reddy"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.mechanicsMobile").value("7542290617"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.mechanicsAge").value(30));
	}
}

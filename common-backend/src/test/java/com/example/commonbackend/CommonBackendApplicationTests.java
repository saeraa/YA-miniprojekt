package com.example.commonbackend;

import com.example.commonbackend.model.Customer;
import com.example.commonbackend.model.Order;
import com.example.commonbackend.model.ProductDetailed;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class CommonBackendApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Test
	void getOrders() throws Exception {
		MvcResult result = mockMvc.perform(get("/orders"))
				.andExpect(status().isOk())
				.andReturn();

		ObjectMapper mapper = new ObjectMapper();
		List<Order> actual = mapper
				.readValue(result
								.getResponse()
								.getContentAsString(),
						new TypeReference<List<Order>>() {
						});
	}

	@Test
	void postOrder() throws	Exception {
		this.mockMvc.perform(post("/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n" +
						"    \"customerID\": \"VINET\"\n" +
						"}"))
				.andExpect(status().isCreated());
	}

	@Test
	void getCustomers() throws Exception {
		MvcResult result = mockMvc.perform(get("/customers"))
				.andExpect(status().isOk())
				.andReturn();

		ObjectMapper mapper = new ObjectMapper();
		List<Customer> actual = mapper
				.readValue(result
						.getResponse()
						.getContentAsString(),
						new TypeReference<List<Customer>>() {
				});
	}

	@Test
	void getProducts() throws Exception {
		MvcResult result = mockMvc.perform(get("/productsdetailed"))
				.andExpect(status().isOk())
				.andReturn();

		ObjectMapper mapper = new ObjectMapper();
		List<ProductDetailed> actual = mapper
				.readValue(result
								.getResponse()
								.getContentAsString(),
						new TypeReference<List<ProductDetailed>>() {
						});
	}

}

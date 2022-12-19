package com.example.recommendation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RecommendationApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getRecommendations () throws Exception {
		this.mockMvc
				.perform(get("/recommendations"))
				.andDo((print()))
				.andExpect(status().isOk());
	}

	@Test
	void getRecommendationsForProductID () throws Exception {
		this.mockMvc
				.perform(get("/recommendations/2"))
				.andDo((print()))
				.andExpect(status().isOk());
	}

	@Test
	void addRecommendation () throws Exception {
		this.mockMvc
				.perform(post("/recommendation")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"rating\": 5,\n" +
								"    \"comment\": \"meep\",\n" +
								"    \"productId\": 2,\n" +
								"    \"email\": \"an@email.address\"\n" +
								"}"))
				.andDo((print()))
				.andExpect(status().isOk());
	}

	@Test
	void addFaultyRecommendation () throws Exception {
		this.mockMvc
				.perform(post("/recommendation")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"rating\": 546342,\n" +
								"    \"comment\": \"meep\",\n" +
								"    \"productId\": 2,\n" +
								"    \"email\": \"an@email.address\"\n" +
								"}"))
				.andDo((print()))
				.andExpect(status().isOk())
				.andExpect(content().string(""));
	}
}

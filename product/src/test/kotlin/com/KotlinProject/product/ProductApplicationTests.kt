package com.KotlinProject.product

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class ProductApplicationTests {

	@Autowired
	private lateinit var mockMvc: MockMvc

	@Test
	fun findAllTest() {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/"))
			.andExpect(MockMvcResultMatchers.status().isOk)
	}

}

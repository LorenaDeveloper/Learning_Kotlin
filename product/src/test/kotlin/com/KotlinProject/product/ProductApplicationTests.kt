package com.KotlinProject.product

import com.KotlinProject.product.Model.Product
import com.KotlinProject.product.Service.ProductService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
class ProductApplicationTests {

	@Autowired
	private lateinit var webApplicationContext : WebApplicationContext

	// initializes MockMvc on request and print every step of each test
	private val mockMvc: MockMvc by lazy {
		MockMvcBuilders.webAppContextSetup(webApplicationContext)
			.alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print()).build()
	}

	@Autowired
	private lateinit var objectMapper: ObjectMapper
	@Autowired
	private lateinit var productService: ProductService

	@Test
	fun findAllTest() {
		var productsFromService:List<Product> = productService.findAll()
		val json = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/"))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andReturn().response.contentAsString

		var products:List<Product> = objectMapper.readValue(json)

		assertThat(products.size).isEqualTo(productsFromService.size)
		assertThat(products.get(0).name).isEqualTo(productsFromService.get(0).name)
		assertThat(products.get(0).price).isEqualTo(productsFromService.get(0).price)
	}

}

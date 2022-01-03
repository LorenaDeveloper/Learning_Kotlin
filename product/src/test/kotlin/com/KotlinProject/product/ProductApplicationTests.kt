package com.KotlinProject.product

import com.KotlinProject.product.Model.Product
import com.KotlinProject.product.Service.ProductService
import com.KotlinProject.product.Utils.body
import com.KotlinProject.product.Utils.bodyTo
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.*

@SpringBootTest
class ProductApplicationTests {

	private val endPoint = "/api/v1/product"

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
		val products:List<Product> = mockMvc.perform(MockMvcRequestBuilders.get("$endPoint/"))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.bodyTo(objectMapper)

		assertThat(products.size).isEqualTo(productsFromService.size)
		assertThat(products.get(0).name).isEqualTo(productsFromService.get(0).name)
		assertThat(products.get(0).price).isEqualTo(productsFromService.get(0).price)
	}

	@Test
	fun findByIdTest(){
		var productsFromService:List<Product> = productService.findAll()
		assert(!productsFromService.isEmpty()){ "It shouldn't be empty" }
		val product = productsFromService.first()

		mockMvc.perform(MockMvcRequestBuilders.get("$endPoint/${product.name}"))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andExpect(jsonPath("$.name").value(product.name));
	}

	@Test
	fun findByIdTestWhenNotFound(){
		var productsFromService:List<Product> = productService.findAll()
		assert(!productsFromService.isEmpty()){ "It shouldn't be empty" }
		val product = productsFromService.first()

		//${UUID.randomUUID()} generate random id
		mockMvc.perform(MockMvcRequestBuilders.get("$endPoint/${UUID.randomUUID()}"))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andExpect(jsonPath("$").doesNotExist())
	}

	@Test
	fun saveTest(){
		val product = Product("pear", 1.55)
		val result:Boolean = mockMvc.perform(MockMvcRequestBuilders.post(endPoint)
			.content(objectMapper.writeValueAsBytes(product))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.bodyTo(objectMapper)

		assert(result)
	}

	@Test
	fun saveTestWhenException(){
		var productsFromService:List<Product> = productService.findAll()
		assert(!productsFromService.isEmpty()){ "It shouldn't be empty" }
		val product = productsFromService.first()

		val result:Boolean = mockMvc.perform(MockMvcRequestBuilders.post(endPoint)
				//another way to perform it
			.body(data = product, mapper = objectMapper))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.bodyTo(objectMapper)

		assertThat(!result)
	}
}

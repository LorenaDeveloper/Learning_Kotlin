package com.KotlinProject.product

import com.KotlinProject.product.Model.Product
import com.KotlinProject.product.Service.ProductService
import com.KotlinProject.product.Service.ProviderService
import com.KotlinProject.product.Utils.body
import com.KotlinProject.product.Utils.bodyTo
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
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
	@Autowired
	private lateinit var providerService: ProviderService

	private val defaultProvider by lazy{
		providerService.findAll().first()
	}

	@Test
	fun findAllTest() {
		val productsFromService:List<Product> = productService.findAll()
		val products:List<Product> = mockMvc.perform(MockMvcRequestBuilders.get("$endPoint/"))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.bodyTo(objectMapper)

		assertThat(products.size).isEqualTo(productsFromService.size)
		assertThat(products.get(0).name).isEqualTo(productsFromService.get(0).name)
		assertThat(products.get(0).price).isEqualTo(productsFromService.get(0).price)
	}

	@Test
	fun findByIdTest(){
		val productsFromService:List<Product> = productService.findAll()
		assert(productsFromService.isNotEmpty()){ "It shouldn't be empty" }
		val product = productsFromService.first()

		mockMvc.perform(MockMvcRequestBuilders.get("$endPoint/${product.name}"))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andExpect(jsonPath("$.name").value(product.name));
	}

	@Test
	fun findByIdTestWhenNotFound(){
		val productsFromService:List<Product> = productService.findAll()
		assert(productsFromService.isNotEmpty()){ "It shouldn't be empty" }
		val product = productsFromService.first()

		//${UUID.randomUUID()} generate random id
		mockMvc.perform(MockMvcRequestBuilders.get("$endPoint/${UUID.randomUUID()}"))
			.andExpect(MockMvcResultMatchers.status().isNotFound)
			.andExpect(jsonPath("$").doesNotExist())
	}

	@Test
	fun saveTest(){
		val product = Product("something new", 1.55, 100, defaultProvider )
		val productFromApi:Product = mockMvc.perform(MockMvcRequestBuilders.post(endPoint)
			.content(objectMapper.writeValueAsBytes(product))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isCreated)
			.bodyTo(objectMapper)

		assertThat(productService.findAll().contains(productFromApi))
	}

	@Test
	fun saveTestWhenCheckRulesName(){
		mockMvc.perform(MockMvcRequestBuilders.post(endPoint)
			.body(data = Product("", 1.0, provider = defaultProvider), mapper = objectMapper)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isBadRequest)
			.andExpect(jsonPath("$.name").exists())
	}

	@Test
	fun saveTestWhenCheckRulesPrice(){
		mockMvc.perform(MockMvcRequestBuilders.post(endPoint)
			.body(data = Product("pear", -1.0, provider = defaultProvider), mapper = objectMapper)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isBadRequest)
			.andExpect(jsonPath("$.price").exists())
	}

	@Test
	fun saveTestWhenCheckRules(){
		mockMvc.perform(MockMvcRequestBuilders.post(endPoint)
			.body(data = Product("", -1.0, provider = defaultProvider), mapper = objectMapper)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isBadRequest)
			.andExpect(jsonPath("$.name").exists())
			.andExpect(jsonPath("$.price").exists())
	}

	@Test
	fun saveTestWhenDuplicateEntity(){
		val productsFromService:List<Product> = productService.findAll()
		assert(!productsFromService.isEmpty()){ "It shouldn't be empty" }
		val product = productsFromService.first()

		mockMvc.perform(MockMvcRequestBuilders.post(endPoint)
				//another way to perform it
			.body(data = product, mapper = objectMapper))
			.andExpect(MockMvcResultMatchers.status().isConflict)
			.andExpect(jsonPath("$.title", Matchers.`is`("DuplicateKeyException")))
	}

	@Test
	fun saveTestWhenProviderNotFound(){
		val productsFromService:List<Product> = productService.findAll()
		assert(!productsFromService.isEmpty()){ "It shouldn't be empty" }
		val product = productsFromService.first().copy(name = "entity test").apply {
			this.provider.id = 44
		}

		mockMvc.perform(MockMvcRequestBuilders.post(endPoint)
			//another way to perform it
			.body(data = product, mapper = objectMapper))
			.andExpect(MockMvcResultMatchers.status().isConflict)
			.andExpect(jsonPath("$.title", Matchers.`is`("JpaObjectRetrievalFailureException")))
	}

	@Test
	fun updateTest(){
		val productsFromService:List<Product> = productService.findAll()
		assert(!productsFromService.isEmpty()){ "It shouldn't be empty" }
		val product = productsFromService.first().copy(price = 21.45) //copy object's state but allows changing attributes

		val productFromApi:Product = mockMvc.perform(MockMvcRequestBuilders.put(endPoint)
			.body(data = product, mapper = objectMapper))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.bodyTo(objectMapper)

		assertThat(product.name, Matchers.`is`(productFromApi.name))
	}

	@Test
	fun updateTestWhenProductDoesNotExists(){
		val product = Product(name = "something wrong",12.0, 2, defaultProvider)
		mockMvc.perform(MockMvcRequestBuilders.put(endPoint)
			.body(data = product, mapper = objectMapper))
			.andExpect(MockMvcResultMatchers.status().isConflict)
			.andExpect(jsonPath("$.title", Matchers.`is`("EntityNotFoundException")))
	}

	@Test
	fun deleteById(){
		val productsFromService:List<Product> = productService.findAll()
		assert(productsFromService.isNotEmpty()){ "It shouldn't be empty" }
		val product = productsFromService.last()

		val productFromApi:Product = mockMvc.perform(MockMvcRequestBuilders.delete("$endPoint/${product.name}"))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.bodyTo(objectMapper)

		assertThat(!productService.findAll().contains(productFromApi))
	}

	@Test
	fun deleteByIdWhenProductNotFound(){
		mockMvc.perform(MockMvcRequestBuilders.delete("$endPoint/${UUID.randomUUID()}"))
			.andExpect(MockMvcResultMatchers.status().isConflict)
			.andExpect(jsonPath("$.title", Matchers.`is`("EntityNotFoundException")))
	}
}

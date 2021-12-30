package com.KotlinProject.product.Controller

import com.KotlinProject.product.Model.Product
import com.KotlinProject.product.Service.ProductService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//to use abstract class
@RestController
@RequestMapping("/api/v1/product")
class ProductController(productService: ProductService):BasicController<Product, String>(productService)


//@RestController
//@RequestMapping("/api/v1/product")
//class ProductController(@Autowired private val productService: ProductService) { // dependency injection by constructor
//
//    @GetMapping("/")
//    fun findAll() = productService.findAll()
//
//    @GetMapping("/{id}")
//    fun findById(@PathVariable id:String) = productService.findById(id)
//
//    @PostMapping
//    fun addProduct(@RequestBody product: Product ) = productService.save(product)
//
//    @PutMapping
//    fun updateProduct(@RequestBody product: Product ) = productService.update(product)
//
//    @DeleteMapping("/{id}")
//    fun deleteById(@PathVariable id:String) = productService.deleteById(id)
//}

//another way to do it
//@RestController
//@RequestMapping("/api/v1/product")
//class ProductController {
//
//    @Autowired
//    private lateinit var productService: ProductService // 'lateinit' indicates that it'll be initialized later
//
//    @GetMapping("/get")
//    fun findAll() = productService.findAll()
//}



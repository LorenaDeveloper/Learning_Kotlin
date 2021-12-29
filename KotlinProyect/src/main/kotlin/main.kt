fun main(args: Array<String>) {
    println("Hello World!")

    //create an instance of class
    val product = Product("Apple", 1.5);
    println("Product: $product") //imprime el objectId
    println("Product name: ${product.name}, price:  ${product.price}") //print properties

    //to add the properties in different order
    val product1 = Product(price = 2.0, name = "Orange")
    println("Product: $product1") //imprime el objectId
    println("Product name: ${product1.name} , price: ${product1.price}")
    product1.price = 2.1
    println("Precio actualizado: ${product1.name} , price: ${product1.price}")

    //product1.name = null  <-  null not allowed
    product1.price=null
    println("Price updated to null: ${product1.name} , price: ${product1.price}")

    val product2 = Product(name = "Orange")
    println("Product name: ${product2.name} , default price: ${product2.price}")

    //check that two instances with the same data are not the same object because they have different ObjectId
    val product3 = Product(name = "Orange")
    println("product2 == product3 -> ${product2 == product3}")
}

// val -> invariable
// var -> variable
// si no tiene funciones, no es necesario usar {}
// si no tiene atributos, se puede eliminar (val name:String)
// Double? indica con la '?' que puede ser nulo
// para declarar valores por defecto " = valor " en el parámetro del atributo
class Product(val name:String, var price:Double? = 1.5)
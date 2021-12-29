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

    // check that two instances with the same data are not the same object because they have different ObjectId
    // only if data is not added to class; if added -> true
    val product3 = Product(name = "Orange")
    println("product2 == product3 -> ${product2 == product3}")

    //once overwritten the equal to the function... it can be compared only by the name
    product3.price = 10.0
    println("product2 == product3 -> ${product2 == product3}")


}

// val -> invariable
// var -> variable
// if it has no functions, it is not necessary to use {}
// if it has no attributes, (val name:String) can be deleted
// Double? indicates with the '?' that it can be null
// to declare default values " = valor " in the attribute parameter
// data -> it'll overwrites toString, equals and hashCode using attributes in main constructor
data class Product(val name:String, var price:Double? = 1.5){
    override fun equals(other: Any?): Boolean {
        //first verify object not null
        other ?: return false //returns false if null
        if (other === this) return true
        if (this.javaClass != other.javaClass) return false

        // now we're sure we've got 2 objects of the same class
        // cast other to Product type
        other as Product

        // return true/false just by name comparison
        return this.name === other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
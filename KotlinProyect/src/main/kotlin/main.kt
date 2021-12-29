fun main(args: Array<String>) {
    println("Hello World!")

    //creo una instancia de la clase
    val product = Product("Apple", 1.5);
    println("Producto: $product") //imprime el objectId
    println("Nombre del producto: ${product.name}, precio:  ${product.price}") //imprime las propiedades

    //para agregar las propiedades en distinto orden
    val product1 = Product(price = 2.0, name = "Orange")
    println("Producto: $product1") //imprime el objectId
    println("Nombre del producto: ${product1.name} , precio: ${product1.price}")
    product1.price = 2.1
    println("Precio actualizado: ${product1.name} , precio: ${product1.price}")

    //product1.name = null  <-  no admite nulos
    product1.price=null
    println("Precio actualizado a null: ${product1.name} , precio: ${product1.price}")

    val product2 = Product(name = "Orange")
    println("Nombre del producto: ${product2.name} , precio por defecto: ${product2.price}")
}

// val -> invariable
// var -> variable
// si no tiene funciones, no es necesario usar {}
// si no tiene atributos, se puede eliminar (val name:String)
// Double? indica con la '?' que puede ser nulo
// para declarar valores por defecto " = valor " en el parámetro del atributo
class Product(val name:String, var price:Double? = 1.5)
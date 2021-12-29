fun main(args: Array<String>) {
    println("Hello World!")

    //creo una instancia de la clase
    val product = Product("new Product");
    println("Producto : $product") //imprime el objectId
    println("Nombre del producto : ${product.name}")
}

// val -> invariable
// var -> variable
// si no tiene funciones, no es necesario usar {}
// si no tiene atributos, se puede eliminar (val name:String)
class Product(val name:String)
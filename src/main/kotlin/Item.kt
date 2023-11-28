package org.springkotlin

class Item() {
    var name: String = ""
    var price: Double = 0.0
        get() {
            println(
                "Getting price()"
            )
            return field
        }
        set(value) {
            if (value >= 0.0) {
                field = value
            } else {
                throw IllegalArgumentException("Price cannot be negative")
            }
        }

    constructor(_name: String) : this() {
        name = _name
    }

}

fun main() {
    val item = Item("Galaxy S20")
    println("Item name is ${item.name}")
    item.name = "Galaxy S21"
    println("Item name is ${item.name}")
    item.price = -4.0
    println(item.price)
}
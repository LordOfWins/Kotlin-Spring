package org.springkotlin

class Item() {
    var name: String = ""

    constructor(_name: String) : this() {
        name = _name
    }

}

fun main() {
    val item = Item("Galaxy S20")
    println("Item name is ${item.name}")
    item.name = "Galaxy S21"
    println("Item name is ${item.name}")
}
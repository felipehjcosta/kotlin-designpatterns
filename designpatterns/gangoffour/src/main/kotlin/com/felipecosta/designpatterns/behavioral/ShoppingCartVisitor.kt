package com.felipecosta.designpatterns.behavioral

import java.io.PrintStream

interface Element {
    fun accept(visitor: Visitor): Unit
}

class Book(val price: Int, val isbnNumber: String) : Element {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}

class Fruit(val name: String, val weight: Int, val pricePerKg: Int) : Element {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}

interface Visitor {
    fun visit(book: Book): Unit

    fun visit(fruit: Fruit): Unit
}

class ShoppingCartVisitor(printer: PrintStream) : Visitor {

    private val printer = printer;
    private var totalCost: Int = 0

    override fun visit(book: Book) {
        totalCost += book.price
        printer.println("The book with ISBN ${book.isbnNumber} was added to the cart with cost U$ ${book.price}.")
    }

    override fun visit(fruit: Fruit) {
        val fruitCost = fruit.pricePerKg * fruit.weight
        totalCost += fruitCost
        printer.println("The fruit ${fruit.name} was added to the cart with cost U$ $fruitCost.")
    }

    fun printTotalCost() {
        printer.println("The total cost of the cart is U$ $totalCost")
    }
}

fun main(args: Array<String>) {
    val elements: Array<Element> = arrayOf(Book(20, "1234"), Book(40, "5678"),
            Fruit("Banana", 2, 10), Fruit("Apple", 5, 5))

    val visitor: ShoppingCartVisitor = ShoppingCartVisitor(System.out)

    elements.forEach { it.accept(visitor) }
    System.out.println("")
    visitor.printTotalCost()
}
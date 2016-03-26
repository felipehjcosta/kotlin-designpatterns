package com.felipecosta.designpatterns.structural

interface Coffee {
    fun getCost(): Double
    fun getIngredients(): String
}

class SimpleCoffee : Coffee {
    override fun getCost(): Double {
        return 1.0
    }

    override fun getIngredients(): String {
        return "Coffee"
    }
}

abstract class CoffeeDecorator(decoratedCoffee: Coffee) : Coffee {

    private var decoratedCoffee = decoratedCoffee

    override fun getCost(): Double {
        return decoratedCoffee.getCost()
    }

    override fun getIngredients(): String {
        return decoratedCoffee.getIngredients()
    }
}

class CoffeeWithMilk(coffee: Coffee) : CoffeeDecorator(coffee) {

    override fun getCost(): Double {
        return super.getCost() + 0.5
    }

    override fun getIngredients(): String {
        return super.getIngredients() + ", Milk"
    }
}

class CoffeeWithSprinkles(coffee: Coffee) : CoffeeDecorator(coffee) {

    override fun getCost(): Double {
        return super.getCost() + 0.2
    }

    override fun getIngredients(): String {
        return super.getIngredients() + ", Sprinkles"
    }
}

fun Coffee.printInfo() {
    System.out.println("Cost: ${this.getCost()}; Ingredients: ${this.getIngredients()}")
}

fun main(args: Array<String>) {
    System.out.println("Example without Properties:")

    var coffee: Coffee = SimpleCoffee();
    coffee.printInfo()

    coffee = CoffeeWithMilk(coffee)
    coffee.printInfo()

    coffee = CoffeeWithSprinkles(coffee)
    coffee.printInfo()
}
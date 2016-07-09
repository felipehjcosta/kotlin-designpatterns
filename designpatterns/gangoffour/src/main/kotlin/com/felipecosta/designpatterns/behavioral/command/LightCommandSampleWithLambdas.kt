package com.felipecosta.designpatterns.behavioral.command

class SwitchWithLambdas() {
    private var switchUpLambda: () -> Unit = {}
    private var switchDownLambda: () -> Unit = {}

    fun setupCommands(switchUp: () -> Unit, switchDown: () -> Unit) {
        this.switchUpLambda = switchUp
        this.switchDownLambda = switchDown
    }

    fun switchUp() {
        switchUpLambda()
    }

    fun switchDown() {
        switchDownLambda()
    }
}

fun main(args: Array<String>) {
    val lamp = Light()

    val switchWithLambdas = SwitchWithLambdas()

    switchWithLambdas.setupCommands({ lamp.turnOn() }, { lamp.turnOff() })

    switchWithLambdas.switchUp()
    switchWithLambdas.switchDown()
}
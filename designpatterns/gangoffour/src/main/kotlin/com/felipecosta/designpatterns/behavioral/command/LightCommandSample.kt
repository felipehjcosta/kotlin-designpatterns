package com.felipecosta.designpatterns.behavioral.command

interface Command {
    fun execute()
}

class EmptyCommand : Command {
    override fun execute() {

    }
}

class Switch() {
    private var switchUp: Command = EmptyCommand();
    private var switchDown: Command = EmptyCommand();

    fun setupCommands(switchUp: Command, switchDown: Command) {
        this.switchUp = switchUp;
        this.switchDown = switchDown;
    }

    fun switchUp() {
        switchUp.execute()
    }

    fun switchDown() {
        switchDown.execute()
    }
}

class FlipUpCommand(light: Light) : Command {

    private val light = light

    override fun execute() {
        light.turnOn()
    }
}

class FlipDownCommand(light: Light) : Command {

    private val light = light

    override fun execute() {
        light.turnOff()
    }
}

fun main(args: Array<String>) {
    val lamp = Light()
    val switchUp = FlipUpCommand(lamp)
    val switchDown = FlipDownCommand(lamp)

    val switch = Switch()

    switch.setupCommands(switchUp, switchDown)

    switch.switchUp()
    switch.switchDown()
}
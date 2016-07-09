package com.felipecosta.designpatterns.behavioral.strategy

interface Behaviour {
    fun moveCommand(): Int
}

class AgressiveBehaviour : Behaviour {
    override fun moveCommand(): Int {
        println("Agressive Behaviour: if find another robot attack it")
        return 1
    }
}

class DefensiveBehaviour : Behaviour {
    override fun moveCommand(): Int {
        println("Defensive Behaviour: if find another robot run from it")
        return -1
    }
}

class NormalBehaviour : Behaviour {
    override fun moveCommand(): Int {
        println("Normal Behaviour: if find another robot ignore it")
        return 0
    }
}

val EMPTY_BEHAVIOR = object : Behaviour {
    override fun moveCommand(): Int {
        return Int.MAX_VALUE
    }
}

class Robot(val name: String) {
    var behaviour: Behaviour = EMPTY_BEHAVIOR

    fun move() {
        println("$name: Based on current position" +
                "the behaviour object decide the next move:")
        val command = behaviour.moveCommand()
        // ... send the command to mechanisms
        println("\tThe result returned by behaviour object ($command) " +
                "which is sent to the movement mechanisms " +
                "for the robot '$name'")
    }
}


fun main(args: Array<String>) {
    val robot1 = Robot("Big Robot")
    val robot2 = Robot("George v.2.1")
    val robot3 = Robot("R2")

    robot1.behaviour = AgressiveBehaviour()
    robot2.behaviour = DefensiveBehaviour()
    robot3.behaviour = NormalBehaviour()

    sequenceOf(robot1, robot2, robot3).forEach { it.move() }

    println("\r\nNew behaviours: " +
            "\r\n\t'Big Robot' gets really scared" +
            "\r\n\t, 'George v.2.1' becomes really mad because" +
            "it's always attacked by other robots" +
            "\r\n\t and R2 keeps its calm\r\n")

    robot1.behaviour = DefensiveBehaviour()
    robot2.behaviour = AgressiveBehaviour()

    sequenceOf(robot1, robot2, robot3).forEach { it.move() }
}

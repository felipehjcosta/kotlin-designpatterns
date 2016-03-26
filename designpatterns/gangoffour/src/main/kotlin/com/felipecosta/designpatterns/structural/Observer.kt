package com.felipecosta.designpatterns.structural

interface Observer {
    fun update(subject: Subject)
}

open class Subject {

    var state: Int = 0
        set(value) {
            field = value
            notifyObservers()
        }

    val observers: MutableList<Observer> = mutableListOf()

    fun attach(observer: Observer) {
        observers.add(observer)
    }

    fun detach(observer: Observer) {
        observers.remove(observer)
    }

    fun notifyObservers() {
        observers.forEach { it.update(this) }
    }
}

class BinaryObserver : Observer {

    override fun update(subject: Subject) {
        System.out.println("\tBinary String: ${Integer.toBinaryString(subject.state)}")
    }
}

class OctalObserver : Observer {

    override fun update(subject: Subject) {
        System.out.println("\tOctal String: ${Integer.toOctalString(subject.state)}")
    }
}

class HexaObserver : Observer {

    override fun update(subject: Subject) {
        System.out.println("\tHex String: ${Integer.toHexString(subject.state)}")
    }
}

fun main(args: Array<String>) {
    val subject = Subject()

    val binaryObserver = BinaryObserver()
    val octalObserver = OctalObserver()
    val hexaObserver = HexaObserver()
    subject.attach(binaryObserver)
    subject.attach(octalObserver)
    subject.attach(hexaObserver)

    System.out.println("First state change: 15")
    subject.state = 15
    System.out.println("Second state change: 10")
    subject.state = 10

    subject.detach(binaryObserver)
    subject.detach(octalObserver)
    subject.detach(hexaObserver)
}



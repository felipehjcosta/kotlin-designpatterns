package com.felipecosta.designpatterns.creational.singleton

object LifeSingleton {

    fun answerToLifeTheUniverseAndEverything() = someImportantValue

    val someImportantValue: Int
        get() = 42

}

fun main(args: Array<String>) {
    System.out.println("Answer to life the universe and everything: ${LifeSingleton.answerToLifeTheUniverseAndEverything()}")
}

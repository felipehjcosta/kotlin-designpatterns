package com.felipecosta.designpatterns.creational

//awful repetition, but this is to avoid clashing with other patterns - I'm open to sugestions.

abstract class MazeBuilder {

    internal val mazeBeingBuilt = BuilderMaze();

    abstract fun makeRoom(number: Int): BuilderRoom;
    abstract fun makeMazeWall(): BuilderWall;
    abstract fun makeDoor(outRoom: BuilderRoom, inRoom: BuilderRoom): BuilderDoor;

    fun getResult(): BuilderMaze {
        return mazeBeingBuilt;
    }
}

open class MazeDirector(val builder: MazeBuilder) {
    fun Construct() {
        val room1 = builder.makeRoom(1)
        val room2 = builder.makeRoom(2)
        val door = builder.makeDoor(room1, room2)

        builder.mazeBeingBuilt.addRoom(room1)
        builder.mazeBeingBuilt.addRoom(room2)

        room1.setSide(RoomSide.NORTH, builder.makeMazeWall())
        room1.setSide(RoomSide.EAST, door)
        room1.setSide(RoomSide.SOUTH, builder.makeMazeWall())
        room1.setSide(RoomSide.WEST, builder.makeMazeWall())
        room2.setSide(RoomSide.NORTH, builder.makeMazeWall())
        room2.setSide(RoomSide.EAST, builder.makeMazeWall())
        room2.setSide(RoomSide.SOUTH, builder.makeMazeWall())
        room2.setSide(RoomSide.WEST, door)
    }
}

class BuilderDoor(val outRoom: BuilderRoom, val inRoom: BuilderRoom)

open class BuilderRoom(val number: Int) {

    val sides: MutableMap<RoomSide, in Any> = mutableMapOf()

    fun setSide(roomSide: RoomSide, wall: BuilderWall) {
        sides.put(roomSide, wall)
    }

    fun setSide(roomSide: RoomSide, door: BuilderDoor) {
        sides.put(roomSide, door)
    }
}

open class BuilderWall {
    open fun print() {
        System.out.println("\t\t\tWall")
    }
}

open class BuilderMaze {
    val rooms: MutableList<BuilderRoom> = mutableListOf()

    fun addRoom(room: BuilderRoom) {
        rooms.add(room)
    }
}

fun BuilderMaze.print() {
    System.out.println("Maze with rooms:")
    this.rooms.forEach { it.print() }
}

fun BuilderRoom.print() {
    System.out.println("\tRoom with sides:")
    for ((side, value) in this.sides) {
        System.out.println("\t\tSide $side with:")
        when (value) {
            is BuilderDoor -> value.print()
            is BuilderWall -> value.print()
        }
    }
}

fun BuilderDoor.print() {
    System.out.println("\t\t\tDoor from ${this.inRoom.number} to ${this.outRoom.number}");
}

class FancyBuilderWall : BuilderWall() {
    override fun print() {
        System.out.println("\t\t\tA pretty fancy wall")
    }
}

open class DefaultMazeBuilder : MazeBuilder() {
    override fun makeMazeWall() = BuilderWall();
    override fun makeDoor(outRoom: BuilderRoom, inRoom: BuilderRoom) = BuilderDoor(outRoom, inRoom)
    override fun makeRoom(number: Int) = BuilderRoom(number)
}

class FancyMazeBuilder : DefaultMazeBuilder() {
    override fun makeMazeWall() = FancyBuilderWall();
}

fun main(args: Array<String>) {
    var fancyBuilder = FancyMazeBuilder();
    var director = MazeDirector(fancyBuilder);
    director.Construct();
    var maze = fancyBuilder.getResult();
    maze.print()
}
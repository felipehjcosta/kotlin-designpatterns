package com.felipecosta.designpatterns.creational

open class Maze {

    val rooms: MutableList<Room> = mutableListOf()

    fun addRoom(room: Room) {
        rooms.add(room)
    }

}

open class Wall {

}

open class Room(val number: Int) {

    val sides: MutableMap<RoomSide, in Any> = mutableMapOf()


    fun setSide(roomSide: RoomSide, wall: Wall) {
        sides.put(roomSide, wall)
    }

    fun setSide(roomSide: RoomSide, door: Door) {
        sides.put(roomSide, door)
    }

}

enum class RoomSide {
    NORTH, EAST, SOUTH, WEST
}

open class Door(val outRoom: Room, val inRoom: Room) {

}

open class MazeFactory {

    fun makeMaze() = Maze()

    fun makeMazeWall() = Wall()

    fun makeRoom(number: Int) = Room(number)

    fun makeDoor(outRoom: Room, inRoom: Room) = Door(outRoom, inRoom)

}

fun createMaze(mazeFactory: MazeFactory): Maze {

    val maze = mazeFactory.makeMaze()
    val room1 = mazeFactory.makeRoom(1)
    val room2 = mazeFactory.makeRoom(2)
    val door = mazeFactory.makeDoor(room1, room2)

    maze.addRoom(room1)
    maze.addRoom(room2)

    room1.setSide(RoomSide.NORTH, mazeFactory.makeMazeWall())
    room1.setSide(RoomSide.EAST, door)
    room1.setSide(RoomSide.SOUTH, mazeFactory.makeMazeWall())
    room1.setSide(RoomSide.WEST, mazeFactory.makeMazeWall())
    room2.setSide(RoomSide.NORTH, mazeFactory.makeMazeWall())
    room2.setSide(RoomSide.EAST, mazeFactory.makeMazeWall())
    room2.setSide(RoomSide.SOUTH, mazeFactory.makeMazeWall())
    room2.setSide(RoomSide.WEST, door)

    return maze
}

fun Maze.print() {
    System.out.println("Maze with rooms:")
    this.rooms.forEach { it.print() }
}

fun Room.print() {
    System.out.println("\tRoom with sides:")
    for ((side, value) in this.sides) {
        System.out.println("\t\tSide $side with:")
        when (value) {
            is Door -> value.print()
            is Wall -> value.print()
        }
    }
}

fun Wall.print() {
    System.out.println("\t\t\tWall")
}

fun Door.print() {
    System.out.println("\t\t\tDoor from ${this.inRoom.number} to ${this.outRoom.number}");
}

fun main(args: Array<String>) {
    var maze = createMaze(MazeFactory());

    maze.print()
}
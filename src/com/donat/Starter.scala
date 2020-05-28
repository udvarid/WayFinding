package com.donat

object Starter extends App {

  val myManager: Manager = new Manager

  myManager.createRoom(5, 5)

  for (i <- 0 until 25) {
    println(myManager.myRoom.cells(i), myManager.myRoom.cells(i) == myManager.myRoom.cells(i).root, myManager.myRoom.size(i))
  }

}

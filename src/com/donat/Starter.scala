package com.donat

object Starter extends App {

  val myManager: Manager = new Manager

  myManager.createRoom(40, 40)

  myManager.process()

  myManager.drawRoom()

}

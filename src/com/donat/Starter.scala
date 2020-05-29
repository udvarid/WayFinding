package com.donat

object Starter extends App {

  val myManager: Manager = new Manager(40, 40)

  myManager.multiProcess(50)

  //myManager.drawRoom()

}

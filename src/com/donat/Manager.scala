package com.donat

import com.donat.cell.Room

class Manager {

  var myRoom: Room = _

  def createRoom(row: Int, column: Int): Unit = {
    myRoom = Room(row, column)
  }
}

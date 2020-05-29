package com.donat

import com.donat.cell.{Cell, Room}

import scala.util.Random

class Manager {

  var myRoom: Room = _

  def createRoom(row: Int, column: Int): Unit = {
    myRoom = Room(row, column)
  }

  def drawRoom(): Unit = {
    if (myRoom != null) {
      for (r <- 0 until myRoom.row) {
        for (c <- 0 until myRoom.column) {
          val cell: Cell = myRoom.cells(c)(r)
          if (cell.closed) print("X  ")
          else if (cell.signed) print(".  ")
          else print("   ")
        }
        println
      }
    }
  }

  def process(): Unit = {
    if (myRoom != null) {

      var i = 0
      var stayInLoop: Boolean = true
      while (stayInLoop) {
        val openedCell = openAWall()
        val neighbours: List[Cell] = findNeighbours(openedCell)
        neighbours.foreach(c => myRoom.connect(openedCell, c))
        for {
          s <- myRoom.openStart
          e <- myRoom.openEnd
        } {
          if (s onSameTree e) {
            if (stayInLoop) {
              stayInLoop = false
              myRoom.signTheWinnerCells(s.rootOfMyTree)
            }
          }
        }
        i += 1
      }
      println(s"The room is free to access on ${i - 1}th step.")
    }
  }

  private def openAWall(): Cell = {
    val closedWalls: List[Cell] = myRoom.closedCells
    val cellToOpen: Cell = closedWalls(Random.nextInt(closedWalls.length))
    cellToOpen.open()
    cellToOpen
  }

  private def findNeighbours(c: Cell): List[Cell] = {
    def detect(x: Int, y: Int): Option[Cell] = {
      if (x >= 1 && x <= myRoom.column && y >= 1 && y <= myRoom.row && !myRoom.cells(x - 1)(y - 1).closed)
        Some(myRoom.cells(x - 1)(y - 1))
      else None
    }

    implicit class PimpMyList(list: List[Cell]) {
      def addTo(element: Option[Cell]): List[Cell] =
        if (element.isEmpty) list
        else list :+ element.get
    }

    List() addTo
      detect(c.x - 1, c.y) addTo
      detect(c.x + 1, c.y) addTo
      detect(c.x, c.y + 1) addTo
      detect(c.x, c.y - 1)
  }
}

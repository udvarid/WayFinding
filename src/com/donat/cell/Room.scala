package com.donat.cell

import scala.annotation.tailrec

case class Room(row: Int, column: Int) {

  val cellFunction: (Int, Int) => Cell = (x, y) => Cell(x, y)

  val cells: List[List[Cell]] = initCells(cellFunction)

  def initCells[B](funct: (Int, Int) => B): List[List[B]] = {
    @tailrec
    def createCells(list: List[List[B]], index: Int): List[List[B]] = {
      if (index > column.max(1)) list
      else {
        val rowOfCells: List[B] = (for (r <- 1 to row.max(1)) yield funct(index, r)).toList
        createCells(list :+ rowOfCells, index + 1)
      }
    }

    createCells(List(), 1)
  }

  def closedCells: List[Cell] = {
    for {
      r <- cells
      c <- r.filter(c => c.closed)
    } yield c
  }

  def openStart: List[Cell] = {
    for {
      r <- cells
      c <- r.filter(c => c.y == 1 && !c.closed)
    } yield c
  }

  def openEnd: List[Cell] = {
    for {
      r <- cells
      c <- r.filter(c => c.y == row && !c.closed)
    } yield c
  }

  def connect(cellOne: Cell, cellTwo: Cell): Unit = {
    if (cellOne == cellOne.root)
      cellOne.root = cellTwo.rootOfMyTree
    else {
      if (cellOne.lengthOfMyTree < cellTwo.lengthOfMyTree)
        cellOne.rootOfMyTree.root = cellTwo.rootOfMyTree
      else
        cellTwo.rootOfMyTree.root = cellOne.rootOfMyTree
    }
  }

  def signTheWinnerCells(ref: Cell): Unit = {
    for {
      r <- cells
      c <- r.filter(c => c.rootOfMyTree == ref)
    } c.sign()
  }


}

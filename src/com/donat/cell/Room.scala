package com.donat.cell

case class Room(row: Int, column: Int) {

  //TODO two dimensional lists should be used to ease the neighbouring finder (and the drawing as well)
  val cells: List[Cell] =
    (for (r <- 1 to row;
          c <- 1 to column)
      yield Cell(r, c)).toList


  val size: List[Int] =
    (for (n <- 1 to row * column)
      yield 1).toList


}

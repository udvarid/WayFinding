package com.donat.cell

case class Cell(x: Int, y: Int, var closed: Boolean = true) {
  var root: Cell = this
}

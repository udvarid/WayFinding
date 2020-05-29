package com.donat.cell

import scala.annotation.tailrec

case class Cell(x: Int, y: Int, var closed: Boolean = true, var signed: Boolean = false) {
  var root: Cell = this

  def open(): Unit = closed = false

  def sign(): Unit = signed = true

  def rootOfMyTree: Cell = {
    @tailrec
    def findRoot(c: Cell): Cell = {
      if (c == c.root) c
      else findRoot(c.root)
    }

    findRoot(this)
  }

  def lengthOfMyTree: Int = {
    @tailrec
    def findRoot(c: Cell, x: Int): Int = {
      if (c == c.root) x
      else findRoot(c.root, x + 1)
    }

    findRoot(this, 1)
  }

  def onSameTree(cellTwo: Cell): Boolean = {
    this.rootOfMyTree == cellTwo.rootOfMyTree
  }
}

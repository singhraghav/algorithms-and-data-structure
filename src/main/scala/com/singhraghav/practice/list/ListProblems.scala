package com.singhraghav.practice.list

import scala.annotation.tailrec

sealed abstract class RList[+T] {
  def head: T

  def tail: RList[T]

  def isEmpty: Boolean

  def headOption: Option[T]

  def ::[R >: T](element: R): RList[R] = new ::(element, this)

}

case object RNil extends RList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException()

  override def tail: RList[Nothing] = throw new NoSuchElementException()

  override def isEmpty: Boolean = true

  override def headOption: Option[Nothing] = None

  override def toString: String = "[]"

}

case class ::[+T](override val head: T, override val tail: RList[T]) extends RList[T] {
  override def isEmpty: Boolean = false

  override def headOption: Option[T] = Some(head)

  override def toString: String = {

    @tailrec
    def toStringTailRec(remaining: RList[T], result: String): String = {
      if (remaining.isEmpty) result
      else if (remaining.tail.isEmpty) s"$result${remaining.head}"
      else toStringTailRec(remaining.tail, s"$result${remaining.head}, ")
    }

    "[" + toStringTailRec(this, "") + "]"
  }

}

object ListProblems extends App {

  val list1 = ::(1, ::(2, ::(3, RNil)))

  println(list1)
}

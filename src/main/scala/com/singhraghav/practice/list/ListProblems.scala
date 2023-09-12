package com.singhraghav.practice.list

import scala.annotation.tailrec

sealed abstract class RList[+T] {
  def head: T

  def tail: RList[T]

  def isEmpty: Boolean

  def headOption: Option[T]

  def ::[R >: T](element: R): RList[R] = new ::(element, this)

  def apply(index: Int): T

  def length: Int

  def reverse: RList[T]

  def ++[S >: T](other: RList[S]): RList[S]
}

case object RNil extends RList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException()

  override def tail: RList[Nothing] = throw new NoSuchElementException()

  override def isEmpty: Boolean = true

  override def headOption: Option[Nothing] = None

  override def toString: String = "[]"

  override def apply(index: Int): Nothing = throw new NoSuchElementException()

  override def length: Int = 0

  override def reverse: RList[Nothing] = this

  override def ++[S >: Nothing](other: RList[S]): RList[S] = other
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

  override def apply(index: Int): T = {
    @tailrec
    def loop(currIndex: Int, remainingList: RList[T]): T =
      if(currIndex < index && remainingList.tail.isEmpty) throw new NoSuchElementException()
      else if(currIndex == index) remainingList.head
      else loop(currIndex + 1, remainingList.tail)

    if (index < 0) throw new IllegalArgumentException()
    else loop(0, this)
  }

  override def length: Int = {
    @tailrec
    def loop(remaining: RList[T], size: Int): Int =
      if(remaining.isEmpty) size
      else loop(remaining.tail, size + 1)

    loop(this, 0)
  }

  override def reverse: RList[T] = {

    @tailrec
    def loop(remaining: RList[T], result: RList[T]): RList[T] =
      if(remaining.isEmpty) result
      else loop(remaining.tail, remaining.head :: result)

    loop(this, RNil)
  }

  override def ++[S >: T](other: RList[S]): RList[S] = {

    @tailrec
    def loop(remaining: RList[S], result: RList[S]): RList[S] =
      if(remaining.isEmpty) result
      else loop(remaining.tail, remaining.head :: result)

    loop(this.reverse, other)
  }
}


object ListProblems extends App {

  val list1 = ::(1, ::(2, ::(3, RNil)))

  println(list1(0))
  println(list1(2))

//  println(list1(3))

  println(list1.reverse)

  val list2 = ::(4, ::(5, RNil))

  println(list2 ++ RNil)
}

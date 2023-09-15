package com.singhraghav.practice.ds.list

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

  def removeAt(index: Int): RList[T]

  def map[S](f: T => S): RList[S]

  def flatMap[S](f: T => RList[S]): RList[S]

  def filter(f: T => Boolean): RList[T]

  def rle: RList[(T, Int)]

  def duplicateEach(n: Int): RList[T]

  def rotateLeft(n: Int): RList[T]

  def sample(k: Int): RList[T]
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

  override def removeAt(index: Int): RList[Nothing] = this

  override def map[S](f: Nothing => S): RList[S] = this

  override def flatMap[S](f: Nothing => RList[S]): RList[S] = this

  override def filter(f: Nothing => Boolean): RList[Nothing] = this

  override def rle: RList[(Nothing, Int)] = this

  override def duplicateEach(n: Int): RList[Nothing] = this

  override def rotateLeft(n: Int): RList[Nothing] = this

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


  override def removeAt(index: Int): RList[T] = {

    @tailrec
    def loop(acc: RList[T], remaining: RList[T], currentIndex: Int): RList[T] =
      if (currentIndex < index && remaining.isEmpty) this
      else if (currentIndex == index && remaining.isEmpty) acc.reverse
      else if(currentIndex == index) acc.reverse ++ remaining.tail
      else loop(remaining.head :: acc, remaining.tail, currentIndex + 1)

    loop(RNil, this, 0)
  }

  override def map[S](f: T => S): RList[S] = {

    @tailrec
    def loop(accumulated: RList[S], remaining: RList[T]): RList[S] =
      if(remaining.isEmpty) accumulated.reverse
      else loop(f(remaining.head) :: accumulated, remaining.tail)

    loop(RNil, this)
  }

  override def flatMap[S](f: T => RList[S]): RList[S] = {

    @tailrec
    def loop(accumulated: RList[S], remaining: RList[T]): RList[S] =
      if (remaining.isEmpty) accumulated
      else loop(accumulated ++ f(remaining.head), remaining.tail)

    loop(RNil, this)
  }

  override def filter(f: T => Boolean): RList[T] = {
    @tailrec
    def loop(accumulated: RList[T], remaining: RList[T]): RList[T] =
      if (remaining.isEmpty) accumulated.reverse
      else loop( if(f(remaining.head)) remaining.head :: accumulated else accumulated, remaining.tail)

    loop(RNil, this)
  }

  override def rle: RList[(T, Int)] = {

    @tailrec
    def loop(accumulator: RList[(T, Int)], remaining: RList[T]): RList[(T, Int)] =
      if (remaining.isEmpty) accumulator.reverse
      else {
        accumulator match {
          case (element, count) :: t if element == remaining.head => loop((element, count + 1) :: t, remaining.tail)
          case _ => loop((remaining.head, 1) :: accumulator, remaining.tail)
        }
      }

    loop(RNil, this)
  }

  override def duplicateEach(n: Int): RList[T] = {

    @tailrec
    def loop(accumulator: RList[T], remaining: RList[T], times: Int): RList[T] =
      if (remaining.isEmpty) accumulator.reverse
      else {
        if (times == 0)
          loop(accumulator, remaining.tail, n)
        else
          loop(remaining.head :: accumulator, remaining, times - 1)
      }
    if(n <= 1) this else loop(RNil, this, n)
  }

  override def rotateLeft(n: Int): RList[T] = {

    val actualRotationNeeded = n % this.length

    @tailrec
    def loop(acc: RList[T], remaining: RList[T], rotationLeft: Int): RList[T] =
      if(rotationLeft == 0) remaining ++ acc.reverse
      else loop(remaining.head :: acc, remaining.tail, rotationLeft - 1)

    if (actualRotationNeeded == 0) this else loop(RNil, this, actualRotationNeeded)
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

  //[2, 3]
  println(list1.removeAt(0))

  //[1, 3]
  println(list1.removeAt(1))

  //[1, 2]
  println(list1.removeAt(2))

  //[1, 2, 3]
  println(list1.removeAt(3))

  println(list1.map(_ * 2))

  println(list1.flatMap(x => ::(x, ::(x+ 1, RNil))))

  println(list1.filter(_ % 2 != 0))

  //[1, 1, 2, 3, 3, 3, 3, 3, 4,4,4,5,6]
  val list3 = ::(1, ::(1, ::(2, ::(3, ::(3, ::(3, ::(3, ::(3, ::(4, ::(4, ::(4, ::(5, ::(6, RNil)))))))))))))
  //[(1, 2), (2, 1), (3, 5), (4, 3), (5, 1), (6,1)]
  println(list3.rle)

  println(list1.duplicateEach(1))

  println(::(1, RNil).rotateLeft(3))
}

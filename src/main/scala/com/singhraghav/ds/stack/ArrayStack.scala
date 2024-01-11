package com.singhraghav.ds.stack

import scala.reflect.ClassTag

class ArrayStack[T] (private val data: Array[T], private var capacity: Int, private var t: Int = -1) extends MyStack[T] {
  override def push(element: T): ArrayStack.this.type = {
    if (size() == data.length) throw new IllegalStateException("Stack full")
    t = t + 1
    data(t) = element
    this
  }

  override def pop(): T = {
    if (isEmpty()) throw new NoSuchElementException("Stack Empty")
    else {
      val elementToReturn = data(t)
      t -= 1
      elementToReturn
    }
  }

  override def isEmpty(): Boolean = t == -1

  override def size(): Int = t + 1

  override def top(): T =
    if (isEmpty()) throw new NoSuchElementException("Stack Empty") else data(t)
}

object ArrayStack {

  def apply[T: ClassTag](): MyStack[T] = apply(1000)

  def apply[T: ClassTag](capacity: Int)= new ArrayStack[T](data = Array.ofDim[T](capacity), capacity = capacity)
}
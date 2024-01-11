package com.singhraghav.ds.stack

trait MyStack[T] {

  def push(element: T): this.type

  def pop(): T

  def isEmpty(): Boolean

  def size(): Int

  def top(): T

}

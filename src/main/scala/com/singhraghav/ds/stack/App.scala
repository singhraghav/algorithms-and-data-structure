package com.singhraghav.ds.stack

import scala.collection.mutable.Stack

object App extends App {

  val stack = new Stack[Int](100)
//
//  stack.push(10)
//
//  stack.push(20)



//  println(stack)

  val array = Array.ofDim[Int](10)

  println(s"Array: ${array.toList}")

  array(0) = 1

  array(5) = 30

  println(s"Array: ${array.toList}")

  val customStack = ArrayStack[Int](10)

  customStack.push(100)

  customStack.push(200)
  customStack.push(300)
  customStack.push(400)

  println(s"Custom Stack size: ${customStack.size()}")
  println(s"Custom Stack Top: ${customStack.top()}")
  customStack.pop()
  println(s"Custom Stack Top: ${customStack.top()}")
  customStack.pop()
  println(s"Custom Stack Top: ${customStack.top()}")


}

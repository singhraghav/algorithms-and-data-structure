package com.singhraghav.scala3.language

object SyntaxChanges extends App {

  val ifExpression = if 2 > 3 then "bigger" else "smaller";

  val ifExpression_v2 =
    if 2 > 3 then
        val result = "bigger"
        result
    else
        val result = "smaller"
        result
  end ifExpression_v2



  println(ifExpression_v2)

  val forComprehension: List[String] =
    for
      num <- List(1, 2, 3)
      char <- List('a', 'b')
    yield s"$num-$char"

  println(forComprehension)

  class Animal:
    def eat(): Unit =
      println("I am eating")
    end eat

  end Animal

  new Animal().eat()
}

package com.singhraghav.scala3.language

object UnionIntersectionTypes extends App {

  // union type
  val truthOr42: Boolean | Int = 42

  def ambivalent(arg: String | Int): String = arg match {
    case _: String => s"$arg is a string"
    case _: Int => s"$arg is an int"
  }

  println(ambivalent(42))
  println(ambivalent("str"))

  // intersection type

  class Animal
  trait Carnivore
  class Crocodile extends Animal with Carnivore

  val carnivoreAnimal: Animal & Carnivore = new Crocodile

}

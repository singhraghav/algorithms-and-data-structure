package com.singhraghav.scala3.language

object ImplicitChanges extends App {

  case class Person(name: String, age: Int)

  val people = List(
    Person("Repel", 24),
    Person("Alice", 23),
    Person("YODA", 900)
  )

  //scala 2
//  implicit val personOrdering: Ordering[Person] = (x: Person, y: Person) => x.name.compareTo(y.name)
//
//  given personOrdering: Ordering[Person] with {
//    override def compare(x: Person, y: Person): Int = x.name.compareTo(y.name)
//  }

  given personOrdering: Ordering[Person] = (x: Person, y: Person) => x.name.compareTo(y.name)


  def methodWithOrdering(list: List[Person])(using ordering: Ordering[Person]): List[Person] =
    list.sorted

  given optionOrdering[T](using ordering: Ordering[T]): Ordering[Option[T]] with {
    override def compare(x: Option[T], y: Option[T]): Int = -1
  }


  val sortedList = people.sorted

  println(sortedList)

}

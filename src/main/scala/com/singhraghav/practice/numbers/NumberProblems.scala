package com.singhraghav.practice.numbers

import scala.annotation.tailrec

object NumberProblems extends App {

  def isTheNumberPrime(num: Int): Boolean = {

    @tailrec
    def loop(currentDivisor: Int): Boolean = {
      if (currentDivisor > Math.sqrt(num)) true
      else if (num % currentDivisor == 0) false
      else loop(currentDivisor + 1)
    }

    if (num <= 1) false else loop(2)
  }

  def decomposeIntoPrimeFactors(num: Int): List[Int] = {

    def loop(remaining: Int, currentDivisor: Int, result: List[Int]): List[Int] = {
      if (currentDivisor > Math.sqrt(remaining)) remaining :: result
      else if (remaining % currentDivisor == 0) loop(remaining / currentDivisor, currentDivisor, currentDivisor :: result)
      else loop(remaining, currentDivisor + 1, result)
    }

    if (num < 1) List() else loop(num, 2, Nil)
  }

  println(decomposeIntoPrimeFactors(96))

}

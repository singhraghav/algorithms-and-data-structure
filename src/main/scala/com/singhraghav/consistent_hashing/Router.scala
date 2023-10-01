package com.singhraghav.consistent_hashing

trait Router {

  def get(key: String): Option[Node]

  def add(node: Node): Unit

  def remove(node: Node): Unit
}


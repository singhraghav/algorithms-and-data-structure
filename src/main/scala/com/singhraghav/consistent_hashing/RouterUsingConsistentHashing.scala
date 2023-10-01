package com.singhraghav.consistent_hashing

import scala.collection.mutable

class RouterUsingConsistentHashing private (hashFunction: String => Int, virtualizationFactor: Int) extends Router {

  private val hostKey = (hostName: String, index: Int) => s"$hostName#$index"

  private val ring: mutable.TreeMap[Int, Node] = new mutable.TreeMap[Int, Node]()

  override def get(key: String): Option[Node] = synchronized {
    if (ring.isEmpty) None
    else {
      val hash = hashFunction(key)

      val nodesGreaterThanCurrentHash = ring.rangeFrom(hash)

      if (nodesGreaterThanCurrentHash.isEmpty) Some(ring.head._2)
      else Some(nodesGreaterThanCurrentHash.head._2)
    }
  }

  override def add(node: Node): Unit = synchronized {
    for (i <- 0 until virtualizationFactor) {
      ring.put(hashFunction(hostKey(node.host, i)), node)
    }
  }

  override def remove(node: Node): Unit = synchronized {
    for (i <- 0 until virtualizationFactor) {
      ring.remove(hashFunction(hostKey(node.host, i)))
    }
  }
}

object RouterUsingConsistentHashing {
  def apply(hashFunction: String => Int, virtualizationFactor: Int): Router =
    new RouterUsingConsistentHashing(hashFunction, virtualizationFactor)

}
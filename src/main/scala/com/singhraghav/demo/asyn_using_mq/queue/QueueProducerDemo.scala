package com.singhraghav.demo.asyn_using_mq.queue

import com.singhraghav.demo.asyn_using_mq.IbmMqConnection

import javax.jms.Session

object QueueProducerDemo extends App {

  val connection = IbmMqConnection.connectionToDockerInstance
  val session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)

  val queueProducer = IbmMqConnection.createQueueProducer(session, queueName = "DEV.QUEUE.1")

  for (i <- 1 to 10) {
    val message = session.createTextMessage(s"message $i")
    queueProducer.send(message)
    println(s"message $i sent")
  }

  queueProducer.close()
  session.close()
  connection.close()

}

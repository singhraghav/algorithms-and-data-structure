package com.singhraghav.demo.asyn_using_mq.queue

import com.singhraghav.demo.asyn_using_mq.IbmMqConnection

import javax.jms.{Message, MessageListener, Session}
import scala.util.{Failure, Success, Try}

/**
 * keep the session in a client Acknowledge mode or transacted mode
 *
 * The behaviour is like this, the app processes the message once the call to onMessage method is complete
 *
 * You won't see the message in the queue any longer
 *
 * because they are marked to be removed, if you start another instance it will not read already read message by this consumer, even though its not committed/acked yet
 *
 * But in case you kill the app without calling session.commit()/message.acknowledge(), the message processed by first consumer will reappear in the queue and will be next read by the new consumer
 *
 * */
object QueueConsumerUsingListenerDemo extends App {

  val transacted = false

  val connection = IbmMqConnection.connectionToDockerInstance
  val session = connection.createSession(transacted, Session.CLIENT_ACKNOWLEDGE)

  val consumer = IbmMqConnection.createConsumer(session, queueName = "DEV.QUEUE.1")

  Try {
    consumer.setMessageListener((message: Message) => {
      val messageBody = message.getBody(classOf[String])

      println(s"Received $message \n")

      println(s"completed processing $messageBody \n")

      message.acknowledge()
    })
  } match {
    case Success(_) => println("success")
    case Failure(exception) =>
      exception.printStackTrace()
      consumer.close()
      session.close()
      connection.close()
  }

  connection.start()

  while (true) {}
}

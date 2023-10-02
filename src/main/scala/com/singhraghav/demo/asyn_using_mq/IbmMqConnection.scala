package com.singhraghav.demo.asyn_using_mq

import com.ibm.mq.jms.MQQueueConnectionFactory
import com.ibm.msg.client.wmq.WMQConstants
import com.ibm.msg.client.wmq.common.CommonConstants

import javax.jms.{Connection, MessageConsumer, MessageProducer, Session}

object IbmMqConnection {

  def createConnection(
                        queueManager: String,
                        host: String,
                        port: Int,
                        channel: String,
                        userId: String,
                        password: String
                      ): Connection = {
    val connectionFactory = new MQQueueConnectionFactory()
    connectionFactory.setHostName(host)
    connectionFactory.setPort(port)
    connectionFactory.setQueueManager(queueManager)
    connectionFactory.setTransportType(CommonConstants.WMQ_CM_CLIENT)
    connectionFactory.setChannel(channel)
    connectionFactory.createConnection(userId, password)
  }

  val session: Connection => Session = (connection: Connection) => connection.createSession()

  def createQueueProducer(session: Session, queueName: String): MessageProducer = {
    val queue = session.createQueue("queue:///" + queueName)
    session.createProducer(queue)
  }

  def createConsumer(session: Session, queueName: String): MessageConsumer = {
    val queue = session.createQueue("queue:///" + queueName)
    session.createConsumer(queue)
  }

  def connectionToDockerInstance =
    createConnection(queueManager = "QM1", host = "localhost", port = 1414, channel = "DEV.ADMIN.SVRCONN", "admin", "passw0rd")
}

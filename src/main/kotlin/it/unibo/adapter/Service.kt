package it.unibo.adapter

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.Channel
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import java.nio.charset.StandardCharsets

class Service {

    private val consumerTag = "2pkt-service"

    fun start() {
        val factory = ConnectionFactory()
        val connection = factory.newConnection("amqp://guest:guest@localhost:5672/")
        val channel = connection.createChannel()
        initRequestsQueue(channel)
        initResponsesQueue(channel)
    }

    private fun initRequestsQueue(channel: Channel) {
        channel.queueDeclare("requests", false, false, false, null)
        println("[$consumerTag] Waiting for messages...")
        val deliverCallback = DeliverCallback { consumerTag: String?, delivery: Delivery ->
            val message = String(delivery.body, StandardCharsets.UTF_8)
            println("[$consumerTag] Received message: $message")
        }
        val cancelCallback = CancelCallback {
            println("[$it] was canceled")
        }
        channel.basicConsume("requests", true, consumerTag, deliverCallback, cancelCallback)
    }

    private fun initResponsesQueue(channel: Channel) {
        channel.queueDeclare("responses", false, false, false, null)
    }

}

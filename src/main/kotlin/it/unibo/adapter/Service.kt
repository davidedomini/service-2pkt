package it.unibo.adapter

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.Channel
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import it.unibo.request.RequestParser
import it.unibo.controller.Controller
import it.unibo.model.ComputationType
import java.nio.charset.StandardCharsets

class Service {

    private val consumerTag = "2pkt-service"

    private var channel: Channel? = null
    private val controller = Controller(this)

    fun start() {
        println("Starting service...")
        val factory = ConnectionFactory()
        val connection = factory.newConnection("amqp://guest:guest@localhost:5672/")
        channel = connection.createChannel()
        initRequestsQueue(channel)
        initResponsesQueue(channel)
    }

    private fun initRequestsQueue(channel: Channel?) {
        channel!!.queueDeclare("requests", false, false, false, null)
        println("[$consumerTag] Waiting for messages...")
        val deliverCallback = DeliverCallback { consumerTag: String?, delivery: Delivery ->
            val message = String(delivery.body, StandardCharsets.UTF_8)
            val request = RequestParser().parseRequest(message)

            when(request.type){
                ComputationType.ALL -> controller.solveAll(request)
                ComputationType.NEXT -> controller.solveNext(request)
                ComputationType.RESET -> controller.reset()
            }

            println("[$consumerTag] Received message: $message")
        }
        val cancelCallback = CancelCallback {
            println("[$it] was canceled")
        }
        channel.basicConsume("requests", true, consumerTag, deliverCallback, cancelCallback)
    }

    private fun initResponsesQueue(channel: Channel?) {
        channel!!.queueDeclare("responses", false, false, false, null)
    }

    fun sendResponse(response: String) {
        channel!!.basicPublish("", "responses", null, response.toByteArray(StandardCharsets.UTF_8))
    }

}

package com.intuisoft.core.data

import com.intuisoft.core.domain.Message


class MessageRepository(
    private val messageDataSource: MessageDataSource
) {

    fun getWelcomeMessage() = messageDataSource.getWelcomeMessage()

    fun buildSentMessage(msg : String) = messageDataSource.buildSentMessage(msg)

    fun buildRecievedMessage(msg : String) = messageDataSource.buildRecievedMessage(msg)

    fun getLiveData() = messageDataSource.getLiveData()

    suspend fun send(message : Message) = messageDataSource.send(message)
}
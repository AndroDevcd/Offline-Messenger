package com.intuisoft.offlinemessenger.framework

import android.content.Context
import androidx.paging.LivePagedListBuilder
import com.intuisoft.offlinemessenger.R
import com.intuisoft.core.data.MessageDataSource
import com.intuisoft.core.domain.Message
import com.intuisoft.core.domain.MessageType
import com.intuisoft.offlinemessenger.framework.db.OfflineMessengerDatabase

class RoomMessageDataSource(val context: Context) :
    MessageDataSource {

    private val messageDao = OfflineMessengerDatabase.getInstance(context).messageDao()

    override fun getWelcomeMessage(): Message =
        buildRecievedMessage(context.getString(R.string.welcome_message))

    override fun buildSentMessage(msg: String) : Message =
        Message(
            msg,
            MessageType.MESAGE_SENT
        )

    override fun buildRecievedMessage(msg: String) : Message =
        Message(
            msg,
            MessageType.MESSAGE_RECIEVED
        )

    override fun getLiveData()  =
        LivePagedListBuilder(messageDao.getDataSourceFactory().map { m ->
            Message(
                m.message,
                m.type
            )
        }, /* page size */ 20).build()

    override suspend fun send(message: Message) {
        messageDao.insert(OfflineMessengerDatabase.getInstance(context).toDb(message))
    }

}
package com.intuisoft.offlinemessenger.presentation

import android.app.Application
import android.content.Context
import com.intuisoft.core.data.MessageRepository
import com.intuisoft.core.usecases.*
import com.intuisoft.offlinemessenger.framework.MessengerViewModelFactory
import com.intuisoft.offlinemessenger.framework.RoomMessageDataSource
import com.intuisoft.offlinemessenger.framework.UseCases
import com.intuisoft.offlinemessenger.presentation.util.AppPreferences


class OfflineMessengerApplication : Application() {

    companion object {
        public lateinit var ctx: Context;
    }

    override fun onCreate() {
        super.onCreate()

        ctx = this
        val messageRepository = MessageRepository(
            RoomMessageDataSource(this)
        )

        MessengerViewModelFactory.inject(
            this,
            UseCases(
                BuildRecieveMessage(messageRepository),
                BuildSendMessage(messageRepository),
                GetLiveData(messageRepository),
                GetWelcomeMessage(messageRepository),
                SendMessage(messageRepository)
            ),
            AppPreferences(this)
        )
    }
}
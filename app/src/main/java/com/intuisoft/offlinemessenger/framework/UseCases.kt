package com.intuisoft.offlinemessenger.framework

import com.intuisoft.core.usecases.*

data class UseCases (
    val buildReceiveMessage: BuildRecieveMessage,
    val buildSendMessage: BuildSendMessage,
    val getLiveData: GetLiveData,
    val getWelcomeMessage: GetWelcomeMessage,
    val sendMessage: SendMessage
)

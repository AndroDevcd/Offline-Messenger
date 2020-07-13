package com.intuisoft.offlinemessenger.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(value = [MessageTypeConverter::class])
@Database(entities = [Message::class], version = 2)
abstract class OfflineMessengerDatabase : RoomDatabase() {
    companion object {

        private const val DATABASE_NAME = "message.db"

        private var instance: OfflineMessengerDatabase? = null

        private fun create(context: Context): OfflineMessengerDatabase =
            Room.databaseBuilder(context, OfflineMessengerDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()


        fun getInstance(context: Context): OfflineMessengerDatabase =
            (instance ?: create(context)).also { instance = it }
    }

    public fun toDb(message : com.intuisoft.core.domain.Message) =
        Message(message.message, message.type)

    abstract fun messageDao(): MessageDao
}
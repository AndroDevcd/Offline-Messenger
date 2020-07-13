package com.intuisoft.offlinemessenger.presentation.viewmodel

import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.content.IntentFilter
import android.provider.Settings
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.intuisoft.core.domain.Message
import com.intuisoft.core.domain.MessageType
import com.intuisoft.offlinemessenger.framework.BaseViewModel
import com.intuisoft.offlinemessenger.framework.UseCases
import com.intuisoft.offlinemessenger.presentation.util.AppPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


class MainActivityViewModel(
    application: Application,
    useCases: UseCases,
    private val preferences: AppPreferences
) : BaseViewModel(application, useCases, preferences) {

    private var data : LiveData<PagedList<Message>>? = null
    private val coroutineCtx = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val bluetoothAdapter : BluetoothAdapter? by lazy {
        BluetoothAdapter.getDefaultAdapter()
    }

    val bluetoothEnabled: LiveData<Boolean>
        get() = _bluetoothEnabled

    val bluetoothSearch: LiveData<Boolean>
        get() = _bluetoothSearch

    private val _bluetoothEnabled = MutableLiveData<Boolean>()
    private val _bluetoothSearch = MutableLiveData<Boolean>()

    fun getLiveData() : LiveData<PagedList<Message>>? {
        return data
    }

    fun getPrefs() = preferences

    fun isAppSupported() : Boolean {
        return bluetoothAdapter != null
    }

    fun pair() {
        bluetoothAdapter?.let {

            if(it.isEnabled) {
                val intentOpenBluetoothSettings = Intent()
                intentOpenBluetoothSettings.action = Settings.ACTION_BLUETOOTH_SETTINGS
                intentOpenBluetoothSettings.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                application.startActivity(intentOpenBluetoothSettings)
            } else {
                _bluetoothEnabled.postValue(false)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

    override fun onCreate() : MainActivityViewModel {
        data = useCases.getLiveData()
        return this
    }

    fun postMessage(msg : String, type : MessageType = MessageType.MESSAGE_RECIEVED) {
        coroutineCtx.launch {
            useCases.sendMessage(
                Message(
                    msg,
                    type
                )
            )
        }
    }
}
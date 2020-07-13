package com.intuisoft.offlinemessenger.presentation.util

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.intuisoft.offlinemessenger.framework.BaseViewModel
import com.intuisoft.offlinemessenger.framework.MessengerViewModelFactory
import java.util.*

fun <T> ArrayList<T>.customShuffle(): ArrayList<T> {
    val rng = Random()

    if(size > 0) {
        for (index in 1..this.size - 1) {
            val randomIndex = rng.nextInt(index)

            // Swap with the random position
            val temp = this[index]
            this[index] = this[randomIndex]
            this[randomIndex] = temp
        }
    }

    return this
}

fun <T : BaseViewModel> injectVM(fragmentActivity: FragmentActivity, clazz: Class<T>) : T {
    var vm : T =  ViewModelProviders.of(fragmentActivity, MessengerViewModelFactory)
        .get(clazz)

    vm.onCreate()
    return vm
}

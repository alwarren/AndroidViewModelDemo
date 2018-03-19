package demo.kotlin.vm.domain

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import timber.log.Timber

data class Hello(val text: String = "Hello from the Model!!!") {
    private val liveData = MutableLiveData<String>()

    fun fetchData(value: String): LiveData<String> {
        Timber.d("---> Setting data in the Hello model")
        liveData.value = value
        return liveData
    }
}
package demo.kotlin.vm.data

import android.arch.lifecycle.*
import demo.kotlin.vm.domain.Hello
import timber.log.Timber
import javax.inject.Inject

class HelloViewModel @Inject constructor(private val dataSource: Hello) : ViewModel() {

    // observed by ViewModel as a trigger to retrieve data from the source
    private var liveTrigger: MutableLiveData<String> = MutableLiveData()

    // observed by Activity/Fragment or some other class as a trigger to do something with the data
    private var liveData: LiveData<String>

    init {
        // default observable trigger
        liveTrigger.value = dataSource.text

        // observe liveTrigger and reload observable liveData when trigger changes
        liveData = Transformations.switchMap(liveTrigger) {
            dataSource.fetchData(liveTrigger.value!!)
        }
    }

    // changing liveTrigger's value causes Transformations.switchMap to send a value to the Hello
    // model where it's live value is updated. Hello model returns it's live data which replaces the
    // ViewModel's live data. MainActivity's observer sees the change and updates it's UI
    // with the new value.
    fun setData(value: String) {
        Timber.d("---> Updating the ViewModel with $value")
        liveTrigger.value = value
    }

    // retrieve the observable data
    fun getData(): LiveData<String> = liveData
}
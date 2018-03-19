package demo.kotlin.vm

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity
import demo.kotlin.vm.data.HelloViewModel
import demo.kotlin.vm.domain.Hello
import demo.kotlin.vm.ui.TextInputDialog

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), TextInputDialog.OnTextResult {
    @Inject lateinit var hello: Hello
    @Inject lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, factory).get(HelloViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("---> onCreate()")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        buttonHello.setOnClickListener {
            TextInputDialog(this, R.string.app_name).build().show()
        }

        // Observe the ViewModel's live data. WHen it changes, update the UI with the new value.
        // Watch the log to see when data is actually retrieved from the model. You'll notice that
        // on rotation, the UI is updated with the value from the view model but no call is made
        // to the Hello model.
        viewModel.getData().observe(this, Observer {
            it?.let { data -> updateUI(data) }
        })
    }

    override fun onResume() {
        super.onResume()
        Timber.d("---> onResume()")
    }

    override fun onTextDialogResult(input: String) {
        viewModel.setData(input)
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(data: String) {
        Timber.d("---> Updating UI from the ViewModel")
        textHello.text = data
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_reset -> {
                // reset the ViewModel's live data with default text from the Hello model
                onTextDialogResult(hello.text)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

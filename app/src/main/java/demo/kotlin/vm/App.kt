package demo.kotlin.vm

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import demo.kotlin.vm.BuildConfig
import demo.kotlin.vm.di.component.DaggerAppComponent
import timber.log.Timber

class App : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerAppComponent.builder().create(this)
}
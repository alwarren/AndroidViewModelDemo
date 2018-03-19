package demo.kotlin.vm.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import demo.kotlin.vm.MainActivity
import demo.kotlin.vm.di.module.MainActivityModule

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    internal abstract fun bindMainActivity(): MainActivity
}
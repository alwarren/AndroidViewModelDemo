package demo.kotlin.vm.di.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import demo.kotlin.vm.App
import demo.kotlin.vm.di.builder.ActivityBuilder
import demo.kotlin.vm.di.builder.ViewModelBuilders
import demo.kotlin.vm.di.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (AppModule::class),
                      (ViewModelBuilders::class), (ActivityBuilder::class)])
internal interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}
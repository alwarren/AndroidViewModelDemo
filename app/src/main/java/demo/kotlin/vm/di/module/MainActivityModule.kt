package demo.kotlin.vm.di.module

import android.arch.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import demo.kotlin.vm.data.HelloViewModel
import demo.kotlin.vm.domain.Hello

@Module
class MainActivityModule {
    @Provides
    fun provideHello() = Hello()

    @Provides
    fun provideDrinksViewModel(viewModel: HelloViewModel): ViewModel {
        return viewModel
    }
}
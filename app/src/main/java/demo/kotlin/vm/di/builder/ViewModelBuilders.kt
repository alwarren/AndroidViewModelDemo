package demo.kotlin.vm.di.builder

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import demo.kotlin.vm.data.CustomViewModelFactory
import demo.kotlin.vm.data.HelloViewModel
import demo.kotlin.vm.data.ViewModelKey

@Module
internal abstract class ViewModelBuilders {
    @Binds
    internal abstract fun bindFactory(factory: CustomViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HelloViewModel::class)
    internal abstract fun bindDrinksViewModel(viewModel: HelloViewModel): ViewModel
}
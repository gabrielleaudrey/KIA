package its.nugrohodimas.capstonebangkit.di

import its.nugrohodimas.capstonebangkit.ui.home.HomeViewModel
import its.nugrohodimas.capstonebangkit.ui.imunitation.ImunitationViewModel
import its.nugrohodimas.core.domain.usecase.KiaInteractor
import its.nugrohodimas.core.domain.usecase.KiaUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<KiaUseCase> { KiaInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { ImunitationViewModel(get()) }
}
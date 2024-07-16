package di

import org.koin.dsl.module
import viewmodel.DashboardViewModel

val provideViewModelModule = module {
    single {
        DashboardViewModel(get())
    }
}
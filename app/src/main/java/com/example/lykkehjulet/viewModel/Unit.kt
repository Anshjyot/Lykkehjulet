package com.example.lykkehjulet.viewModel

import com.example.lykkehjulet.gamecomponents.GameRepo
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel {
        MainScreenViewModel(get(), get())
    }
    viewModel {
        GameViewModel(get())
    }
}

val repository = module {
    single { GameRepo() }
}
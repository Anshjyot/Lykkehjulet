package com.example.lykkehjulet.navigate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lykkehjulet.view.gamescreen.GameScreen
import com.example.lykkehjulet.view.gamescreen.RuleScreen
import com.example.lykkehjulet.view.mainscreen.MainScreen
import com.example.lykkehjulet.viewModel.GameViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun NavControllers(
    firstScreen: String = ScreenNavigation.MAINSCREEN,
    navigation: ScreenNavigation = ScreenNavigation
) {
    val navController = rememberNavController()
    val actions = remember(navController) {
        Navigation(navController, navigation)
    }

    NavHost(
        navController = navController,
        startDestination = firstScreen
    ) {

        composable(
            ScreenNavigation.MAINSCREEN
        ) {
            MainScreen(
                GameScreenNavigation = actions.GameScreenNavigation,
                RulesScreenNavigation = actions.RuleScreenNavigation
            )
    }

        composable(
            ScreenNavigation.GAMESCREEN
        ) {
            val viewModel = getViewModel<GameViewModel>()
            GameScreen(
                navigateUp = actions.navigate,
                viewModel = viewModel,
                GameScreenNavigation = actions.GameScreenNavigation,

            )
        }

        composable(
            ScreenNavigation.RULESCREEN
        ) {
            val viewModel = getViewModel<GameViewModel>()
            RuleScreen(
                navigateUp = actions.navigate,
                viewModel = viewModel,
                )
        }



    }
}
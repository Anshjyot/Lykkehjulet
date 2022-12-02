package com.example.lykkehjulet.navigate

import androidx.navigation.NavHostController


class Navigation(
    private val navController: NavHostController,
    private val navigation: ScreenNavigation
) {
    val  GameScreenNavigation = {
        navController.navigate(navigation.GAMESCREEN)
    }

    val  RuleScreenNavigation = {
        navController.navigate(navigation.RULESCREEN)
    }

    val navigate: () -> Unit = {
        navController.navigateUp()
    }
}
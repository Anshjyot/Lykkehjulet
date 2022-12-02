package com.example.lykkehjulet.view.gamescreen
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*

@Composable
fun LivesLeft(attemptsLeft: Int): Float { var lives by remember { mutableStateOf(0f) }
    when (attemptsLeft) {
        5 -> lives = 0f
        4 -> lives = 0.20f
        3 -> lives = 0.40f
        2 -> lives = 0.60f
        1 -> lives = 0.80f
        0 -> lives = 1f
    }
    return progressAnimation(
        progress = lives
    )
}

@Composable
private fun progressAnimation(
    progress: Float,
    durationMillis: Int = 500,
    delayMillis: Int = 0,
    easing: Easing = LinearEasing
): Float {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = durationMillis,
            delayMillis = delayMillis,
            easing = easing
        )
    )
    return animatedProgress
}
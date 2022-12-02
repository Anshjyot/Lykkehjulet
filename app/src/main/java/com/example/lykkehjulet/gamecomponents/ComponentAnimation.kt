package com.example.lykkehjulet.gamecomponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.lykkehjulet.R

// Manages the state for animations state.
@Composable
private fun TransitionState() = remember {
    MutableTransitionState(false).apply {
        targetState = true
    }
}

@Composable
fun ApplyAnimatedVisibility(
    content: @Composable () -> Unit,
    densityValue: Dp
) {
    val density = LocalDensity.current

    AnimatedVisibility(
        visibleState = TransitionState(),
        enter = slideInVertically(
            animationSpec = tween(durationMillis = 500)
        ) {
            // Slide in from top/bottom the direction.
            with(density) { densityValue.roundToPx() }
        },
        content = { content() }
    )
}

/**
 * rotating controller
 * Used this as inspiration:
 * https://stackoverflow.com/questions/68239734/jetpack-compose-shortest-rotate-animation
 * https://stackoverflow.com/questions/68381193/infinite-rotation-of-an-image-in-jetpack-compose
 */
@Composable
fun Rotation(
    initialValue: Float = 0f,
    targetValue: Float = 360f,
    repeat: RepeatMode = RepeatMode.Restart,
    durationMillis: Int = 1000,
    delayMillis: Int = 0,
    easing: Easing = LinearEasing
): Float {
    val rotateAnimation = rememberInfiniteTransition()

    val rotating by rotateAnimation.animateFloat(
        initialValue = initialValue,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            repeatMode = repeat,
            animation = tween(
                durationMillis = durationMillis,
                delayMillis = delayMillis,
                easing = easing
            )
        )
    )

    return rotating
}



/**
 * Code inspiration from https://www.jetpackcompose.net/jetpack-compose-animations
 */

@Composable
fun SpinRotation(onClick: () -> Unit, buttonName: String) {
    var isRotated by rememberSaveable { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isRotated) 360F else 0f,
        animationSpec = tween(durationMillis = 2000)
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(R.drawable.wheel),
            contentDescription = "wheel",
            modifier = Modifier
                .rotate(rotationAngle)
                .size(130.dp)
                .clickable {
                    onClick.invoke()
                    isRotated = !isRotated
                }

        )

    }

        Text(
            text = buttonName,
            style = MaterialTheme.typography.button,
            color = Color.White,
            modifier = Modifier.padding(vertical = 25.dp)
        )
    }






@Composable
fun SparkAnimateGuessedLetter(
    sparkColor: Color = MaterialTheme.colors.primary.copy(0.50f)
) {
    val infiniteTransition = rememberInfiniteTransition()
    val scale: Float by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 12f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawCircle(
            color = sparkColor,
            center = Offset(
                x = canvasWidth / 2,
                y = canvasHeight / 2
            ),
            radius = size.minDimension / 4,
        )
    }
}


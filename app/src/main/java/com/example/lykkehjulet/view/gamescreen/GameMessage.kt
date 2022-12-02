package com.example.lykkehjulet.view.gamescreen


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.lykkehjulet.R
import com.example.lykkehjulet.gamecomponents.ApplyAnimatedVisibility
import androidx.compose.ui.graphics.ColorFilter
import com.example.lykkehjulet.gamecomponents.Rotation
import com.example.lykkehjulet.viewModel.GameViewModel

@Composable
fun GameWon(
    viewModel: GameViewModel,
    navigateUp: () -> Unit,
    GameScreenNavigation: () -> Unit,
) {
    Dialog(
        onDismissRequest = { navigateUp() }
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colors.surface,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {

            ApplyAnimatedVisibility(
                densityValue = (-400).dp,
                content = {
                    Image(
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primary),
                        painter = painterResource(id = R.drawable.controller),
                        contentDescription = stringResource(R.string.game_won_title),
                        alpha = 0.75f,
                        alignment = Alignment.TopCenter,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(alignment = Alignment.TopCenter),
                    )
                }
            )
            Text(
                text = buildAnnotatedString {

                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.secondary,
                            fontSize = 24.sp,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append(stringResource(id = R.string.game_won_title))
                    }

                    append(stringResource(id = R.string.game_won_points))

                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.secondary,
                            fontSize = 28.sp
                        )
                    ) {
                        //append(viewModel.pointsScoredOverall.toString())
                        append(viewModel.pointsSummary.toString())
                    }
                },
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.secondary.copy(0.75f),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 40.dp, vertical = 60.dp)
                    .fillMaxWidth()
                    .align(alignment = Alignment.Center),
            )

            Button (
                modifier = Modifier.width(120.dp)
                    .padding(top = 200.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
                border = BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colors.secondary.copy(0.5f)
                ),
                onClick = {
                    GameScreenNavigation()
                },
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.controller),
                    contentDescription = stringResource(R.string.cd_play_game_button),
                    tint = MaterialTheme.colors.secondary,
                    modifier = Modifier
                        .size(20.dp)
                        .rotate(Rotation())
                )

                Spacer(modifier = Modifier.width(16.dp) )
                GameMessageText(buttonName = stringResource(R.string.button_title_playagain))
            }


        }

    }
}


@Composable
fun GameLost(
    viewModel: GameViewModel,
    navigateUp: () -> Unit,
    GameScreenNavigation: () -> Unit
) {
    Dialog(
        onDismissRequest = { navigateUp() }
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colors.surface,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {

            ApplyAnimatedVisibility(
                densityValue = (-400).dp,
                content = {
                    Image(
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primary),
                        painter = painterResource(id = R.drawable.controller),
                        contentDescription = stringResource(R.string.game_lost),
                        alpha = 0.5f,
                        alignment = Alignment.TopCenter,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(alignment = Alignment.TopCenter),
                    )
                }
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.secondary,
                            fontSize = 24.sp,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append(stringResource(id = R.string.game_lost_title))
                    }

                    append(stringResource(id = R.string.game_lost_word))

                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.secondary,
                            fontSize = 28.sp,
                        )
                    ) {
                        append(viewModel.guessTheWord)
                    }
                },
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.secondary.copy(0.75f),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 40.dp, vertical = 60.dp)
                    .fillMaxWidth()
                    .align(alignment = Alignment.Center),
            )

            Button (
                modifier = Modifier.width(120.dp)
                    .padding(top = 270.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
                border = BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colors.secondary.copy(0.5f)
                ),
                onClick = {
                    GameScreenNavigation()
                },
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.controller),
                    contentDescription = stringResource(R.string.cd_play_game_button),
                    tint = MaterialTheme.colors.secondary,
                    modifier = Modifier
                        .size(20.dp)
                        .rotate(Rotation())
                )

                Spacer(modifier = Modifier.width(16.dp) )
                GameMessageText(buttonName = stringResource(R.string.button_title_playagain))
            }
        }
    }
}

@Composable
private fun GameMessageText(
    buttonName: String
) {
    Text(
        text = buttonName,
        style = MaterialTheme.typography.button,
        color = MaterialTheme.colors.secondaryVariant.copy(0.75f),
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

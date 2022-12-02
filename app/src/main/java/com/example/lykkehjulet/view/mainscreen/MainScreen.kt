package com.example.lykkehjulet.view.mainscreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.lykkehjulet.R
import com.example.lykkehjulet.gamecomponents.Rotation
import com.example.lykkehjulet.ui.theme.darkpurple


@Composable
fun MainScreen(
    GameScreenNavigation: () -> Unit,
    RulesScreenNavigation: () -> Unit
) {
    Surface(
        color = darkpurple
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Image(
                painter = painterResource(id = R.drawable.wheeloffortune),
                contentDescription = stringResource(R.string.bg),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.1f
            )

            // Main screen content
            MainScreenDisplay(
                GameScreenNavigation = GameScreenNavigation,
                RulesScreenNavigation = RulesScreenNavigation
            )
        }
    }
}


@Composable
private fun MainScreenDisplay(
    GameScreenNavigation: () -> Unit,
    RulesScreenNavigation: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(150.dp))
        Tagline()
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                PlayGame(GameScreenNavigation)
                Spacer(modifier = Modifier.height(8.dp))
                Rules(RulesScreenNavigation)

            }
        }
    }
}

@Composable
private fun PlayGame(
    navigateToGameScreen: () -> Unit
) {
    Button (
        modifier = Modifier.width(160.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colors.secondaryVariant.copy(0.5f)
        ),
        onClick = {
            navigateToGameScreen()
        },
    ) {
        Icon(
            painter = painterResource(id = R.drawable.controller),
            contentDescription = stringResource(R.string.cd_play_game_button),
            tint = MaterialTheme.colors.secondaryVariant,
            modifier = Modifier
                .size(20.dp)
                .rotate(Rotation())
        )
        Spacer(modifier = Modifier.width(16.dp))
        MainScreenText(buttonName = stringResource(R.string.button_title_play))
    }
}

@Composable
private fun Rules(
    navigateToRulesScreen: () -> Unit
) {
    Button (
        modifier = Modifier.width(160.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colors.secondaryVariant.copy(0.5f)
        ),
        onClick = {
            navigateToRulesScreen()
        },
    ) {
        Icon(
            painter = painterResource(id = R.drawable.wheeloffortune),
            contentDescription = stringResource(R.string.rules),
            tint = MaterialTheme.colors.secondaryVariant,
            modifier = Modifier
                .size(20.dp)
                .rotate(Rotation())
        )
        Spacer(modifier = Modifier.width(16.dp))
        MainScreenText(buttonName = stringResource(R.string.rules))
    }
}



@Composable
private fun Tagline() {
    Text(
        text = stringResource(R.string.game_tagline),
        style = MaterialTheme.typography.h4,
        color = MaterialTheme.colors.secondaryVariant.copy(1f),
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 28.dp, vertical = 20.dp)
    )
}


@Composable
private fun MainScreenText(
    buttonName: String
) {
    Text(
        text = buttonName,
        style = MaterialTheme.typography.button,
        color = MaterialTheme.colors.secondaryVariant.copy(0.8f),
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

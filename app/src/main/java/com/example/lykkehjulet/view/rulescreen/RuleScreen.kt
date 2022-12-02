package com.example.lykkehjulet.view.gamescreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.lykkehjulet.R
import com.example.lykkehjulet.gamecomponents.*
import com.example.lykkehjulet.model.Word
import com.example.lykkehjulet.ui.theme.*
import com.example.lykkehjulet.viewModel.GameViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RuleScreen(
    navigateUp: () -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    viewModel: GameViewModel
) {

    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
    )

    BackHandler(enabled = true) {
        coroutineScope.launch {
            modalSheetState.show()
        }
    }

    Surface(
        color = BottomGradient

    ) {
        ModalBottomSheetLayout(
            sheetState = modalSheetState,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetBackgroundColor = Teal200,
            scrimColor = Teal200.copy(0f),
            sheetContent = {
                ExitGame(
                    navigateUp = navigateUp,
                    modalSheetState = modalSheetState
                )
            },
        ) {

            RuleScreenContent(
                navigateUp = navigateUp
            )


        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun RuleScreenContent(
    navigateUp: () -> Unit,
) {

        Box {
            Text(
                modifier = Modifier
                    .padding(20.dp),
                text = "RULES: For the Android application, the modified rules are:\n" +
                        "1. The game is for one player.\n" +
                        "2. When the game starts, a word is randomly chosen from predefined categories and\n" +
                        "displayed along with the category.\n" +
                        "3. The word is displayed with the letters hidden.\n" +
                        "4. The player “spins the wheel”. (A graphically spinning wheel is not required to be\n" +
                        "implemented this could be done simply by tapping a button and showing the result.)\n" +
                        "5. The possible results of the “spinning the wheel” are: a number of points e.g 1000 or\n" +
                        "“bankrupt”.\n" +
                        "6. In the event of a value being shown, a letter (consonant or vowel) is chosen by the user\n" +
                        "(from a keyboard or otherwise). If the letter is present, the user’s points total is\n" +
                        "incremented by the value shown times the number of occurrences of the letter. The\n" +
                        "occurrences of the letter are revealed in the word. If the letter is not present the user loses\n" +
                        "a “life”.\n" +
                        "7. In the event of “bankrupt” being shown, the user loses all their points.\n" +
                        "8. The “wheel is spun” until the game is won or lost.\n" +
                        "9. The game is won when all the letters have been found and the user still has a life.\n" +
                        "10. The game is lost when the user has no lives left and the word has not been found.\n" +
                        "11. A user starts with 5 “lives”.",
                color = secondaryvariant
            )

            Button(
                modifier = Modifier.width(150.dp)
                    .padding(start = 20.dp, top = 700.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
                border = BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colors.secondary.copy(0.5f)
                ),
                onClick = {
                    navigateUp()
                },
            ) {
                RuleScreenText(buttonName = stringResource(R.string.go_back))
            }
        } }



@Composable
private fun RuleScreenText(
    buttonName: String
) {
    Text(
        text = buttonName,
        style = MaterialTheme.typography.button,
        color = MaterialTheme.colors.secondaryVariant.copy(0.8f),
        modifier = Modifier.padding(vertical = 4.dp)
    )
}





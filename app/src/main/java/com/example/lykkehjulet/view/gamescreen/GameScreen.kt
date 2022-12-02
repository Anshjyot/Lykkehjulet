package com.example.lykkehjulet.view.gamescreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
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
fun GameScreen(
    navigateUp: () -> Unit,
    GameScreenNavigation: () -> Unit,
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

            GameScreenContent(
                viewModel = viewModel,
                modalSheetState = modalSheetState,
            )

            val shouldRevealWord by viewModel.wordToGuess.observeAsState(false)
            if (shouldRevealWord) {
                GameLost(
                    viewModel = viewModel,
                    navigateUp = navigateUp,
                    GameScreenNavigation = GameScreenNavigation
                )
            }
            if (viewModel.gameOverWon) {
                GameWon(
                    viewModel = viewModel,
                    navigateUp = navigateUp,
                    GameScreenNavigation = GameScreenNavigation,
                    //MainScreenNavigation = MainScreenNavigation,
                )
            }

        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun GameScreenContent(
    viewModel: GameViewModel,
    modalSheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (
            navigateBackIconButton, gameProgressInfo, randomWordText,
            alphabetsGridItems
        ) = createRefs()

        IconButton(
            onClick = {
                coroutineScope.launch {
                    modalSheetState.show()
                }
            },
            modifier = Modifier
                .background(
                    color = MaterialTheme.colors.primary.copy(0.06f),
                    shape = CircleShape
                )
                .constrainAs(navigateBackIconButton) {
                    start.linkTo(parent.start, 20.dp)
                    top.linkTo(parent.top, 20.dp)
                }
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = stringResource(R.string.exit),
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.alpha(0.75f)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.constrainAs(gameProgressInfo) {
                centerHorizontallyTo(parent)
                top.linkTo(parent.top, 40.dp)
            }
        ) {
            AttemptsLeftProgressBars(viewModel)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CategoryandAttemptsLeft(viewModel)
            }
        }

        val guess = viewModel.PlayerGuess.value

        LazyVerticalGrid(
            columns = GridCells.Adaptive(40.dp),
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            //verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.constrainAs(randomWordText) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top, 320.dp)
               // top.linkTo(gameProgressInfo.bottom, 5.dp)
                //bottom.linkTo(alphabetsGridItems.top, 20.dp)
            }
        ) {
            if (guess!= null) {
                items(
                    items = guess
                ) { validGuess ->
                    GuessingLetter(validGuess)
                }
            }
        }

        Row(
            modifier = Modifier.padding(
                top = 400.dp, start = 135.dp
            )
        ) {


            val spin = remember { mutableStateOf("") }
            SpinRotation(onClick =
            {spin.value = viewModel.spinValue()
                Log.d("navn", spin.value.toString())}, buttonName = spin.value)

        }

        val alphabetsList by viewModel.wordList.observeAsState(listOf())

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(alphabetsGridItems) {
                    centerHorizontallyTo(parent)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            ApplyAnimatedVisibility(
                densityValue = 350.dp,
                content = {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(30.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(
                            items = alphabetsList,
                            key = { it.id }
                        ) { alphabet ->
                            LetterText(alphabet, viewModel)
                        }
                    }
                }
            )
        }
    }
}


@Composable
private fun LetterText(
    alphabet: Word,
    viewModel: GameViewModel
) {
    ConstraintLayout(
        modifier = Modifier
            .alpha(if (!alphabet.hasLetterBeenGuessed) 1f else 0.25f)
            .clip(CircleShape)
            .size(40.dp)
            .background(color = MaterialTheme.colors.primary.copy(0.12f))
            .clickable(
                enabled = !alphabet.hasLetterBeenGuessed,
                onClick = {
                    if (!viewModel.NoAttemptsLeft) {
                        // For each guess check if match is correct from ViewModel.
                        viewModel.letterMatch(alphabet)
                        alphabet.hasLetterBeenGuessed = true
                    }
                }
            )
    ) {
        val (letterText, clickEffectAnim) = createRefs()

        if (alphabet.hasLetterBeenGuessed) {
            Box(
                modifier = Modifier.constrainAs(clickEffectAnim) {
                    centerTo(parent)
                }
            ) {
                GuessedLetter()
            }
        }

        Text(
            text = alphabet.letter,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.constrainAs(letterText) {
                centerTo(parent)
            }
        )
    }
}

@Composable
private fun CategoryandAttemptsLeft(
    viewModel: GameViewModel
) {

    Text(
        text = buildAnnotatedString {
            append(stringResource(id = R.string.category))
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colors.primary,
                    fontSize = 28.sp
                )
            ) {
                append(viewModel.category.value.toString())
            }
        },
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.primary.copy(0.50f),
        textAlign = TextAlign.Center,
    )

    Divider(
        modifier = Modifier
            .width(width = 100.dp)
            .padding(vertical = 8.dp)
            .clip(MaterialTheme.shapes.small),
        color = MaterialTheme.colors.primary.copy(0.25f),
        thickness = 2.dp
    )

    Text(
        text = buildAnnotatedString {
            append(stringResource(id = R.string.points))
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colors.primary,
                    fontSize = 28.sp
                )
            ) {
                //append(viewModel.pointsScoredOverall.toString())
                append(viewModel.points.toString())
                //append(viewModel.spinValue())
            }
        },
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.primary.copy(0.50f),
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun AttemptsLeftProgressBars(
    viewModel: GameViewModel
) {

    LivesLeftDisplay(
        currentProgress = LivesLeft(viewModel.attemptsLeft),
        strokeWidth = 10.dp,
        indicatorSize = 240.dp,
        progressColor = Color.Red.copy(0.95f)
    )

    LivesLeftDisplay(
        currentProgress = 1f,
        strokeWidth = 10.dp,
        progressColor = Color.Green.copy(0.25f),
        indicatorSize = 240.dp
    )
}




@Composable
private fun GuessingLetter(
    validGuess: String,
) {
    ConstraintLayout {
        val (alphabet, box) = createRefs()

        Text(
            text = validGuess,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.constrainAs(alphabet) {
                centerTo(parent)
            }
        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colors.primary.copy(0.10f))
                .padding(20.dp)
                .constrainAs(box) {
                    centerTo(parent)
                }
        )
    }
}

@Composable
fun LivesLeftDisplay(
    currentProgress: Float,
    strokeWidth: Dp = 8.dp,
    progressColor: Color = MaterialTheme.colors.primary,
    indicatorSize: Dp
) {
    CircularProgressIndicator(
        progress = currentProgress,
        strokeWidth = strokeWidth,
        color = progressColor,
        modifier = Modifier.size(size = indicatorSize)
    )
}


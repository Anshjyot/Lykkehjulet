package com.example.lykkehjulet.view.gamescreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lykkehjulet.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExitGame(
    navigateUp: () -> Unit,
    modalSheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    Column(
        modifier = Modifier.padding(horizontal = 40.dp, vertical = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.exit_question),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.primary.copy(0.5f),
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    modalSheetState.hide()
                }
                navigateUp()
            },
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
            border = BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colors.primary.copy(0.5f)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = stringResource(R.string.exit),
                //letterSpacing = 6.sp,
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.primary.copy(0.5f),
                modifier = Modifier.padding(vertical = 6.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            onClick = {
                coroutineScope.launch {
                    modalSheetState.hide()
                }
            },
        ) {

            Text(
                text = stringResource(id = R.string.continue_game),
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.primary.copy(0.75f),
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}
package com.startup.ecoapp.signin.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.startup.ecoapp.signin.presentation.SignInIntent
import com.startup.ecoapp.signin.presentation.SignInState
import com.startup.ecoapp.signin.presentation.SignInViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun SignInScreen(navController: NavController, signInViewModel: SignInViewModel = koinViewModel()) {

    val state by signInViewModel.uiState.collectAsState(SignInState())
    val focusManager = LocalFocusManager.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 20.dp,
            alignment = Alignment.CenterVertically,
        ),
        modifier =
        Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(start = 40.dp, end = 40.dp, top = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            "Sign In",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary
        )
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                OutlinedTextField(
                    value = state.email,
                    onValueChange = { signInViewModel.handle(SignInIntent.ChangeUserEmail(it)) },
                    singleLine = true,
                    label = {
                        Text(
                            "Email",
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(
                            FocusDirection.Down
                        )
                    })
                )
                OutlinedTextField(
                    value = state.password,
                    onValueChange = { signInViewModel.handle(SignInIntent.ChangeUserPassword(it)) },
                    singleLine = true,
                    label = {
                        Text(
                            "Password",
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.clearFocus()
                    })
                )
            }
        }
        Button(
            onClick = {
                signInViewModel.handle(SignInIntent.ConfirmSignIn)
            },
            Modifier.width(150.dp)
        )
        {
            Text("Confirm", style = MaterialTheme.typography.bodyLarge)
        }
        Text(
            "Sign Up", modifier = Modifier
                .clickable {
                    navController.navigate("signUp_screen")
                },
            style = MaterialTheme.typography.titleSmall,
            color = Color.Gray
        )
        Spacer(Modifier.height(20.dp))
    }
    if (state.error != null)
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Gray.copy(alpha = 0.5f))
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    state.error.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Button(onClick = { signInViewModel.handle(SignInIntent.CloseErrorDialog) }) {
                    Text("OK")
                }
            }
        }

}

@Preview
@Composable
fun Preview2() {
    SignInScreen(navController = rememberNavController())
}

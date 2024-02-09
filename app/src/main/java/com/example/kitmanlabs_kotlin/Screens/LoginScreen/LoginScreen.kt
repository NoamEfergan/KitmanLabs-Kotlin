package com.example.kitmanlabs_kotlin.Screens.LoginScreen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kitmanlabs_kotlin.Models.Login.LoginResponse
import com.example.kitmanlabs_kotlin.R
import com.example.kitmanlabs_kotlin.ui.theme.KitmanLabsKotlinTheme

@Composable
fun LoginScreen() {
    val viewModel by remember { mutableStateOf(LoginScreenViewModel()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        when (viewModel.loadingUiState) {
            LoginState.Idle -> LoginState(viewModel)
            LoginState.Loading -> LoadingState()
            LoginState.Error -> ErrorState(viewModel)
            is LoginState.Success -> SuccessState(
                (viewModel.loadingUiState as LoginState.Success).name,
                viewModel
            )
        }
    }
}

@Composable
fun ErrorState(viewModel: LoginScreenViewModel) {
    Text(text = "Whoops! something went wrong. Please try again!")
    Button(onClick = { viewModel.retry() }) {
        Text(text = "Retry")
    }
    
}

@Composable
fun SuccessState(response: LoginResponse?, viewModel: LoginScreenViewModel) {
    Text(text = response?.username ?: "User name")
    Button(onClick = { viewModel.retry() }) {
        Text(text = "Retry")
    }
}

@Composable
fun LoadingState() {
    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}

@Composable
fun LoginState(viewModel: LoginScreenViewModel) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var hasErrorUsername by remember {
        mutableStateOf(false)
    }
    var hasErrorPassword by remember {
        mutableStateOf(false)
    }
    TextField(value = userName,
        onValueChange = { userName = it },
        label = { Text("Username") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        isError = hasErrorUsername,
        modifier = Modifier.animateContentSize(),
        supportingText = { if (hasErrorUsername) TextError(field = "Username") },
        trailingIcon = {
            if (hasErrorUsername) Icon(
                Icons.Filled.Info, "error", tint = MaterialTheme.colorScheme.error
            )
        })
    TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Password") },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier.animateContentSize(),
        isError = hasErrorPassword,
        supportingText = { if (hasErrorPassword) TextError(field = "Password") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            PasswordSupportingIcon(passwordVisible) {
                passwordVisible = !passwordVisible
            }
        },

        )
    Button(onClick = {
        hasErrorUsername = viewModel.setUsername(userName)
        hasErrorPassword = viewModel.setUserPassword(password)
        if (!hasErrorPassword && !hasErrorUsername) {
            viewModel.performLogin()
        }
    }) {
        Text(text = "Login")
    }
}

@Composable
private fun PasswordSupportingIcon(passwordVisible: Boolean, onClick: () -> Unit ) {
    val image =
        if (passwordVisible) painterResource(R.drawable.baseline_visibility_24) else painterResource(
            R.drawable.baseline_visibility_off_24
        )
    val description = if (passwordVisible) "Hide password" else "Show password"

    IconButton(onClick) {
        Icon(painter = image, description)
    }
}

@Composable
private fun TextError(field: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Please enter a valid $field!",
        color = MaterialTheme.colorScheme.error
    )

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    KitmanLabsKotlinTheme {
        LoginScreen()
    }
}
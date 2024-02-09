package com.example.kitmanlabs_kotlin.Screens.LoginScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kitmanlabs_kotlin.Models.Login.LoginRequest
import com.example.kitmanlabs_kotlin.Models.Login.LoginResponse
import com.example.kitmanlabs_kotlin.Networking.KitmanService
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface LoginState {
    data class Success(val name: LoginResponse?) : LoginState
    data object Error : LoginState
    data object Loading : LoginState
    data object Idle : LoginState
}

class LoginScreenViewModel : ViewModel() {
    private var userName = ""
    private var password = ""
    private val networkService = KitmanService
    var loadingUiState: LoginState by mutableStateOf(LoginState.Idle)
        private set

    fun retry() {
        loadingUiState = LoginState.Idle
    }

    fun performLogin() {
        viewModelScope.launch {
        loadingUiState = LoginState.Loading
            val request = LoginRequest(userName, password)
            loadingUiState = try {
                LoginState.Success(networkService.instance.login(request).body())
            } catch (e: IOException) {
                LoginState.Error
            } catch (e: HttpException) {
                LoginState.Error
            }
        }

    }

    fun setUsername(value: String): Boolean {
        return if (value.isNotBlank()) {
            userName = value
            false
        } else {
            true
        }
    }

    fun setUserPassword(value: String): Boolean {
        return if (value.isNotBlank()) {
            password = value
            false
        } else {
            true
        }
    }
}
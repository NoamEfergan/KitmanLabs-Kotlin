package com.example.kitmanlabs_kotlin.Screens.MainScreen

import SquadItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kitmanlabs_kotlin.Models.MainView.Athlete.AthleteItem
import com.example.kitmanlabs_kotlin.Networking.KitmanService
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MainScreenLoadingState {
    data object Error : MainScreenLoadingState // TODO: Hold the different errors
    data object Loading : MainScreenLoadingState
    data object Idle : MainScreenLoadingState
}

class MainScreenViewModel : ViewModel() {
    class PresentableData : LinkedHashMap<SquadItem, List<AthleteItem>>()

    var presentableData: PresentableData = PresentableData()
        private set
    private val networkService = KitmanService
    var loadingState: MainScreenLoadingState by mutableStateOf(MainScreenLoadingState.Idle)
        private set

    fun fetchData() {
        viewModelScope.launch {
            loadingState = MainScreenLoadingState.Loading
            loadingState = try {
                val squadResponse = networkService.instance.getSquads().body()
                val athleteResponse = networkService.instance.getAthletes().body()
                if (squadResponse != null && athleteResponse != null) {
                    presentableData =
                        matchAthletesWithSquads(athleteResponse.athletes, squadResponse.squads)
                    MainScreenLoadingState.Idle
                } else {
                    MainScreenLoadingState.Error
                }


            } catch (e: IOException) {
                MainScreenLoadingState.Error
            } catch (e: HttpException) {
                MainScreenLoadingState.Error
            }
        }
    }

    private fun matchAthletesWithSquads(
        athletes: List<AthleteItem>,
        squads: List<SquadItem>
    ): PresentableData {
        var result = PresentableData()
        for (squad in squads) {
            result[squad] = athletes.filter { it.squad_ids.contains(squad.id) }
        }
        result = result.filter { it.value.isNotEmpty() } as PresentableData
        return result

    }
}
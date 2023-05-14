package com.fraporitmos.criptocurrency.presentation.getCyptos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.fraporitmos.criptocurrency.common.Resource
import com.fraporitmos.criptocurrency.domain.use_case.getCryptos.getCyptosUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GetCrptosViewModel @Inject constructor(
    private val getCrptoUseCase: getCyptosUseCase
) : ViewModel() {

    private val _state = mutableStateOf(getCryptosState())
    val state: State<getCryptosState> = _state

    init {
        getCryptos()
    }
    private fun getCryptos(){
        getCrptoUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = getCryptosState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = getCryptosState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = getCryptosState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}


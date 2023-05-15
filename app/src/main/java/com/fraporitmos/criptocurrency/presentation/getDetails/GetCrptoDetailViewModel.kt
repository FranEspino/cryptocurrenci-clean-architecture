package com.fraporitmos.criptocurrency.presentation.getDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fraporitmos.criptocurrency.common.Constants
import com.fraporitmos.criptocurrency.common.Resource
import com.fraporitmos.criptocurrency.domain.use_case.getCryptoDetail.getCryptoDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GetCrptoDetailViewModel @Inject constructor(
    private val getCoinUseCase: getCryptoDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(getCryptoDetailState())
    val state: State<getCryptoDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_CRYPTO_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = getCryptoDetailState(crypto = result.data)
                }
                is Resource.Error -> {
                    _state.value = getCryptoDetailState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = getCryptoDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}

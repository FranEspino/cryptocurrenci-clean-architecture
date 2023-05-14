package com.fraporitmos.criptocurrency.presentation.getCyptos

import com.fraporitmos.criptocurrency.domain.model.Crypto

data class getCryptosState(
    val isLoading: Boolean = false,
    val coins: List<Crypto> = emptyList(),
    val error: String = ""
)

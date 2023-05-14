package com.fraporitmos.criptocurrency.presentation.getDetails

import com.fraporitmos.criptocurrency.domain.model.Crypto
import com.fraporitmos.criptocurrency.domain.model.CryptoDetail

data class getCryptoDetailState(
    val isLoading: Boolean = false,
    val crypto: CryptoDetail? = null,
    val error: String = ""
)

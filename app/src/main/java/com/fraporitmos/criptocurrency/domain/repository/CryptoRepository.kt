package com.fraporitmos.criptocurrency.domain.repository

import com.fraporitmos.criptocurrency.data.remote.dto.CryptoDetailDto.CryptoDetailDto
import com.fraporitmos.criptocurrency.data.remote.dto.CryptoDto.CryptoDto

interface CryptoRepository {
    suspend fun getCryptos(): List<CryptoDto>
    suspend fun getDetailCrypto(coinId: String): CryptoDetailDto
}
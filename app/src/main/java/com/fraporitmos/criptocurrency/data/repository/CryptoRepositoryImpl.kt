package com.fraporitmos.criptocurrency.data.repository

import com.fraporitmos.criptocurrency.data.remote.dto.CryptoDetailDto.CryptoDetailDto
import com.fraporitmos.criptocurrency.data.remote.dto.CryptoDto.CryptoDto
import com.fraporitmos.criptocurrency.data.remote.dto.CrytoApiInterface
import com.fraporitmos.criptocurrency.domain.repository.CryptoRepository
import javax.inject.Inject


class CryptoRepositoryImpl @Inject constructor(
    private val api : CrytoApiInterface
): CryptoRepository {

    override suspend fun getCryptos(): List<CryptoDto> {
        return api.getCryptos()
    }

    override suspend fun getDetailCrypto(cryptoId: String): CryptoDetailDto {
        return api.getDetailCrypto(cryptoId)
    }


}
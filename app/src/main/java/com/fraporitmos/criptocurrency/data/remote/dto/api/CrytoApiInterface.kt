package com.fraporitmos.criptocurrency.data.remote.dto

import com.fraporitmos.criptocurrency.data.remote.dto.CryptoDetailDto.CryptoDetailDto
import com.fraporitmos.criptocurrency.data.remote.dto.CryptoDto.CryptoDto
import retrofit2.http.GET
import retrofit2.http.Path
interface CrytoApiInterface {

    @GET("/v1/coins")
    suspend fun getCryptos(): List<CryptoDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getDetailCrypto(
        @Path("coinId") coindId: String
    ): CryptoDetailDto
}

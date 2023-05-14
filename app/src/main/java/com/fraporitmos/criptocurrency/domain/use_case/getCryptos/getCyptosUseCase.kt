package com.fraporitmos.criptocurrency.domain.use_case.getCryptos

import com.fraporitmos.criptocurrency.common.Resource
import com.fraporitmos.criptocurrency.data.remote.dto.CryptoDto.toCrypto
import com.fraporitmos.criptocurrency.domain.model.Crypto
import com.fraporitmos.criptocurrency.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class getCyptosUseCase @Inject constructor(
    private val repository: CryptoRepository
) {
    operator fun invoke(): Flow<Resource<List<Crypto>>> = flow {
        try {
            emit(Resource.Loading<List<Crypto>>())
            val coins = repository.getCryptos().map { it.toCrypto() }
            emit(Resource.Success<List<Crypto>>(coins))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Crypto>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Crypto>>("Couldn't reach server. Check your internet connection."))
        }
    }
}
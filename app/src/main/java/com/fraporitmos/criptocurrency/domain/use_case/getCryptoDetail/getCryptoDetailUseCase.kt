package com.fraporitmos.criptocurrency.domain.use_case.getCryptoDetail

import android.util.Log
import com.fraporitmos.criptocurrency.common.Resource
import com.fraporitmos.criptocurrency.data.remote.dto.CryptoDetailDto.toCryptoDetail
import com.fraporitmos.criptocurrency.domain.model.CryptoDetail
import com.fraporitmos.criptocurrency.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class getCryptoDetailUseCase @Inject constructor(
private val repository: CryptoRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CryptoDetail>> = flow {
        try {
            emit(Resource.Loading<CryptoDetail>())
            val coin = repository.getDetailCrypto(coinId).toCryptoDetail()
            emit(Resource.Success<CryptoDetail>(coin))
        } catch(e: HttpException) {
            emit(Resource.Error<CryptoDetail>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<CryptoDetail>("Couldn't reach server. Check your internet connection."))
        }
    }
}
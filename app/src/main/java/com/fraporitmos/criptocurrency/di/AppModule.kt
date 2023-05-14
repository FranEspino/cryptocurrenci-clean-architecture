package com.fraporitmos.criptocurrency.di

import com.fraporitmos.criptocurrency.common.Constants
import com.fraporitmos.criptocurrency.common.Constants.CRYPTO_BASE_URL
import com.fraporitmos.criptocurrency.data.remote.dto.CrytoApiInterface
import com.fraporitmos.criptocurrency.data.repository.CryptoRepositoryImpl
import com.fraporitmos.criptocurrency.domain.repository.CryptoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //Proveedor de la interfaz de la api
    @Provides
    @Singleton
    fun provuideCryptoApi()
    : CrytoApiInterface{
    return Retrofit.Builder()
            .baseUrl(CRYPTO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CrytoApiInterface::class.java)
    }
    //Proveedor del repositorio
    @Provides
    @Singleton
    fun provideCryptoRepository(
        api: CrytoApiInterface
    ): CryptoRepository {
        return  CryptoRepositoryImpl(api)
    }


}
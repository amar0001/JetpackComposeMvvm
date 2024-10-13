package com.test.di

import com.test.medicine.data.MedicalRepository
import com.test.medicine.data.MedicalRepositoryImpl
import com.test.medicine.data.network.MedicineService
import com.test.medicine.data.database.MedicalDataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient() {
            install(ContentNegotiation) {
                json()
            }
        }
    }

    @Provides
    @Singleton
    fun provideMedicalApiService(httpClient: HttpClient, dao: MedicalDataDao): MedicineService {
        return MedicineService(httpClient, dao)
    }

    @Provides
    @Singleton
    fun provideMedicalRepository(apiService: MedicineService, dao: MedicalDataDao): MedicalRepository {
        return MedicalRepositoryImpl(apiService, dao)
    }
}

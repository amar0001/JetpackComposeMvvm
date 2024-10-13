package com.test.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.medicine.data.database.MedicalDataDao
import com.test.medicine.data.database.MedicalDataEntity
import com.test.medicine.data.database.ProblemConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(entities = [MedicalDataEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun medicalDataDao(): MedicalDataDao
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "medical_db.db").build()
    }

    @Provides
    @Singleton
    fun provideMedicalDataDao(db: AppDatabase): MedicalDataDao {
        return db.medicalDataDao()
    }
}

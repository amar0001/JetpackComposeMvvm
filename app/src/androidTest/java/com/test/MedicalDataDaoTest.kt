package com.test

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.test.database.AppDatabase
import com.test.medicine.data.database.MedicalDataDao
import com.test.medicine.data.database.MedicalDataEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MedicalDataDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: MedicalDataDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            AppDatabase::class.java
        ).build()
        dao = db.medicalDataDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertAndGetMedicalData() = runBlocking {
        val entity = MedicalDataEntity(problems = "Test Problems")
        dao.saveMedicalData(entity)

        val retrievedData = dao.getAllMedicalData()
        assertEquals(1, retrievedData.size)
        assertEquals("Test Problems", retrievedData[0].problems)
    }
}

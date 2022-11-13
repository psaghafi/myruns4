package com.example.parmida_saghafi_myruns2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ExerciseDatabaseDao {
    @Insert
    fun insertActivity(exercise: Exercise)

    @Query("SELECT * FROM ExerciseEntry")
    fun getAllExerciseEntries(): Flow<List<Exercise>>

    @Query("DELETE FROM ExerciseEntry")
    fun deleteAll()

    @Query("DELETE FROM ExerciseEntry WHERE id = :key")
    fun deleteEntry(key:Long)
}
package com.example.parmida_saghafi_myruns2

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ExerciseEntryRepository(private val databaseDao: ExerciseDatabaseDao) {
    var allEntries: Flow<List<Exercise>> = databaseDao.getAllExerciseEntries()

    fun insert(exercise: Exercise){
        CoroutineScope(IO).launch {
            databaseDao.insertActivity(exercise)
        }

    }

    fun delete(id:Long){
        CoroutineScope(IO).launch {
            databaseDao.deleteEntry(id)
        }
    }

    fun deleteAll(){
        CoroutineScope(IO).launch {
            databaseDao.deleteAll()
        }
    }
}
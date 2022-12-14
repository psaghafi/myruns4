package com.example.parmida_saghafi_myruns2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Exercise::class], version=1, exportSchema = false)
abstract class ExerciseDatabase:RoomDatabase() {
    abstract val exerciseDao:ExerciseDatabaseDao
    companion object{
        @Volatile
        private var INSTANCE: ExerciseDatabase?=null

        fun getInstance(context: Context): ExerciseDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,
                        ExerciseDatabase::class.java,"exercise_DB").build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}
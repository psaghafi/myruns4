package com.example.parmida_saghafi_myruns2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ExerciseEntry")

data class Exercise(
    @PrimaryKey(autoGenerate = true)
       var id: Long = 0L,

       @ColumnInfo(name = "inputType")
       var inputType: Int=0,  // Manual, GPS or automatic

       @ColumnInfo(name = "activityType")
       var activityType: Int = 0,   // Running, cycling etc.

       @ColumnInfo(name = "dateTime")
       var dateTime: String = "",    // When does this entry happen

       @ColumnInfo(name = "duration")
       var duration: Double = 0.0,      // Exercise duration in seconds

       @ColumnInfo(name = "distance")
       var distance: Double = 0.0,      // Distance traveled. Either in meters or feet.

       @ColumnInfo(name = "avgPace")
       var avgPace: Double = 0.0,      // Average pace

       @ColumnInfo(name = "avgSpeed")
       var avgSpeed: Double = 0.0,      // Average speed

       @ColumnInfo(name = "calorie")
       var calorie: Double = 0.0,      // Calories burnt

       @ColumnInfo(name = "climb")
       var climb: Double = 0.0,      // Climb. Either in meters or feet.

       @ColumnInfo(name = "heartRate")
       var heartRate: Double = 0.0,      // Heart rate

       @ColumnInfo(name = "comment")
       var comment: String = "",      // Comments

    // @ColumnInfo(name = "locationList")
    //var locationList: ArrayList<LatLng>  // Location list
    )

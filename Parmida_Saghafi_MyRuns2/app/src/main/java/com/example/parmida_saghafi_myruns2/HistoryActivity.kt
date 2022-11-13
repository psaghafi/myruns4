package com.example.parmida_saghafi_myruns2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlin.properties.Delegates

class HistoryActivity: AppCompatActivity() {
    private lateinit var database: ExerciseDatabase
    private lateinit var databasedao: ExerciseDatabaseDao
    private lateinit var repository: ExerciseEntryRepository
    private lateinit var viewModel: ExerciseEntryViewModel
    private lateinit var factory: ExerciseEntryViewModelFactory
//    var id by Delegates.notNull<Long>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_activity)
        var id: Long
        var input: String
        var activity:String
        var date: String
        var duration: String
        var distance: String
        var calories: String
        var heart: String
        var extras = intent.extras
        if(extras!= null){
            id = extras.getString("id","0").toLong()
            input = extras.getString("input","")
            activity = extras.getString("activity","")
            date = extras.getString("datetime","")
            duration = extras.getString("duration","")
            distance = extras.getString("distance","")
            calories = extras.getString("calories","")
            heart = extras.getString("heartRate","")

            var inputTypeView = findViewById<EditText>(R.id.historyInputType)
            var activityTypeText = findViewById<EditText>(R.id.historyActivityType)
            var dateTimeText = findViewById<EditText>(R.id.historyDateTime)
            var durationText = findViewById<EditText>(R.id.historyDuration)
            var distanceText =  findViewById<EditText>(R.id.historyDistance)
            var caloriesText =  findViewById<EditText>(R.id.historyCalories)
            var heartRateText = findViewById<EditText>(R.id.historyHeartRate)
            var deleteButton = findViewById<Button>(R.id.deleteButtonHistory)
            database = ExerciseDatabase.getInstance(this)
            databasedao = database.exerciseDao
            repository = ExerciseEntryRepository(databasedao)
            factory = ExerciseEntryViewModelFactory(repository)
            viewModel = ViewModelProvider(this, factory).get(ExerciseEntryViewModel::class.java)

            inputTypeView.setText(inputTypeConverter(input))
            activityTypeText.setText(activityTypeConverter(activity))
            dateTimeText.setText(date)
            durationText.setText(duration+"mins"+" 0 secs")
            distanceText.setText(distance + " Miles")
            caloriesText.setText(calories + " cals")
            heartRateText.setText(heart + " bpm")

            deleteButton.setOnClickListener(){

                //onDeleteClicked(this)
                viewModel.deleteEntry(id)
                Log.d("Main","Entry"+id+ "deleted")
                finish()


            }
        }



    }
    /*fun onDeleteClicked(view: HistoryActivity){
        viewModel.deleteEntry(id)
        Log.d("Main","Entry"+id+ "deleted")
        finish()

    }*/
    private fun inputTypeConverter(id: String): String
    {
        var string = ""
        when(id)
        {
            "0" -> {string = "Manual Entry"}
            "1" -> {string = "GPS"}
            "2" -> {string = "Automatic"}
        }
        return string
    }
    private fun activityTypeConverter(id: String): String
    {
        var string = ""
        when(id)
        {
            "0" ->{string = "Running"}
            "1" ->{string = "Walking"}
            "2" ->{string = "Standing"}
            "3" ->{string = "Cycling"}
            "4" ->{string = "Hiking"}
            "5" ->{string = "Downhill Skiing"}
            "6" ->{string = "Cross-Country Skiing"}
            "7" ->{string = "Snowboarding"}
            "8" ->{string = "Skating"}
            "9" ->{string = "Swimming"}
            "10" ->{string = "Mountain Biking"}
            "11" ->{string = "Wheelchair"}
            "12" ->{string = "Elliptical"}
            "13" ->{string = "Other"}
        }
        return string
    }


}
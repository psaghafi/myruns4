package com.example.parmida_saghafi_myruns2

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*


class ManualInputActivity : AppCompatActivity(){
   /* private var day=0
    private var month=0
    private var year=0
    private var hour=0
    private var minute=0

    private  var savedDay=0
    private var savedMonth=0
    private var savedYear=0
    private var savedHour=0
    private var savedMinute=0*/


    private lateinit var database: ExerciseDatabase
    private lateinit var databasedao: ExerciseDatabaseDao
    private lateinit var repository: ExerciseEntryRepository
    private lateinit var viewModel: ExerciseEntryViewModel
    private lateinit var factory: ExerciseEntryViewModelFactory
    private var duration: Double = 0.0
    private var distance: Double = 0.0
    private var calories: Double = 0.0
    private var heartrate: Double = 0.0
    private var comment: String = ""
    private lateinit var dateDialog: DatePickerDialog
    private lateinit var timeDialog: TimePickerDialog





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manual_input)

        database = ExerciseDatabase.getInstance(this)
        databasedao = database.exerciseDao
        repository = ExerciseEntryRepository(databasedao)
        factory = ExerciseEntryViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ExerciseEntryViewModel::class.java)
        val entry = Exercise()
        var inputType: String
        var activityType: String
        val cal=Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        var month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        var hour = cal.get(Calendar.HOUR_OF_DAY)
        var minutes = cal.get(Calendar.MINUTE)
        var seconds = cal.get(Calendar.SECOND)





        val listView=findViewById<ListView>(R.id.manualInputListView)
        val listViewcategories=arrayOf("Date","Time","Duration","Distance","Calories","Heart Rate","Comment")
        val arrayAdapter:ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1,listViewcategories)
        listView.adapter=arrayAdapter
        listView.setOnItemClickListener { parent, view, position, id ->

            //calendar
            if(position==0){
                dateDialog = DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        cal.set(Calendar.YEAR, year)
                        cal.set(Calendar.MONTH, month)
                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)},
                    year, month, day)
                dateDialog.show()
                //pickDate()

            }
            //time
            else if(position==1){
                timeDialog = TimePickerDialog(this,
                    TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                        cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        cal.set(Calendar.MINUTE, minute)},
                    hour, minutes, true)
                cal.set(Calendar.SECOND, 0)
                timeDialog.show()
               // pickTime()
            }
            //duration
            else if(position==2){
                val builder: AlertDialog.Builder=AlertDialog.Builder(this)
                val inflater = layoutInflater
                val dialogLayout = inflater.inflate(R.layout.duration_dialog, null)
                val editText: EditText=dialogLayout.findViewById(R.id.duration)

                with(builder){
                    setTitle("Duration")
                    setPositiveButton("OK"){dialog, which ->
                        var durationString: String = editText.text.toString()
                        if (durationString.isNotEmpty())
                            entry.duration=durationString.toDouble()
                    }
                    setNegativeButton("CANCEL"){dialog, which->
                        Log.d("Main","duration cancel button clicked")
                    }
                    setView(dialogLayout)
                    show()

                }


            }
            //distance
            else if(position==3){
                val builder: AlertDialog.Builder=AlertDialog.Builder(this)
                val inflater = layoutInflater
                val dialogLayout = inflater.inflate(R.layout.duration_dialog, null)
                val editText: EditText=dialogLayout.findViewById(R.id.duration)

                with(builder){
                    setTitle("Distance")
                    setPositiveButton("OK"){dialog, which ->
                        var distanceString: String = editText.text.toString()
                        if (distanceString.isNotEmpty())
                        entry.distance=distanceString.toDouble()
                    }
                    setNegativeButton("CANCEL"){dialog, which->

                        Log.d("Main","distance cancel button clicked")
                    }
                    setView(dialogLayout)
                    show()

                }


            }

            //calories
            else if(position==4){
                val builder: AlertDialog.Builder=AlertDialog.Builder(this)
                val inflater = layoutInflater
                val dialogLayout = inflater.inflate(R.layout.calories_dialog, null)
                val editText: EditText=dialogLayout.findViewById(R.id.calories)

                with(builder){
                    setTitle("Calories")
                    setPositiveButton("OK"){dialog, which ->
                        var caloriesString: String = editText.text.toString()
                        if (caloriesString.isNotEmpty())
                        entry.calorie=caloriesString.toDouble()
                    }
                    setNegativeButton("CANCEL"){dialog, which->

                        Log.d("Main","calories cancel button clicked")
                    }
                    setView(dialogLayout)
                    show()

                }


            }

            //Heart Rate
            else if(position==5){
                val builder: AlertDialog.Builder=AlertDialog.Builder(this)
                val inflater = layoutInflater
                val dialogLayout = inflater.inflate(R.layout.hearrate_dialog, null)
                val editText: EditText=dialogLayout.findViewById(R.id.heartRate)

                with(builder){
                    setTitle("Heart Rate")
                    setPositiveButton("OK"){dialog, which ->
                        var heartrateString: String = editText.text.toString()
                        if (heartrateString.isNotEmpty())
                        entry.heartRate=heartrateString.toDouble()
                    }
                    setNegativeButton("CANCEL"){dialog, which->

                        Log.d("Main","heart rate cancel button clicked")
                    }
                    setView(dialogLayout)
                    show()

                }


            }


            //Comments
            else if(position==6){
                val builder: AlertDialog.Builder=AlertDialog.Builder(this)
                val inflater = layoutInflater
                val dialogLayout = inflater.inflate(R.layout.comments_dialog, null)
                val editText: EditText=dialogLayout.findViewById(R.id.comment)

                with(builder){
                    setTitle("Comments")
                    setPositiveButton("OK"){dialog, which ->
                        var commentString: String = editText.text.toString()
                        if (commentString.isNotEmpty())
                        entry.comment=commentString
                    }
                    setNegativeButton("CANCEL"){dialog, which->

                        Log.d("Main","comment cancel button clicked")
                    }
                    setView(dialogLayout)
                    show()

                }


            }

        }
        var extra = intent.extras
        if (extra != null) {
            inputType = extra.getString("inputTypePosition", "")
            activityType = extra.getString("activityTypePosition", "")
            entry.inputType = inputType.toInt()
            entry.activityType = activityType.toInt()
        }
        val button: Button = findViewById(R.id.save_button)
        button.setOnClickListener {
            val temp_format = SimpleDateFormat("HH:mm:ss MMM dd yyyy")
            entry.dateTime = temp_format.format(cal.time)
            viewModel.insert(entry)
            Toast.makeText(this, "Entry has been recorded", Toast.LENGTH_LONG).show()
            finish()
        }
        val button2: Button = findViewById(R.id.cancel_button)
        button2.setOnClickListener {
            Toast.makeText(this, "Entry has been discarded", Toast.LENGTH_LONG).show()
            finish()
        }
    }

/*
    private fun getDateCalendar(){
        var day=0
        var month=0
        var year=0
        val cal=Calendar.getInstance()
        day=cal.get(Calendar.DAY_OF_MONTH)
        month=cal.get(Calendar.MONTH)
        year=cal.get(Calendar.YEAR)
    }
    private fun pickDate(){
         var day=0
        var month=0
       var year=0
        var hour=0
        var minute=0

        var savedDay=0
         var savedMonth=0
         var savedYear=0
         var savedHour=0
        var savedMinute=0
        val cal=Calendar.getInstance()
        day=cal.get(Calendar.DAY_OF_MONTH)
        month=cal.get(Calendar.MONTH)
        year=cal.get(Calendar.YEAR)
        getDateCalendar()
        DatePickerDialog(this, this, year, month,day).show()


    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        var savedDay=0
        var savedMonth=0
        var savedYear=0
        val cal=Calendar.getInstance()
        savedDay=dayOfMonth
        savedMonth=month
        savedYear=year
        cal.set(savedDay,savedMonth,savedYear)
    }
    private fun getTime(){
        var day=0
        var month=0
        var year=0
        var hour=0
        var minute=0

        var savedDay=0
        var savedMonth=0
        var savedYear=0
        var savedHour=0
        var savedMinute=0
        val cal=Calendar.getInstance()
        hour=cal.get(Calendar.HOUR_OF_DAY)
        minute=cal.get(Calendar.MINUTE)
    }
    private fun pickTime(){
        var day=0
        var month=0
        var year=0
        var hour=0
        var minute=0

        var savedDay=0
        var savedMonth=0
        var savedYear=0
        var savedHour=0
        var savedMinute=0
        getTime()
        TimePickerDialog(this,this,hour,minute,true).show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        var day=0
        var month=0
        var year=0
        var hour=0
        var minute=0

        var savedDay=0
        var savedMonth=0
        var savedYear=0
        var savedHour=0
        var savedMinute=0
        val cal=Calendar.getInstance()
        savedHour=hourOfDay
        savedMinute=minute
        //cal.set(savedHour,savedMinute)

    }*/

}
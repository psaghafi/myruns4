package com.example.parmida_saghafi_myruns2

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MyListAdapter(val context: Context, private var exerciseList: List<Exercise> ) : BaseAdapter() {
    override fun getCount(): Int {
        return exerciseList.size
    }

    override fun getItem(position: Int): Any {
        return exerciseList[position]
    }

    override fun getItemId(position: Int): Long {
        return exerciseList[position].id
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = View.inflate(context,R.layout.history_layout_adapter,null)

        val input_type_list = arrayOf("Manual Entry", "GPS", "Automatic")
        val exercise_type_list = arrayOf(
            "Running",
            "Walking",
            "Standing",
            "Cycling",
            "Hiking",
            "Downhill Skiing",
            "Cross-Country Skiing",
            "Snowboarding",
            "Skating",
            "Swimming",
            "Mountain Biking",
            "Wheelchair",
            "Elliptical",
            "Other")

        val input = view.findViewById(R.id.inputType) as TextView
        val activity = view.findViewById(R.id.activityType) as TextView
        val datetime = view.findViewById(R.id.dateTime) as TextView
        val miles = view.findViewById(R.id.miles) as TextView
        val exerciseTime = view.findViewById(R.id.exerciseTime) as TextView

        input.text = input_type_list[exerciseList[position].inputType] + ": "
        activity.text = exercise_type_list[exerciseList[position].activityType]+", "
        datetime.text = exerciseList[position].dateTime.toString()+" "
        miles.text = exerciseList[position].distance.toString()+"Miles, "
        exerciseTime.text = exerciseList[position].duration.toString()+"mins 0 secs"
        return view
    }
    fun update(newList: List<Exercise>){
        exerciseList = newList
    }

}
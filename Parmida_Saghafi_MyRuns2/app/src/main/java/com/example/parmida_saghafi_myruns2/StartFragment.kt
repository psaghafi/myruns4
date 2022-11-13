package com.example.parmida_saghafi_myruns2
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import android.content.Intent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class StartFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.start, container, false)
        val startButton: Button = view.findViewById(R.id.buttonStart)
        //startButton.setOnClickListener(this)
        var inputTypeSpinner: Spinner = view.findViewById(R.id.spinnerInputType) as Spinner
        var activityTypeSpinner: Spinner = view.findViewById(R.id.spinnerActivityType) as Spinner

        // val listViewcategories=arrayOf("Date","Time","Duration","Distance","Calories","Heart Rate","Comment")
        //val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1,listViewcategories)
        //listView.adapter=arrayAdapter
        lateinit var arrayAdapter2: ArrayAdapter<String>
        var inputTypeData = arrayListOf<String>("Manual", "GPS", "Automatic")
        arrayAdapter2 = ArrayAdapter(requireContext().applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            inputTypeData)
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        inputTypeSpinner.adapter = arrayAdapter2

        var ActivityTypeData = arrayListOf<String>("Running",
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
        var arrayAdapter: ArrayAdapter<String> = ArrayAdapter(requireContext().applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            ActivityTypeData)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        activityTypeSpinner.adapter = arrayAdapter

        inputTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                startButton.setOnClickListener {
                    if (parent != null) {
                        if (parent.getItemAtPosition(position).toString() == "Manual") {
                            val intent = Intent(view!!.context, ManualInputActivity::class.java)
                            intent.putExtra("inputTypePosition", inputTypeSpinner.selectedItemPosition.toString())
                            intent.putExtra("activityTypePosition", activityTypeSpinner.selectedItemPosition.toString())
                            startActivity(intent)
                        }
                        if(parent.getItemAtPosition(position).toString() == "GPS"){
                            val intent = Intent(view!!.context, MapsActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //code

            }

        }

        return view
    }
}


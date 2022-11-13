package com.example.parmida_saghafi_myruns2
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class HistoryFragment: Fragment() {

    private lateinit var database: ExerciseDatabase
    private lateinit var databasedao: ExerciseDatabaseDao
    private lateinit var repository: ExerciseEntryRepository
    private lateinit var viewModel: ExerciseEntryViewModel
    private lateinit var factory: ExerciseEntryViewModelFactory
    private lateinit var myList: ListView
    private lateinit var mylistAdapter: MyListAdapter
    private lateinit var arrayList: ArrayList<Exercise>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        super.onCreate(savedInstanceState)
        val view= inflater.inflate(R.layout.history, container,false)
        myList = view.findViewById(R.id.ListViewHistory)
        database = ExerciseDatabase.getInstance(requireActivity())
        databasedao = database.exerciseDao
        repository = ExerciseEntryRepository(databasedao)
        factory = ExerciseEntryViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(ExerciseEntryViewModel::class.java)

        arrayList = ArrayList()
        mylistAdapter = MyListAdapter(requireActivity(), arrayList)
        myList.adapter = mylistAdapter
        viewModel.allEntriesLiveData.observe(requireActivity()) {
            mylistAdapter.update(it)
            mylistAdapter.notifyDataSetChanged()
        }

        myList.setOnItemClickListener{
                _,_, position,_ ->
            var selectedPosition: Exercise = viewModel.selected(position)!!
            val intent = Intent(context, HistoryActivity::class.java)
            var id = selectedPosition.id
            var inputType = selectedPosition.inputType
            var activityType = selectedPosition.activityType
            var dateTime = selectedPosition.dateTime
            var duration = selectedPosition.duration
            var distance = selectedPosition.distance
            var calorie = selectedPosition.calorie
            var heartRate = selectedPosition.heartRate

            intent.putExtra("id",id.toString())
            intent.putExtra("input", inputType.toString())
            intent.putExtra("activity", activityType.toString())
            intent.putExtra("datetime", dateTime.toString())
            intent.putExtra("duration", duration.toString())
            intent.putExtra("distance", distance.toString())
            intent.putExtra("calories", calorie.toString())
            intent.putExtra("heartRate", heartRate.toString())

            startActivity(intent)

        }
        return view
    }
}

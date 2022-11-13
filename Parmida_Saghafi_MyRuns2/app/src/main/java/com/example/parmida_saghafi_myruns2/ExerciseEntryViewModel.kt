package com.example.parmida_saghafi_myruns2

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.CreationExtras

class ExerciseEntryViewModel(private val repository: ExerciseEntryRepository):
    ViewModel() {
    val allEntriesLiveData: LiveData<List<Exercise>> = repository.allEntries.asLiveData()
    fun insert(exercise: Exercise){
        repository.insert(exercise)
    }

    fun deleteEntry(id:Long){

            repository.delete(id)

    }

    fun selected(position:Int): Exercise? {
        val exerciseList = allEntriesLiveData.value
        if(exerciseList!= null && exerciseList.size >0){
            return exerciseList[position]
        }
        return null
    }

    fun deleteAll(){
        val exerciseList = allEntriesLiveData.value
        if(exerciseList!= null && exerciseList.size >0){
            repository.deleteAll()
        }
    }

}
class ExerciseEntryViewModelFactory(private val repository: ExerciseEntryRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(ExerciseEntryViewModel::class.java))
            return ExerciseEntryViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
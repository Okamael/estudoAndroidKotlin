package com.estudos.android.listadehabitos.collections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.estudos.android.listadehabitos.core.HabitsRepository


class HabitListViewModel (private val repository: HabitsRepository) : ViewModel(){

    /**
     * Mutable Live Data that initialize with the current list of saved Habits.
     */
    private val uiState: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>(UiState(habitItemList = repository.fetchHabits()))
    }

    /**
     * Expose the uiState as LiveData to UI.
     */
    fun stateOnceAndStream(): LiveData<UiState> {
        return uiState
    }

    /**
     * Toggle a Habit complete status.
     */
    fun toggleHabitCompleted(id: String) {
        repository.toggleHabitCompleted(id)
        refreshHabitList()
    }


    fun addRandomHabit() {
        repository.addRandomNewHabit()
        refreshHabitList()
    }

    private fun refreshHabitList() {
        uiState.value?.let { currentUiState ->
            uiState.value = currentUiState.copy(
                habitItemList = repository.fetchHabits()
            )
        }
    }

    /**
     * UI State containing every data needed to show Habits.
     */
    data class UiState(val habitItemList: List<HabitItem>)

    /**
     * ViewModel Factory needed to provide Repository injection to ViewModel.
     */
    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: HabitsRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HabitListViewModel(repository) as T
        }
    }
}
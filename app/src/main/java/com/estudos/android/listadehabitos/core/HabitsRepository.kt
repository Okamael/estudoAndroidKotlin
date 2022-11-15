package com.estudos.android.listadehabitos.core

import com.estudos.android.listadehabitos.collections.HabitItem

interface HabitsRepository {

    fun fetchHabits(): List<HabitItem>

    fun addRandomNewHabit()

    fun toggleHabitCompleted(id: String)
}
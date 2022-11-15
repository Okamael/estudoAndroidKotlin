package com.estudos.android.listadehabitos.dummy

import com.estudos.android.listadehabitos.collections.HabitItem
import com.estudos.android.listadehabitos.core.HabitsRepository
import java.util.*

object MockHabits : HabitsRepository {
    //Cria lista com todos os possiveis itens da lista
    private val randomHabitList = listOf(
        HabitItem(
            id = UUID.randomUUID().toString(),
            title = "Estudar Programação Android",
            subtitle = "Replicar listas do curso",
            isCompleted = false

        ),
        HabitItem(
            id = UUID.randomUUID().toString(),
            title = "Estudar Programação Android",
            subtitle = "Replicar listas do curso usando Flutter",
            isCompleted = false

        ),
        HabitItem(
            id = UUID.randomUUID().toString(),
            title = "Estudar Programação Android",
            subtitle = "Algoritimos",
            isCompleted = false

        ),
        HabitItem(
            id = UUID.randomUUID().toString(),
            title = "Estudar Programação Android",
            subtitle = "Replicar Criar uma lista",
            isCompleted = false

        )
    )

    // cria uma lista mutavel a ser iniciada e populada
    private val habitItemList: MutableList<HabitItem> = mutableListOf(
        HabitItem(
            id = UUID.randomUUID().toString(),
            title = "Lendo sobre algoritimos",
            subtitle = "Reproduzir usando uri judge",
            isCompleted = false
        )
    )

    // instancia uma lista mutavel com  um objeito instanciado.
    override fun fetchHabits() = habitItemList.map { it.copy() }

    //adiciona um novo habito de forma aleatoria.
    override fun addRandomNewHabit() {
        habitItemList.add(randomHabit())
    }

    //marca quando o habito estiver completo
    override fun toggleHabitCompleted(id: String) {
        val habitIndex = findHabitIndexById(id)
        val habit = habitItemList[habitIndex]
        habitItemList[habitIndex] = habit.copy(isCompleted = !habit.isCompleted)
    }

    //pega um objeto de forma aleatoriamente e coloca um id
    private fun randomHabit() = randomHabitList.random().copy(
        id = UUID.randomUUID().toString()
    )

    //busca o indice de um id
    private fun findHabitIndexById(id: String) = habitItemList.indexOfFirst { habitItem ->
        habitItem.id == id
    }
}
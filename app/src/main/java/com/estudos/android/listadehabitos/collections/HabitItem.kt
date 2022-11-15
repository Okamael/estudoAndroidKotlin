package com.estudos.android.listadehabitos.collections

/**
 * Habit model representa um item da listView
 * @param title o titulo do habito
 * @param subtitle subtitulo do habito
 * @param isCompleted se esta com o habito completo ou não.
 *
 */
data class HabitItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val isCompleted: Boolean
)

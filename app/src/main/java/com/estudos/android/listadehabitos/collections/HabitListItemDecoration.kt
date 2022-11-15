package com.estudos.android.listadehabitos.collections

import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.estudos.android.listadehabitos.R

class HabitListItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val space = context.resources.getDimensionPixelSize(R.dimen.simple_margin)

    override  fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ){
        outRect.left=space
        outRect.right = space
        outRect.bottom = space
        outRect.top = space
    }
}
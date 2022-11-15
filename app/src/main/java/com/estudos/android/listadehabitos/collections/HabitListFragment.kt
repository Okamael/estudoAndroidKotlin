package com.estudos.android.listadehabitos.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.estudos.android.listadehabitos.R
import com.estudos.android.listadehabitos.databinding.FragmentHabitListBinding
import com.estudos.android.listadehabitos.dummy.MockHabits


class HabitListFragment : Fragment() {

    private var _binding: FragmentHabitListBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: HabitListAdapter

    private val viewModel: HabitListViewModel by activityViewModels {
        HabitListViewModel.Factory(MockHabits)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = HabitListAdapter(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHabitListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Observer UI State for changes.
        viewModel
            .stateOnceAndStream()
            .observe(viewLifecycleOwner) {
                bindUiState(it)
            }
    }

    private fun bindUiState(uiState: HabitListViewModel.UiState) {
        adapter.updateHabits(uiState.habitItemList)
    }

    private fun addingDividerDecoration() {
        // Adding Line between items with MaterialDividerItemDecoration
        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)

        // Adding the line at the end of the list
        divider.isLastItemDecorated = true

        val resources = requireContext().resources

        // Adding start spacing
        divider.dividerInsetStart = resources.getDimensionPixelSize(R.dimen.horizontal_margin)
        // Defining size of the line
        divider.dividerThickness = resources.getDimensionPixelSize(R.dimen.divider_height)
        divider.dividerColor = ContextCompat.getColor(requireContext(), R.color.primary_200)

        binding.habitRecyclerView.addItemDecoration(divider)
    }

    private fun addingDividerSpace() {
        binding.habitRecyclerView.addItemDecoration(HabitListItemDecoration(requireContext()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
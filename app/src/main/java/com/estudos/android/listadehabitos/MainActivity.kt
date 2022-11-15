package com.estudos.android.listadehabitos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.estudos.android.listadehabitos.collections.HabitListAdapter
import com.estudos.android.listadehabitos.collections.HabitListViewModel
import com.estudos.android.listadehabitos.databinding.ActivityMainBinding
import com.estudos.android.listadehabitos.dummy.MockHabits

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val viewModel: HabitListViewModel by viewModels {
        HabitListViewModel.Factory(MockHabits)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setupNavigation()

        binding.fab.setOnClickListener{ _ -> viewModel.addRandomHabit()}
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
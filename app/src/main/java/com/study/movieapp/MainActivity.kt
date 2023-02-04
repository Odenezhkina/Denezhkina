package com.study.movieapp

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.study.ui.SearchFragment
import com.study.movieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        with(binding) {
            bottomNav.setupWithNavController(navController)
            setSupportActionBar(toolbar)
        }
        setupActionBarWithNavController(
            navController = navController
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu?.findItem(R.id.search_action)
        searchItem?.iconTintList = ColorStateList.valueOf(getColor(com.study.ui.R.color.main_blue))
        val searchView = searchItem?.actionView as? SearchView
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                val navHostFragment: Fragment? =
                    supportFragmentManager.findFragmentById(R.id.fragment_container)
                navHostFragment?.childFragmentManager?.fragments?.get(0)
                    ?.takeIf { it is SearchFragment }?.let { searchFragment ->
                        (searchFragment as SearchFragment).onSearchQueryChanged(p0)
                    }
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}

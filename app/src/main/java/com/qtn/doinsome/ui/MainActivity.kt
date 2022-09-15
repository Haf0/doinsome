package com.qtn.doinsome.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.color.DynamicColors
import com.google.android.material.navigation.NavigationBarView.LABEL_VISIBILITY_SELECTED
import com.qtn.doinsome.R
import com.qtn.doinsome.databinding.ActivityMainBinding
import com.qtn.doinsome.ui.favorite.FavoriteFragment
import com.qtn.doinsome.ui.home.HomeFragment
import com.qtn.doinsome.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openFragment(HomeFragment())

        binding.bottomNavigation.labelVisibilityMode = LABEL_VISIBILITY_SELECTED


        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.home -> {
                    openFragment(HomeFragment())
                    true
                }
                R.id.search -> {
                    openFragment(SearchFragment())
                    true
                }
                R.id.watchlist -> {
                    openFragment(FavoriteFragment())
                    true
                }
                else -> false

            }
        }



    }
    private fun openFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}
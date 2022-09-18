package com.qtn.doinsome.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView.LABEL_VISIBILITY_SELECTED
import com.qtn.doinsome.R
import com.qtn.doinsome.databinding.ActivityMainBinding
import com.qtn.doinsome.ui.favorite.FavoriteFragment
import com.qtn.doinsome.ui.home.HomeFragment
import com.qtn.doinsome.ui.login.LoginActivity
import com.qtn.doinsome.ui.search.SearchFragment
import com.qtn.doinsome.viewmodel.DatastoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: DatastoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.show()
        setContentView(binding.root)
        setSubtitleAppBar(R.string.upcoming_movies)
        openFragment(HomeFragment())

        binding.bottomNavigation.labelVisibilityMode = LABEL_VISIBILITY_SELECTED


        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.home -> {
                    setSubtitleAppBar(R.string.upcoming_movies)
                    openFragment(HomeFragment())
                    true
                }
                R.id.search -> {
                    setSubtitleAppBar(R.string.search)
                    openFragment(SearchFragment())
                    true
                }
                R.id.watchlist -> {
                    setSubtitleAppBar(R.string.watchlist)
                    openFragment(FavoriteFragment())
                    true
                }
                else -> false

            }
        }

        binding.topAppBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.logout -> {
                    viewModel.logout()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
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
    private fun setSubtitleAppBar(id:Int){
        binding.topAppBar.subtitle = getString(id)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_bar_menu,menu)
        return true
    }

}
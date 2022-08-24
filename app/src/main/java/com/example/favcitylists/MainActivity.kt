package com.example.favcitylists

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    private val navigator = AppNavigator(this, R.id.fragment_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        if (savedInstanceState == null) {
            viewModel.routeToCityListScreen()
        }
    }


    override fun onResumeFragments() {
        super.onResumeFragments()
        viewModel.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        viewModel.navigatorHolder.removeNavigator()
        super.onPause()
    }
}
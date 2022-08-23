package com.example.favcitylists.screens

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.favcitylists.screens.main.CityListFragment
import com.example.favcitylists.screens.newlist.NewCustomCityListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    class CityListScreen : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment {
            return CityListFragment.newInstance()
        }
    }

    class NewListScreen : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment {
            return NewCustomCityListFragment.newInstance()
        }
    }
}
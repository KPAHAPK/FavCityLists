package com.example.favtownlists.screens

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.favtownlists.screens.main.CityListFragment
import com.example.favtownlists.screens.mylist.CustomCityListsFragment
import com.example.favtownlists.screens.newlist.NewCustomCityListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    class CityListScreen() : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment {
            return CityListFragment.newInstance()
        }
    }

    class MyListsScreen() : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment {
            return CustomCityListsFragment.newInstance()
        }
    }

    class NewListScreen() : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment {
            return NewCustomCityListFragment.newInstance()
        }
    }
}
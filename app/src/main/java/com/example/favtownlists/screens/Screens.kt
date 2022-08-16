package com.example.favtownlists.screens

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.favtownlists.screens.main.CityListFragment
import com.example.favtownlists.screens.mylist.MyListsFragment
import com.example.favtownlists.screens.newlist.NewListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    class CityListScreen() : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment {
            return CityListFragment.newInstance()
        }
    }

    class MyListsScreen() : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment {
            return MyListsFragment.newInstance()
        }
    }

    class NewListScreen() : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment {
            return NewListFragment.newInstance()
        }
    }
}
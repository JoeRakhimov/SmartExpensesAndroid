package com.joerakhimov.smartexpenses.launcher

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.PagerAdapter
import androidx.fragment.app.FragmentPagerAdapter


class LauncherPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    // Returns total number of pages
    override fun getCount(): Int {
        return NUM_ITEMS
    }

    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 // Fragment # 0 - This will show FirstFragment
            -> return LauncherFragment.newInstance("", "Page # 1")
            1 // Fragment # 0 - This will show FirstFragment different title
            -> return LauncherFragment.newInstance("", "Page # 2")
            2 // Fragment # 1 - This will show SecondFragment
            -> return LauncherFragment.newInstance("", "Page # 3")
            else -> return LauncherFragment.newInstance("", "Page # 3")
        }
    }

    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        return "Page $position"
    }

    companion object {
        private val NUM_ITEMS = 3
    }

}
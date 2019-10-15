package com.joerakhimov.smartexpenses.screen.launcher

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.joerakhimov.smartexpenses.R


class LauncherPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    // Returns total number of pages
    override fun getCount(): Int {
        return NUM_ITEMS
    }

    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return LauncherFragment.newInstance(
                R.string.follow_expenses,
                R.string.from_anywhere,
                R.drawable.welcome1
            )
            1 -> return LauncherFragment.newInstance(
                R.string.find_cheap_places,
                R.string.in_seconds,
                R.drawable.welcome2
            )
            2 -> return LauncherFragment.newInstance(
                R.string.share_expenses,
                R.string.with_anyone,
                R.drawable.welcome3
            )
            else -> return LauncherFragment.newInstance(
                R.string.follow_expenses,
                R.string.from_anywhere,
                R.drawable.welcome1
            )
        }
    }

//    // Returns the page title for the top indicator
//    override fun getPageTitle(position: Int): CharSequence? {
//        return "Page $position"
//    }

    companion object {
        private val NUM_ITEMS = 3
    }

}
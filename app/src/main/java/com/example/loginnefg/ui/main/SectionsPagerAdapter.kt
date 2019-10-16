package com.example.loginnefg.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.loginnefg.R
import com.example.loginnefg.Tabbed1Fragment
import com.example.loginnefg.Tabbed2Fragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3,
    R.string.tab_text_4
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager, bundle: Bundle) : FragmentPagerAdapter(fm) {

    val args = bundle

    override fun getItem(position: Int): Fragment {

        when(position){
            0 -> { val fragment= Tabbed1Fragment()
                    fragment.arguments = args
                    return fragment}

            1 -> { val fragment2= Tabbed2Fragment()
                fragment2.arguments = args
                return fragment2}

            else -> { val fragment= Tabbed1Fragment()
                fragment.arguments = args
                return fragment}
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 4
    }
}
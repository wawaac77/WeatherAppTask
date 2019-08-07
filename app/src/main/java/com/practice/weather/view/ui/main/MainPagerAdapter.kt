package com.practice.weather.view.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainPagerAdapter(fm: FragmentManager)
  : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

  private var fragmentList: ArrayList<Fragment> = ArrayList()

  private var fragmentTitleList: ArrayList<String> = ArrayList()

  override fun getItem(position: Int): Fragment {
    return fragmentList[position]
  }

  override fun getCount(): Int {
    return fragmentList.count()
  }

  override fun getPageTitle(position: Int): CharSequence? {
    return fragmentTitleList[position]
  }

  fun addFragment(fragment: Fragment, title: String) {
    fragmentList.add(fragment)
    fragmentTitleList.add(title)
  }

}

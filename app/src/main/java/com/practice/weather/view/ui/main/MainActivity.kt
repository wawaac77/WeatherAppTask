package com.practice.weather.view.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.practice.weather.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

  @Inject
  lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    initializeUI()
  }

  private fun initializeUI() {
    val adapter = MainPagerAdapter(supportFragmentManager)
    adapter.addFragment(WeatherListFragment(0),"A - Z")
    adapter.addFragment(WeatherListFragment(1), "Temperature")
    adapter.addFragment(WeatherListFragment(2), "Last Updated")
    main_viewpager.adapter = adapter
    main_viewpager.offscreenPageLimit = 3
    main_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
      override fun onPageScrollStateChanged(state: Int) = Unit
      override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit
      override fun onPageSelected(position: Int) {
      }
    })

  }

  override fun supportFragmentInjector(): AndroidInjector<Fragment> {
    return fragmentInjector
  }
}

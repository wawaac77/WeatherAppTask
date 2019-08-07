package com.practice.weather.view.ui.details.weather

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.practice.weather.R
import com.practice.weather.databinding.ActivityWeatherDetailBinding
import com.practice.weather.extension.activityBinding
import com.practice.weather.extension.viewModel
import com.practice.weather.extension.vmDelegate
import com.practice.weather.models.entity.Weather
import com.practice.weather.view.viewholder.WeatherListViewHolder
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.toolbar_default.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class WeatherDetailActivity : AppCompatActivity(), WeatherListViewHolder.Delegate {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private val vmDelegate by vmDelegate(WeatherDetailViewModel::class)
  private val binding by activityBinding<com.practice.weather.databinding.ActivityWeatherDetailBinding>(R.layout.activity_weather_detail)

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this@WeatherDetailActivity)
    super.onCreate(savedInstanceState)
    val vm = viewModel(vmDelegate, viewModelFactory)
    vm.postWeatherId(getWeatherFromIntent().id.toInt())
    with(binding) {
      lifecycleOwner = this@WeatherDetailActivity
      viewModel = vm
      weather = getWeatherFromIntent()
    }
    initializeUI()
  }

  private fun initializeUI() {
    toolbar_home.setOnClickListener { onBackPressed() }
    toolbar_title.text = getWeatherFromIntent().name
  }

  private fun getWeatherFromIntent(): Weather {
    return intent.getParcelableExtra(weatherId) as Weather
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    if (item?.itemId == android.R.id.home) onBackPressed()
    return false
  }

  override fun onItemClick(weather: Weather) {
//    val playVideoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Api.getYoutubeVideoPath(video.key)))
//    startActivity(playVideoIntent)
  }


  companion object {
    private const val weatherId = "weather"
    fun startActivityModel(context: Context?, weather: Weather) {
      context?.startActivity<WeatherDetailActivity>(weatherId to weather)
    }
  }
}

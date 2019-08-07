package com.practice.weather.view.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import com.practice.weather.R
import com.practice.weather.databinding.MainFragmentWeatherBinding
import com.practice.weather.extension.vm
import com.practice.weather.models.entity.Weather
import com.practice.weather.models.network.Status
import com.practice.weather.view.adapter.WeatherListAdapter
import com.practice.weather.view.ui.details.weather.WeatherDetailActivity
import com.practice.weather.view.viewholder.WeatherListViewHolder
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.main_fragment_weather.*
import javax.inject.Inject

@Suppress("SpellCheckingInspection")
class WeatherListFragment (private val type: Int) : Fragment(), WeatherListViewHolder.Delegate {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private val viewModel by lazy { vm(viewModelFactory, MainActivityViewModel::class) }
  private lateinit var binding: com.practice.weather.databinding.MainFragmentWeatherBinding
  private lateinit var paginator: RecyclerViewPaginator

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment_weather, container, false)
    binding.viewModel = viewModel
    binding.lifecycleOwner = this
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    initializeUI()
  }

  override fun onAttach(context: Context) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
    loadMore(page = 1)
  }

  private fun initializeUI() {
    recyclerView.adapter = WeatherListAdapter(this, type)
    recyclerView.layoutManager = GridLayoutManager(context, 1) as RecyclerView.LayoutManager?
    paginator = RecyclerViewPaginator(
      recyclerView = recyclerView,
      isLoading = {
        viewModel.getWeatherListValues()?.status == Status.LOADING
      },
      loadMore = { loadMore(it) },
      onLast = { viewModel.getWeatherListValues()?.onLastPage!! }
    )
    paginator.currentPage = 1
  }

  private fun loadMore(page: Int) {
    viewModel.postWeatherPage(page)
  }

  override fun onItemClick(weather: Weather) {
    WeatherDetailActivity.startActivityModel(context, weather)
  }
}

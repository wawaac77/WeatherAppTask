package com.practice.weather.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.practice.weather.models.entity.Weather

@Dao
interface WeatherDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertWeatherList(weathers: List<Weather>)

  @Update
  fun updateWeatherDetail(weather: Weather)

  @Query("SELECT * FROM Weather WHERE id = :id_")
  fun getWeather(id_: Int): Weather

  @Query("SELECT * FROM Weather")
  fun getWeatherList(): LiveData<List<Weather>>

  //TODO:Alice - query specific country
//  @Query("SELECT * FROM Weather WHERE country = :country_")
//  fun getSpecificCountryWeatherList(country_: Country): LiveData<List<Weather>>

}

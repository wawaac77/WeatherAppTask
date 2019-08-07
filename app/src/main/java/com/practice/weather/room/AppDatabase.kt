package com.practice.weather.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.practice.weather.models.entity.Weather
import com.practice.weather.utils.IntegerListConverter
import com.practice.weather.utils.StringListConverter

@Database(entities = [(Weather::class)],
  version = 3, exportSchema = false)
@TypeConverters(value = [(StringListConverter::class), (IntegerListConverter::class)])
abstract class AppDatabase : RoomDatabase() {
  abstract fun weatherDao(): WeatherDao
}

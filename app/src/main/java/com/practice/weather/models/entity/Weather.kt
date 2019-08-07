package com.practice.weather.models.entity

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(primaryKeys = [("id")])
data class Weather(
        @SerializedName("_venueID") val id: Int,
        @SerializedName("_name") val name: String?,
        @Embedded @SerializedName("_country") val country: Country ? = null,
        @SerializedName("_weatherCondition") val weatherCondition: String?,
        @SerializedName("_weatherConditionIcon") val weatherConditionIcon: String?,
        @SerializedName("_weatherWind") val weatherWind: String?,
        @SerializedName("_weatherHumidity") val weatherHumidity: String?,
        @SerializedName("_weatherTemp") val weatherTemp: String?,
        @SerializedName("_weatherFeelsLike") val weatherFeelsLike: String?,
        @Embedded @SerializedName("_sport") val sport: Sport ? = null,
        @SerializedName("_weatherLastUpdated") val weatherLastUpdated: Int
) : Parcelable


@Parcelize
data class Country(
        @SerializedName("_name") val countryName: String?,
        @SerializedName("_countryID") val countryID: String?
): Parcelable

@Parcelize
data class Sport(
        @SerializedName("_sportID") val sportID: String?,
        @SerializedName("_description") val description: String?
): Parcelable


package com.xevenition.database.model

import com.google.maps.android.clustering.ClusterItem
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

abstract class Home : ClusterItem {
    abstract var booliId: Int
    abstract var listPrice: Int
    abstract var rent: Int
    abstract var floor: Int
    abstract var livingArea: Int
    abstract var rooms: Int
    abstract var published: String
    abstract var constructionYear: Int
    abstract var objectType: String
    abstract var soldDate: String
    abstract var soldPrice: Int
    abstract var apartmentNumber: String
    abstract var soldPriceSource: String
    abstract var url: String
    abstract var plotArea: Int
    abstract var additionalArea: Int
    abstract var streetAddress: String
    abstract var countyName: String
    abstract var municipalityName: String
    abstract var longitude: Double
    abstract var latitude: Double
    abstract var sourceType: String
    abstract var sourceName: String
    abstract var sourceUrl: String

    fun getSquareMeterPrice(): Int {
        when {
            listPrice == 0 -> return 0
            livingArea == 0 -> return 0
            else -> {
                val squarePrice = listPrice / livingArea
                return squarePrice
            }
        }

    }

    fun getDaysOnBooli(): Int {
        val calendar = Calendar.getInstance()
        var date = Date()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        try {
            date = dateFormat.parse(published)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        calendar.time = date

        val today = Calendar.getInstance()

        val diff = today.timeInMillis - calendar.timeInMillis
        val days = diff / (24 * 60 * 60 * 1000)
        return days.toInt()
    }
}
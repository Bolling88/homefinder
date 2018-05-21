package com.xevenition.database.model.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "sold")
class Sold {
    @PrimaryKey
    var booliId: Int = 0
    var listPrice: Int = 0
    var rent: Int = 0
    var floor: Int = 0
    var livingArea: Int = 0
    var rooms: Int = 0
    var published: String? = null
    var constructionYear: Int = 0
    var objectType: String? = null
    var soldDate: String? = null
    var soldPrice: Int = 0
    var apartmentNumber: String? = null
    var soldPriceSource: String? = null
    var url: String? = null
    var plotArea: Int = 0
    var additionalArea: Int = 0
    var streetAddress: String? = null
    var countyName: String? = null
    var municipalityName: String? = null
    var longitude: Double = 0.toDouble()
    var latitude: Double = 0.toDouble()
    var sourceType: String? = null
    var sourceName: String? = null
    var sourceUrl: String? = null
}

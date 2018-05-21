package com.xevenition.database.model.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.xevenition.database.model.gson.GsonListing

@Entity(tableName = "listings")
class Listing {

    companion object Mapper{
         fun create(gson: GsonListing) : Listing{
             val listing = Listing()
             listing.booliId = gson.booliId
             listing.additionalArea = gson.additionalArea
             listing.constructionYear = gson.constructionYear
             listing.objectType = gson.objectType
             listing.objectType = gson.published
             listing.objectType = gson.url
             listing.floor = gson.floor
             listing.listPrice = gson.listPrice
             listing.livingArea = gson.livingArea
             listing.plotArea = gson.plotArea
             listing.rent = gson.rent
             listing.rooms = gson.rooms

             listing.streetAddress = gson.location.address.streetAddress
             listing.countyName = gson.location.region.countyName
             listing.municipalityName = gson.location.region.municipalityName
             listing.longitude = gson.location.position.longitude
             listing.latitude = gson.location.position.latitude
             listing.sourceName = gson.source.name
             listing.sourceType = gson.source.type
             listing.sourceUrl = gson.source.url
             return listing
         }
    }

    @PrimaryKey
    var booliId: Int = 0
    var listPrice: Int = 0
    var published: String? = null
    var objectType: String? = null
    var rooms: Int = 0
    var livingArea: Double = 0.toDouble()
    var additionalArea: Int = 0
    var rent: Int = 0
    var floor: Int = 0
    var constructionYear: Int = 0
    var url: String? = null
    var plotArea: Int = 0
    var streetAddress: String? = null
    var countyName: String? = null
    var municipalityName: String? = null
    var longitude: Double = 0.toDouble()
    var latitude: Double = 0.toDouble()
    var sourceType: String? = null
    var sourceName: String? = null
    var sourceUrl: String? = null
}

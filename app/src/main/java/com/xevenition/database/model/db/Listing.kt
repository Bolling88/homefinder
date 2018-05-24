package com.xevenition.database.model.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import com.xevenition.database.model.Home
import com.xevenition.database.model.gson.GsonListing

@Entity(tableName = "listings")
class Listing: Home() {

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
    override var booliId: Int = 0
    override var listPrice: Int = 0
    override var published: String = ""
    override var objectType: String  = ""
    override var rooms: Int = 0
    override var livingArea: Int = 0
    override var additionalArea: Int = 0
    override var rent: Int = 0
    override var floor: Int = 0
    override var constructionYear: Int = 0
    override var url: String = ""
    override var plotArea: Int = 0
    override var streetAddress: String = ""
    override var countyName: String = ""
    override var municipalityName: String = ""
    override var longitude: Double = 0.toDouble()
    override var latitude: Double = 0.toDouble()
    override var sourceType: String = ""
    override var sourceName: String = ""
    override var sourceUrl: String = ""

    override var soldDate: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    override var soldPrice: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    override var apartmentNumber: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    override var soldPriceSource: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override fun getSnippet(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTitle(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPosition(): LatLng {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

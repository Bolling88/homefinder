package app.bolling.chucknorris.util

import app.bolling.chucknorris.database.AppDatabase
import com.xevenition.HomeApp
import com.xevenition.R
import com.xevenition.database.model.db.Listing
import com.xevenition.database.model.gson.GsonListingInfo
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.inject.Inject
import kotlin.experimental.and


/**
 * Repository handling the work with products and comments.
 */
const val TAG = "ListingsRepository"
const val CALLER_ID = "hitta_bostad"

class ListingsRepository @Inject
constructor(private val mDatabase: AppDatabase, private val retrofit: Retrofit, private val resources: ResourceUtil) {

    val listings: Flowable<List<Listing>>
        get() = mDatabase.listingsDao().getAllListings()

    init {
        HomeApp.component.inject(this)
    }

    /**
     * This will fetch the listings info object, get all the listings, and then convert them to
     * internal listings objects, and return them
     */
    fun loadNewListings(): Flowable<Listing> {
        val service = retrofit.create(BooliApi::class.java)

        val unique = getUniqueNumber()
        val timestamp = (System.currentTimeMillis() / 1000L).toString() + ""
        var hash = getSha1(CALLER_ID + timestamp + resources.getString(R.string.booli_key) + unique)
        
        return service.getTasks(hash = hash, callerId = CALLER_ID, latLon = "", limit = 500, offset = 0, timestamp = timestamp, unique = unique, widthHeight = "200")
                .flatMap { Flowable.fromIterable(it.listings) }
                .map { Listing.create(it) }
                .subscribeOn(Schedulers.io())
    }

    //    fun deleteJoke(viewedJoke: ListingsRepository) {
//        Observable.just(viewedJoke)
//                .subscribeOn(Schedulers.io())
//                .doOnNext { joke -> mDatabase.listingsDao().deleteJoke(joke) }
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe { jokeEntity -> Log.d(TAG, "Joke deleted") }
//    }
//
//    fun deleteAllNonfavouriteJokes() {
//        Completable.fromAction { mDatabase.listingsDao().deleteAllNonfavouriteJokes() }
//                .subscribeOn(Schedulers.io())
//                .subscribe()
//    }
//    fun saveJoke(listing: Listing) {
//        Observable.just(listing)
//                .observeOn(Schedulers.io())
//                .doOnNext { mDatabase.listingsDao().insert(it) }
//                .subscribe()
//    }

    //    @GET("/listings/?center={latitude},{longitude}&dim={window_width},{window_height}&limit={limit}offset=0&callerId={caller_id}&time={timestamp}&unique={unique}&hash={hash}")
    private interface BooliApi {
        @GET("/listings/&dim={window_width},{window_height}&limit={limit}offset=0&callerId={caller_id}&time={timestamp}&unique={unique}&hash={hash}")
        fun getTasks(@Path("center") latLon: String,
                     @Path("dim") widthHeight: String,
                     @Path("limit") limit: Int,
                     @Path("offset") offset: Int,
                     @Path("callerId") callerId: String,
                     @Path("time") timestamp: String,
                     @Path("unique") unique: String,
                     @Path("hash") hash: String):
                Flowable<GsonListingInfo>
    }

    private fun getUniqueNumber(): String {
        val random = Random()
        val number = random.nextInt(8999) + 1000
        return "263748263748$number"
    }

    private val hexArray = "0123456789ABCDEF".toCharArray()
    private fun convertToHex(data: ByteArray): String {
        val hexChars = CharArray(data.size * 2)
        for (j in data.indices) {
            val v = (data[j] and 0xFF.toByte()).toInt()

            hexChars[j * 2] = hexArray[v ushr 4]
            hexChars[j * 2 + 1] = hexArray[v and 0x0F]
        }
        return String(hexChars)
    }

    @Throws(NoSuchAlgorithmException::class, UnsupportedEncodingException::class)
    fun getSha1(text: String): String {
        val md = MessageDigest.getInstance("SHA-1")
        md.update(text.toByteArray(charset("iso-8859-1")), 0, text.length)
        val sha1hash = md.digest()
        return convertToHex(sha1hash)
    }
}

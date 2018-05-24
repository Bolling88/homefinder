package app.bolling.chucknorris.util

import app.bolling.chucknorris.database.AppDatabase
import com.xevenition.HomeApp
import com.xevenition.R
import com.xevenition.database.model.db.Listing
import com.xevenition.database.model.gson.GsonListingInfo
import com.xevenition.util.SaveUtil
import com.xevenition.util.SaveUtil.Companion.PARAM_ACTIVE_DAYS
import com.xevenition.util.SaveUtil.Companion.PARAM_DISTANCE_SEA
import com.xevenition.util.SaveUtil.Companion.PARAM_MAX_AREA
import com.xevenition.util.SaveUtil.Companion.PARAM_MAX_CONSTRUCTION
import com.xevenition.util.SaveUtil.Companion.PARAM_MAX_PLOT
import com.xevenition.util.SaveUtil.Companion.PARAM_MAX_PRICE
import com.xevenition.util.SaveUtil.Companion.PARAM_MAX_SQUAREPRICE
import com.xevenition.util.SaveUtil.Companion.PARAM_MIN_AREA
import com.xevenition.util.SaveUtil.Companion.PARAM_MIN_CONSTRUCTION
import com.xevenition.util.SaveUtil.Companion.PARAM_MIN_PLOT
import com.xevenition.util.SaveUtil.Companion.PARAM_MIN_PRICE
import com.xevenition.util.SaveUtil.Companion.PARAM_MIN_SQUAREPRICE
import com.xevenition.util.SaveUtil.Companion.PARAM_RENT
import com.xevenition.util.SaveUtil.Companion.PARAM_SHOW_APARTMENT
import com.xevenition.util.SaveUtil.Companion.PARAM_SHOW_FARM
import com.xevenition.util.SaveUtil.Companion.PARAM_SHOW_HOLLIDAY
import com.xevenition.util.SaveUtil.Companion.PARAM_SHOW_PLOT
import com.xevenition.util.SaveUtil.Companion.PARAM_SHOW_ROOM_1
import com.xevenition.util.SaveUtil.Companion.PARAM_SHOW_ROOM_2
import com.xevenition.util.SaveUtil.Companion.PARAM_SHOW_ROOM_3
import com.xevenition.util.SaveUtil.Companion.PARAM_SHOW_ROOM_4
import com.xevenition.util.SaveUtil.Companion.PARAM_SHOW_ROOM_5
import com.xevenition.util.SaveUtil.Companion.PARAM_SHOW_ROW
import com.xevenition.util.SaveUtil.Companion.PARAM_SHOW_SEMI
import com.xevenition.util.SaveUtil.Companion.PARAM_SHOW_TERRACED
import com.xevenition.util.SaveUtil.Companion.PARAM_SHOW_VILLA
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
const val TAG = "HomeRepository"
const val CALLER_ID = "hitta_bostad"

class HomeRepository @Inject constructor(private val mDatabase: AppDatabase, private val retrofit: Retrofit, private val resources: ResourceUtil, private val saveUtil: SaveUtil) {

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

    //    fun deleteJoke(viewedJoke: HomeRepository) {
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

    fun setUpFilter() {
        val minPrice = saveUtil.getInt(PARAM_MIN_PRICE)
        val maxPrice = saveUtil.getInt(PARAM_MAX_PRICE)
        val minArea = saveUtil.getInt(PARAM_MIN_AREA)
        val maxArea = saveUtil.getInt(PARAM_MAX_AREA)
        val minSqr = saveUtil.getInt(PARAM_MIN_SQUAREPRICE)
        val maxSqr = saveUtil.getInt(PARAM_MAX_SQUAREPRICE)
        val minPlot = saveUtil.getInt(PARAM_MIN_PLOT)
        val maxPlot = saveUtil.getInt(PARAM_MAX_PLOT)
        val minConstruct = saveUtil.getInt(PARAM_MIN_CONSTRUCTION)
        val maxConstruct = saveUtil.getInt(PARAM_MAX_CONSTRUCTION)
        val rent = saveUtil.getInt(PARAM_RENT)
        val distanceSea = saveUtil.getInt(PARAM_DISTANCE_SEA)
        val daysActive = saveUtil.getInt(PARAM_ACTIVE_DAYS)

        val showApartments = saveUtil.getBoolean(PARAM_SHOW_APARTMENT, true)
        val showVillas = saveUtil.getBoolean(PARAM_SHOW_VILLA, true)
        val showDetatched = saveUtil.getBoolean(PARAM_SHOW_TERRACED, true)
        val showCottage = saveUtil.getBoolean(PARAM_SHOW_HOLLIDAY, true)
        val showPlot = saveUtil.getBoolean(PARAM_SHOW_PLOT, true)
        val showFarms = saveUtil.getBoolean(PARAM_SHOW_FARM, true)
        val showSemi = saveUtil.getBoolean(PARAM_SHOW_SEMI, true)
        val showRow = saveUtil.getBoolean(PARAM_SHOW_ROW, true)

        val rooms1 = saveUtil.getBoolean(PARAM_SHOW_ROOM_1, true)
        val rooms2 = saveUtil.getBoolean(PARAM_SHOW_ROOM_2, true)
        val rooms3 = saveUtil.getBoolean(PARAM_SHOW_ROOM_3, true)
        val rooms4 = saveUtil.getBoolean(PARAM_SHOW_ROOM_4, true)
        val rooms5 = saveUtil.getBoolean(PARAM_SHOW_ROOM_5, true)
    }


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

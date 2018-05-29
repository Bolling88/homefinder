package com.xevenition.util

import android.app.Application
import android.app.Service
import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import app.bolling.chucknorris.util.ResourceUtil
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator
import com.google.maps.android.ui.SquareTextView
import com.xevenition.HomeApp
import com.xevenition.R
import com.xevenition.database.model.Home

/**
 * Created by bolling on 2017-10-12.
 */

class MyClusterRenderer constructor(val app: Application, val resources: ResourceUtil, saveUtil: SaveUtil, map: GoogleMap, clusterManager: ClusterManager<Home>) : DefaultClusterRenderer<Home>(app, map, clusterManager) {

    private val mClusterManager: ClusterManager<Home>
    private val markerInfo: Int
    private val mapMarker: LinearLayout
    private val textType: TextView
    private val textInfo: TextView
    private val redMarker: Drawable?
    private val mDensity: Float
    private val other: String
    private val blueMarker: Drawable?
    private val iconGenerator: IconGenerator
    private val clusterIconGenerator: IconGenerator
    private val apartment: String
    private val villa: String
    private val cottage: String
    private val detached: String
    private val semidetached: String
    private val terraced: String
    private val plot: String
    private val farm: String

    private var clusterIcons: SparseArray<BitmapDescriptor>? = null
    private var singleIcons: SparseArray<BitmapDescriptor>? = null

    private var mColoredCircleBackground: ShapeDrawable? = null
    private val greenMarker: Drawable?
    private val brownMarker: Drawable?
    private val purpleMarker: Drawable?
    private val yellowMarker: Drawable?
    private val cyanMarker: Drawable?
    private val blackMarker: Drawable?

    init {
        HomeApp.component.inject(this)
        iconGenerator = IconGenerator(app)

        this.mDensity = app.resources.displayMetrics.density

        clusterIconGenerator = IconGenerator(app)
        clusterIconGenerator.setContentView(makeSquareTextView(app))
        this.clusterIconGenerator.setBackground(this.makeClusterBackground())

        var li = app.getSystemService(Service.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mapMarker = li.inflate(R.layout.map_marker, null) as LinearLayout
        textInfo = mapMarker.findViewById(R.id.text_info)
        textType = mapMarker.findViewById(R.id.text_type)
        iconGenerator.setContentView(mapMarker)
        iconGenerator.setBackground(null)

        redMarker = resources.getDrawable( R.drawable.marker_red)
        blueMarker = resources.getDrawable( R.drawable.marker_blue)
        greenMarker = resources.getDrawable( R.drawable.marker_green)
        brownMarker = resources.getDrawable( R.drawable.marker_brown)
        purpleMarker = resources.getDrawable( R.drawable.marker_purple)
        yellowMarker = resources.getDrawable( R.drawable.marker_yellow)
        cyanMarker = resources.getDrawable( R.drawable.marker_cyan)
        blackMarker = resources.getDrawable( R.drawable.marker_black)

        mClusterManager = clusterManager

        apartment = resources.getString(R.string.hitta_apartment)
        villa = resources.getString(R.string.hitta_villa)
        cottage = resources.getString(R.string.hitta_cottage)
        detached = resources.getString(R.string.hitta_detached)
        semidetached = resources.getString(R.string.hitta_semidetached)
        terraced = resources.getString(R.string.hitta_terraced)
        plot = resources.getString(R.string.hitta_plot)
        farm = resources.getString(R.string.hitta_farm)
        other = resources.getString(R.string.hitta_other)

        markerInfo = saveUtil.getInt(SaveUtil.KEY_MAP_MARKER_INFO)
    }

     override fun onBeforeClusterItemRendered(home: Home, markerOptions: MarkerOptions) {
        val booliId = home.booliId.hashCode()
        var descriptor: BitmapDescriptor? = this.singleIcons?.get(booliId)
        if (descriptor == null) {
            val objectType = home.objectType
            if (objectType.equals(Home.LAGENEHET, ignoreCase = true)) {
                mapMarker.background = blueMarker
                textType.text = apartment
            } else if (objectType.equals(Home.TOMT, ignoreCase = true)) {
                mapMarker.background = greenMarker
                textType.text = plot
            } else if (objectType.equals(Home.VILLA, ignoreCase = true)) {
                mapMarker.background = redMarker
                textType.text = villa
            } else if (objectType.equals(Home.RADHUS, ignoreCase = true)) {
                mapMarker.background = redMarker
                textType.text = terraced
            } else if (objectType.equals(Home.FRITIDSHUS, ignoreCase = true)) {
                mapMarker.background = brownMarker
                textType.text = cottage
            } else if (objectType.equals(Home.KEDJEHUS, ignoreCase = true)) {
                mapMarker.background = purpleMarker
                textType.text = detached
            } else if (objectType.equals(Home.PARHUS, ignoreCase = true)) {
                mapMarker.background = yellowMarker
                textType.text = semidetached
            } else if (objectType.equals(Home.GARD, ignoreCase = true)) {
                mapMarker.background = cyanMarker
                textType.text = farm
            } else {
                mapMarker.background = blackMarker
                textType.text = other
            }

            var info = ""
            var price: Int
            val year: Int
            var priceInfo = ""
            when (markerInfo) {
                1 -> {
                    price = home.listPrice
                    if (price == 0)
                        priceInfo = resources.getString(R.string.hitta_unspecified)
                    else
                        priceInfo = "$price kr"
                    info = priceInfo
                }
                2 -> info = "${home.getSquareMeterPrice()} kr/m²"
                3 -> info = "${home.rent}  kr"
                4 -> info = "${home.rooms} ${resources.getString(R.string.hitta_room)}"
                5 -> info = "${home.livingArea} m²"
                6 -> {
                    year = home.constructionYear
                    if (year == 0)
                        priceInfo = resources.getString(R.string.hitta_unspecified)
                    else
                        priceInfo = year.toString()
                    info = priceInfo
                }
                7 -> info = home.sourceName
                8 -> info = resources.getString(R.string.hitta_floor) + " " + home.floor
                else -> {
                    if (home.listPrice == 0)
                        priceInfo = resources.getString(R.string.hitta_unspecified)
                    else
                        priceInfo = "${home.listPrice} kr"
                    info = priceInfo
                }
            }
            textInfo.text = info


            val icon = iconGenerator.makeIcon()
            descriptor = BitmapDescriptorFactory.fromBitmap(icon)
            singleIcons?.put(booliId, descriptor)
        }
        markerOptions.icon(descriptor).snippet(home.booliId.toString()).anchor(0f, 1f)
    }

    override fun onBeforeClusterRendered(cluster: Cluster<Home>, markerOptions: MarkerOptions) {
        val bucket = cluster.size
        var descriptor: BitmapDescriptor? = this.clusterIcons?.get(bucket)
        if (descriptor == null) {
            this.mColoredCircleBackground!!.paint.color = this.getColor(bucket)
            descriptor = BitmapDescriptorFactory.fromBitmap(this.clusterIconGenerator.makeIcon(this.getClusterText(bucket)))
            this.clusterIcons?.put(bucket, descriptor)
        }

        markerOptions.icon(descriptor)
    }

     override fun shouldRenderAsCluster(cluster: Cluster<Home>?): Boolean {
        return cluster != null && cluster.size > 0
    }

    private fun makeClusterBackground(): LayerDrawable {
        this.mColoredCircleBackground = ShapeDrawable(OvalShape())
        val outline = ShapeDrawable(OvalShape())
        outline.paint.color = resources.getColor(R.color.hitta_blue_transparent)
        val background = LayerDrawable(arrayOf<Drawable>(outline, this.mColoredCircleBackground!!))
        val strokeWidth = (this.mDensity * 3.0f).toInt()
        background.setLayerInset(1, strokeWidth, strokeWidth, strokeWidth, strokeWidth)
        return background
    }

    private fun makeSquareTextView(context: Context): SquareTextView {
        val squareTextView = SquareTextView(context)
        val layoutParams = ViewGroup.LayoutParams(-2, -2)
        squareTextView.layoutParams = layoutParams
        squareTextView.id = R.id.amu_text
        val twelveDpi = (12.0f * this.mDensity).toInt()
        squareTextView.setPadding(twelveDpi, twelveDpi, twelveDpi, twelveDpi)
        return squareTextView
    }

}

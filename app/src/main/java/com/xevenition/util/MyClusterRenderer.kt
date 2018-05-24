package com.xevenition.util

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.support.v4.content.ContextCompat
import android.util.SparseArray
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import com.bolling.production.hittabostad.HittaApplication
import com.bolling.production.hittabostad.R
import com.bolling.production.hittabostad.model.Bostad
import com.bolling.production.hittabostad.utils.SaveFile
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator
import com.google.maps.android.ui.SquareTextView
import com.xevenition.database.model.Home

import javax.inject.Inject

/**
 * Created by bolling on 2017-10-12.
 */

class MyClusterRenderer(context: Activity, map: GoogleMap, clusterManager: ClusterManager<Bostad>) : DefaultClusterRenderer<Home>(context, map, clusterManager) {

    private val mClusterManager: ClusterManager<Home>
    private val markerInfo: Int
    private val mapMarker: LinearLayout
    private val textType: TextView
    private val textInfo: TextView
    private val redMarker: Drawable?
    private val mDensity: Float
    private val other: String
    private val blueMarker: Drawable?
    private val context: Context
    private val iconGenerator: IconGenerator
    private val clusterIconGenerator: IconGenerator
    private val LAGENEHET: String
    private val TOMT: String
    private val VILLA: String
    private val RADHUS: String
    private val FRITIDSHUS: String
    private val KEDJEHUS: String
    private val PARHUS: String
    private val GARD: String

    private val apartment: String
    private val villa: String
    private val cottage: String
    private val detached: String
    private val semidetached: String
    private val terraced: String
    private val plot: String
    private val farm: String

    private val clusterIcons = SparseArray()
    private val singleIcons = SparseArray()


    @Inject
    internal var saveFile: SaveFile? = null
    private var mColoredCircleBackground: ShapeDrawable? = null
    private val greenMarker: Drawable?
    private val brownMarker: Drawable?
    private val purpleMarker: Drawable?
    private val yellowMarker: Drawable?
    private val cyanMarker: Drawable?
    private val blackMarker: Drawable?

    init {
        HittaApplication.getComponent().inject(this)
        iconGenerator = IconGenerator(context)
        this.context = context

        this.mDensity = context.resources.displayMetrics.density

        clusterIconGenerator = IconGenerator(context)
        clusterIconGenerator.setContentView(makeSquareTextView(context))
        this.clusterIconGenerator.setBackground(this.makeClusterBackground())

        mapMarker = context.layoutInflater.inflate(R.layout.map_marker, null) as LinearLayout
        textInfo = mapMarker.findViewById(R.id.text_info)
        textType = mapMarker.findViewById(R.id.text_type)
        iconGenerator.setContentView(mapMarker)
        iconGenerator.setBackground(null)

        redMarker = ContextCompat.getDrawable(context, R.drawable.marker_red)
        blueMarker = ContextCompat.getDrawable(context, R.drawable.marker_blue)
        greenMarker = ContextCompat.getDrawable(context, R.drawable.marker_green)
        brownMarker = ContextCompat.getDrawable(context, R.drawable.marker_brown)
        purpleMarker = ContextCompat.getDrawable(context, R.drawable.marker_purple)
        yellowMarker = ContextCompat.getDrawable(context, R.drawable.marker_yellow)
        cyanMarker = ContextCompat.getDrawable(context, R.drawable.marker_cyan)
        blackMarker = ContextCompat.getDrawable(context, R.drawable.marker_black)

        mClusterManager = clusterManager
        LAGENEHET = context.getString(R.string.lagenhet)
        VILLA = context.getString(R.string.villa)
        FRITIDSHUS = context.getString(R.string.fritidshus)
        KEDJEHUS = context.getString(R.string.kedjehus)
        PARHUS = context.getString(R.string.parhus)
        RADHUS = context.getString(R.string.radhus)
        TOMT = context.getString(R.string.tomtmark)
        GARD = context.getString(R.string.gard)

        apartment = context.getString(R.string.hitta_apartment)
        villa = context.getString(R.string.hitta_villa)
        cottage = context.getString(R.string.hitta_cottage)
        detached = context.getString(R.string.hitta_detached)
        semidetached = context.getString(R.string.hitta_semidetached)
        terraced = context.getString(R.string.hitta_terraced)
        plot = context.getString(R.string.hitta_plot)
        farm = context.getString(R.string.hitta_farm)
        other = context.getString(R.string.hitta_other)

        markerInfo = saveFile!!.getInt(SaveFile.KEY_MAP_MARKER_INFO)
    }

    protected fun onBeforeClusterItemRendered(bostad: Bostad, markerOptions: MarkerOptions) {
        val booliId = bostad.getBooliId().hashCode()
        var descriptor: BitmapDescriptor? = this.singleIcons.get(booliId)
        if (descriptor == null) {
            val objectType = bostad.getObjectType()
            if (objectType.equals(LAGENEHET, ignoreCase = true)) {
                mapMarker.background = blueMarker
                textType.text = apartment
            } else if (objectType.equals(TOMT, ignoreCase = true)) {
                mapMarker.background = greenMarker
                textType.text = plot
            } else if (objectType.equals(VILLA, ignoreCase = true)) {
                mapMarker.background = redMarker
                textType.text = villa
            } else if (objectType.equals(RADHUS, ignoreCase = true)) {
                mapMarker.background = redMarker
                textType.text = terraced
            } else if (objectType.equals(FRITIDSHUS, ignoreCase = true)) {
                mapMarker.background = brownMarker
                textType.text = cottage
            } else if (objectType.equals(KEDJEHUS, ignoreCase = true)) {
                mapMarker.background = purpleMarker
                textType.text = detached
            } else if (objectType.equals(PARHUS, ignoreCase = true)) {
                mapMarker.background = yellowMarker
                textType.text = semidetached
            } else if (objectType.equals(GARD, ignoreCase = true)) {
                mapMarker.background = cyanMarker
                textType.text = farm
            } else {
                mapMarker.background = blackMarker
                textType.text = other
            }

            var info = ""
            var price: String
            val year: Int
            when (markerInfo) {
                1 -> {
                    price = bostad.getListPrice()
                    if (price.equals("0", ignoreCase = true))
                        price = context.resources.getString(R.string.hitta_unspecified)
                    else
                        price = "$price kr"
                    info = price
                }
                2 -> info = bostad.getSquareMeterPrice() + " kr/m²"
                3 -> info = bostad.getRent() + " kr"
                4 -> info = bostad.getRooms() + " " + context.resources.getString(R.string.hitta_room)
                5 -> info = bostad.getLivingArea() + " m²"
                6 -> {
                    year = bostad.getConstructionYear()
                    if (year == 0)
                        price = context.resources.getString(R.string.hitta_unspecified)
                    else
                        price = year.toString() + ""
                    info = price
                }
                7 -> info = bostad.getBrookerName()
                8 -> info = "Plan " + bostad.getFloor()
                else -> {
                    price = bostad.getListPrice()
                    if (price.equals("0", ignoreCase = true))
                        price = context.resources.getString(R.string.hitta_unspecified)
                    else
                        price = "$price kr"
                    info = price
                }
            }
            textInfo.text = info


            val icon = iconGenerator.makeIcon()
            descriptor = BitmapDescriptorFactory.fromBitmap(icon)
            singleIcons.put(booliId, descriptor)
        }
        markerOptions.icon(descriptor).snippet(String.valueOf(bostad.getBooliId())).anchor(0f, 1f)
    }

    protected fun onBeforeClusterRendered(cluster: Cluster<Bostad>, markerOptions: MarkerOptions) {
        val bucket = cluster.getSize()
        var descriptor: BitmapDescriptor? = this.clusterIcons.get(bucket)
        if (descriptor == null) {
            this.mColoredCircleBackground!!.paint.color = this.getColor(bucket)
            descriptor = BitmapDescriptorFactory.fromBitmap(this.clusterIconGenerator.makeIcon(this.getClusterText(bucket)))
            this.clusterIcons.put(bucket, descriptor)
        }

        markerOptions.icon(descriptor)
    }

    protected override fun shouldRenderAsCluster(cluster: Cluster<*>): Boolean {
        return cluster.size > 1
    }

    private fun makeClusterBackground(): LayerDrawable {
        this.mColoredCircleBackground = ShapeDrawable(OvalShape())
        val outline = ShapeDrawable(OvalShape())
        outline.paint.color = ContextCompat.getColor(context, R.color.hitta_blue_transparent)
        val background = LayerDrawable(arrayOf<Drawable>(outline, this.mColoredCircleBackground))
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

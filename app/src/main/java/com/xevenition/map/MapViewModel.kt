/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.bolling.chucknorris.ui.fragment.joke

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.view.View
import app.bolling.chucknorris.util.HomeRepository
import app.bolling.chucknorris.util.ResourceUtil
import app.bolling.chucknorris.util.SingleLiveEvent
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.xevenition.R
import com.xevenition.R.id.map
import com.xevenition.database.model.Home
import com.xevenition.database.model.db.Listing
import com.xevenition.util.MyClusterRenderer
import com.xevenition.util.SaveUtil
import com.xevenition.util.TypedValueUtil
import io.reactivex.android.schedulers.AndroidSchedulers


class MapViewModel(private val resourceUtil: ResourceUtil,
                   private val repository: HomeRepository,
                   private val saveUtil: SaveUtil,
                   private val typedValueUtil: TypedValueUtil,
                   private val app: Application) :
        AndroidViewModel(app), GoogleMap.OnCameraIdleListener, GoogleMap.OnCameraMoveListener, ClusterManager.OnClusterClickListener<Home>, ClusterManager.OnClusterInfoWindowClickListener<Home>, ClusterManager.OnClusterItemClickListener<Home>, ClusterManager.OnClusterItemInfoWindowClickListener<Home> {

    private lateinit var googleMap: GoogleMap
    private lateinit var clusterManager: ClusterManager<Home>
    private var markerClicked: Boolean = false

    //single live events
    val observableToast = SingleLiveEvent<String>()
    val loadingVisibilityEvent = SingleLiveEvent<Int>()
    val buttonVisibilityEvent = SingleLiveEvent<Int>()
    val jokeChangedEvent = MutableLiveData<Listing>()

    private var viewedJoke: Listing? = null

    fun onNextJokeClicked() {
        loadingVisibilityEvent.value = View.VISIBLE
        buttonVisibilityEvent.value = View.GONE
        repository.loadNewListings()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    viewedJoke = it
                    loadingVisibilityEvent.value = View.GONE
                    buttonVisibilityEvent.value = View.VISIBLE
                    jokeChangedEvent.postValue(it)
                }
    }

    fun onMapReady(map: GoogleMap, clusterManager: ClusterManager<Home>) {
        this.googleMap = map
        this.clusterManager = clusterManager
        // Point the googleMap's listeners at the listeners implemented by the cluster
        // manager.
        this.googleMap.setOnMarkerClickListener(clusterManager)
        this.googleMap.setOnCameraMoveListener(this)
        this.googleMap.setOnInfoWindowClickListener(clusterManager)
        this.googleMap.setInfoWindowAdapter(clusterManager.markerManager)
        map.setOnCameraIdleListener(this)
        clusterManager.setOnClusterClickListener(this)
        clusterManager.setOnClusterInfoWindowClickListener(this)
        clusterManager.setOnClusterItemClickListener(this)
        clusterManager.setOnClusterItemInfoWindowClickListener(this)
        clusterManager.renderer = MyClusterRenderer(app, resourceUtil, saveUtil, map, clusterManager)
        this.googleMap.uiSettings.isMyLocationButtonEnabled = false
        this.googleMap.uiSettings.isZoomControlsEnabled = false
        this.googleMap.uiSettings.isMapToolbarEnabled = false
        this.googleMap.uiSettings.setAllGesturesEnabled(true)
        this.googleMap.isBuildingsEnabled = true
        this.googleMap.isIndoorEnabled = true

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(63.333962, 16.472882), 5.0f))

        val mode = saveUtil.getInt(SaveUtil.KEY_MAP_MODE)
        when (mode) {
            1 -> this.googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            2 -> this.googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            3 -> this.googleMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            4 -> this.googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            else -> this.googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        }
    }

    override fun onCameraMove() {

    }

    fun setMapType(mapType: Int) {
        googleMap.mapType = mapType
    }

    override fun onClusterItemClick(item: Home): Boolean {
        moveTo(item.latitude, item.longitude, googleMap.cameraPosition.zoom)
        //getView().showSingleMarkerFragment(item)
        return true
    }

    override fun onClusterInfoWindowClick(cluster: Cluster<Home>) {}

    override fun onClusterClick(cluster: Cluster<Home>): Boolean {
        return if (googleMap.cameraPosition.zoom < 18) {
            markerClicked = false
            val builder = LatLngBounds.builder()
            for (item in cluster.items) {
                builder.include(item.position)
            }
            val bounds = builder.build()
            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, typedValueUtil.dipToPixels(100F), 300, 0))
            true
        } else {
            //else show the list with all the clusters
            false
        }
    }

    override fun onCameraIdle() {
        val visibleRegion = googleMap.projection.visibleRegion
        val target = googleMap.cameraPosition.target
    }

    fun moveTo(latitude: Double, longitude: Double, zoom: Float) {
        if (longitude != 0.0 && latitude != 0.0 && map != null) {
            val cameraUpdate: CameraUpdate = if (zoom == 0f)
                CameraUpdateFactory.newLatLngZoom(LatLng(latitude, longitude), googleMap.cameraPosition.zoom)
            else
                CameraUpdateFactory.newLatLngZoom(LatLng(latitude, longitude), zoom)
            googleMap.animateCamera(cameraUpdate)
        } else {
            observableToast.value = resourceUtil.getString(R.string.hitta_no_position)
        }
    }

    override fun onClusterItemInfoWindowClick(p0: Home?) {
        //leave empty
    }

    private fun createCluster() {
        googleMap.clear()
        clusterManager!!.clearItems()
        //clusterManager!!.addItems(clusterArray)
        clusterManager!!.cluster()
    }


//    fun onHomesFetched(array: HashMap<String, Home>) {
//        fetchingHomes = false
//        WORKING_DATA = array
//        map.clear()
//        clusterManager!!.clearItems()
//        clusterManager!!.addItems(array.values)
//        clusterManager!!.cluster()
//    }
}

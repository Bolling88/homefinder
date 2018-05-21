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
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.bolling.chucknorris.util.ListingsRepository
import app.bolling.chucknorris.util.ResourceUtil
import com.xevenition.HomeApp
import com.xevenition.R
import com.xevenition.databinding.FragmentMapBinding
import javax.inject.Inject

class MapFragment : Fragment() {
    private lateinit var mBinding: FragmentMapBinding

    @Inject
    lateinit var resources: ResourceUtil
    @Inject
    lateinit var repository: ListingsRepository
    @Inject
    lateinit var application: Application

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HomeApp.component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate this data binding layout
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)

        // Create and set the adapter for the RecyclerView.
        return mBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.title = resources.getString(R.string.app_name)

        val viewModel = ViewModelProviders.of(this, MapViewModelFactory(resources, repository, application)).get(MapViewModel::class.java)

        //now we can hook up the observables to the view viewModel
        setUpObservables(viewModel)
    }

    private fun setUpObservables(model: MapViewModel) {
        //LiveData observable
        model.jokeChangedEvent.observe(this, Observer {})
    }
}

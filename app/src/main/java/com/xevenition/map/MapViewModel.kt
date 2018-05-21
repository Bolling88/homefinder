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
import app.bolling.chucknorris.util.ListingsRepository
import app.bolling.chucknorris.util.ResourceUtil
import app.bolling.chucknorris.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers



class MapViewModel(private val resourceUtil: ResourceUtil, private val repository: ListingsRepository, application: Application) : AndroidViewModel(application) {



    //single live events
    val observableToast = SingleLiveEvent<String>()
    val loadingVisibilityEvent = SingleLiveEvent<Int>()
    val buttonVisibilityEvent = SingleLiveEvent<Int>()
    val jokeChangedEvent = MutableLiveData<HomeEntity>()

    private var viewedJoke: HomeEntity? = null

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
}

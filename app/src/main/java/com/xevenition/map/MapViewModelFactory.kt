package app.bolling.chucknorris.ui.fragment.joke

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import app.bolling.chucknorris.util.ListingsRepository
import app.bolling.chucknorris.util.ResourceUtil

class MapViewModelFactory(private val resourceUtil: ResourceUtil, private val repository: ListingsRepository, private val application: Application) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MapViewModel(resourceUtil, repository, application) as T
    }
}
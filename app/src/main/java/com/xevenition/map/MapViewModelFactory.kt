package app.bolling.chucknorris.ui.fragment.joke

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import app.bolling.chucknorris.util.DataRepository
import app.bolling.chucknorris.util.ResourceUtil

class MapViewModelFactory(private val resourceUtil: ResourceUtil, private val repository: DataRepository, private val application: Application) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MapViewModel(resourceUtil, repository, application) as T
    }
}
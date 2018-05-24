package app.bolling.chucknorris.ui.fragment.joke

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import app.bolling.chucknorris.util.HomeRepository
import app.bolling.chucknorris.util.ResourceUtil
import com.xevenition.util.SaveUtil
import com.xevenition.util.TypedValueUtil

class MapViewModelFactory(private val resourceUtil: ResourceUtil, private val repository: HomeRepository, private val saveFile: SaveUtil, private val typedValueUtil: TypedValueUtil, private val application: Application) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MapViewModel(resourceUtil, repository, saveFile, typedValueUtil, application) as T
    }
}
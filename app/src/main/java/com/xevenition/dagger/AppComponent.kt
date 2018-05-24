package app.bolling.chucknorris.dagger

import app.bolling.chucknorris.ui.fragment.joke.MapFragment
import app.bolling.chucknorris.util.HomeRepository
import com.xevenition.util.MyClusterRenderer
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, RoomModule::class))
interface AppComponent {
    fun inject(repository: HomeRepository)
    fun inject(fragment: MapFragment)
    fun inject(renderer: MyClusterRenderer)
}
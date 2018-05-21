package app.bolling.chucknorris.dagger

import android.app.Application
import android.arch.persistence.room.Room
import app.bolling.chucknorris.database.AppDatabase
import app.bolling.chucknorris.database.dao.ListingsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    internal fun providesRoomDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "home-db").fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    internal fun providesProductDao(demoDatabase: AppDatabase): ListingsDao {
        return demoDatabase.listingsDao()
    }
}
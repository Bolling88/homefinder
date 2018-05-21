package com.xevenition

import android.app.Application
import app.bolling.chucknorris.dagger.AppComponent
import app.bolling.chucknorris.dagger.ApplicationModule
import app.bolling.chucknorris.dagger.DaggerAppComponent
import app.bolling.chucknorris.dagger.RoomModule
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
import io.fabric.sdk.android.Fabric

class HomeApp: Application(){

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics(), Answers())

        component = DaggerAppComponent.builder()
                .applicationModule(ApplicationModule(this)).roomModule(RoomModule())
                .build()
    }

    companion object {
        lateinit var component: AppComponent
    }
}
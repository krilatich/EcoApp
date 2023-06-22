package com.startup.ecoapp

import android.app.Application
import com.startup.ecoapp.di.appModule
import com.startup.ecoapp.di.blogModule
import com.startup.ecoapp.di.blogsModule
import com.startup.ecoapp.di.categoriesModule
import com.startup.ecoapp.di.commentModule
import com.startup.ecoapp.di.homeModule
import com.startup.ecoapp.di.mapModule
import com.startup.ecoapp.di.networkModule
import com.startup.ecoapp.di.postModule
import com.startup.ecoapp.di.profileModule
import com.startup.ecoapp.di.reactionsModule
import com.startup.ecoapp.di.signInModule
import com.startup.ecoapp.di.signUpModule
import com.startup.ecoapp.di.threadModule
import com.startup.ecoapp.di.tokenModule
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

	companion object {

		const val BACKEND = "BACKEND"
		private const val BACKEND_ENDPOINT = "http://d.wolf.16.fvds.ru/"

		internal lateinit var INSTANCE: App
			private set
	}

	override fun onCreate() {
		super.onCreate()

		INSTANCE = this

		MapKitFactory.setApiKey("32be1030-59a5-4540-965b-636a057fbd2b")

		startKoin {
			androidLogger(Level.ERROR)
			androidContext(this@App)
			properties(mapOf(BACKEND to BACKEND_ENDPOINT))
			androidFileProperties()

			modules(appModule)
			modules(networkModule)
            modules(tokenModule)

            modules(reactionsModule)
            modules(postModule)
            modules(commentModule)
            modules(threadModule)

            modules(signUpModule)
            modules(signInModule)
            modules(homeModule)
            modules(blogsModule)
            modules(blogModule)
            modules(profileModule)
            modules(categoriesModule)
			modules(mapModule)
		}
	}
}

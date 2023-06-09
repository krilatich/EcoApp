package com.startup.ecoapp.core.network.token.data.storage

import android.content.Context
import android.content.SharedPreferences
import com.startup.ecoapp.core.network.token.domain.model.AuthTokenPair

class SharedPrefsDataStorage(context: Context) : TokenDataStorage {
	companion object {

		const val SHARED_PREFERENCES_FILENAME = "myPrefs"
		const val ACCESS_TOKEN = "access_token"
		const val REFRESH_TOKEN = "refresh_token"
	}

	private val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILENAME, Context.MODE_PRIVATE)

	override fun save(authTokenPair: AuthTokenPair) {
		val e: SharedPreferences.Editor = sharedPreferences.edit()

		e.putString(ACCESS_TOKEN, authTokenPair.accessToken)
		e.putString(REFRESH_TOKEN, authTokenPair.refreshToken)
		e.apply()
	}

	override fun load() =
		AuthTokenPair(
			accessToken = sharedPreferences.getString(ACCESS_TOKEN, "") ?: "",
			refreshToken = sharedPreferences.getString(REFRESH_TOKEN, "") ?: ""
		)
}
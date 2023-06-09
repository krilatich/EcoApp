package com.startup.ecoapp.data.storage

import android.content.Context
import android.content.SharedPreferences

class FirstStartDataStorageImpl(context: Context) : FirstStartDataStorage {

	companion object {

		const val SHARED_PREFERENCES_FILENAME = "myPrefs"
		const val IS_FIRST_START = "isFirstStart"
	}

	private val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILENAME, Context.MODE_PRIVATE)

	override fun isFirstStart(): Boolean {
		if (sharedPreferences.getString(IS_FIRST_START, "") == "") {
			val e: SharedPreferences.Editor = sharedPreferences.edit()
			e.putString(IS_FIRST_START, "true")
			e.apply()

			return true
		}

		return false
	}
}
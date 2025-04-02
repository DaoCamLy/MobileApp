package com.example.newproject.helper

import android.content.Context

class PreferenceHelper(context: Context) {

        private val sharedPreferences =
            context.getSharedPreferences("Userpref", Context.MODE_PRIVATE)
        private val editor = sharedPreferences.edit()

        fun saveUser(username: String, password: String) {
            sharedPreferences.edit().apply() {
                putString("username", username)
                putString("password", password)
                apply()
            }
        }

        fun getUser(): Pair<String?, String?> {
            val username = sharedPreferences.getString("username", null)
            val password = sharedPreferences.getString("password", null)
            return Pair(username, password)
        }

        fun deleteUser() {
            sharedPreferences.edit().clear().apply()
        }


    }

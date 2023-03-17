package com.example.kittens_catalog.features.auth


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kittens_catalog.R

class AuthActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_fragment)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerAuth, AuthFragment.newInstance())
                .commitNow()
        }
    }
}
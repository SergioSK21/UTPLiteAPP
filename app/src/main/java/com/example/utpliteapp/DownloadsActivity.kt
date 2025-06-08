package com.example.utpliteapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.utpliteapp.R
import com.example.utpliteapp.fragments.DownloadsFragment

class DownloadsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_downloads)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DownloadsFragment())
            .commit()
    }
}

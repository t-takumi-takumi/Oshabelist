package com.outside.oshabelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.outside.oshabelist.databinding.ActivityMainBinding
import com.outside.oshabelist.home.HomeFragment
import com.outside.oshabelist.remoteconfig.RemoteConfigUtils

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        RemoteConfigUtils.initRemoteConfig()
        supportFragmentManager.beginTransaction().add(R.id.content, HomeFragment.newInstance())
            .commit()
    }
}
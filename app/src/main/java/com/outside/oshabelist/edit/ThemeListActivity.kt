package com.outside.oshabelist.edit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.outside.oshabelist.R
import com.outside.oshabelist.databinding.ActivityThemeListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThemeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThemeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThemeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.talk_theme_list)
        supportFragmentManager.beginTransaction().add(R.id.content, ThemeListFragment.newInstance())
            .commit()
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, ThemeListActivity::class.java)
    }
}
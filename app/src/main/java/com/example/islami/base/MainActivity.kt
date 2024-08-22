package com.example.islami.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.islami.R
import com.example.islami.databinding.ActivityMainBinding
import com.example.islami.ui.HadithFragment
import com.example.islami.ui.QuranFrafment
import com.example.islami.ui.RadioFragment
import com.example.islami.ui.SibhahFragment

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigateViews()

    }

    private fun navigateViews() {
        binding.contentHome.btmNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_radio -> { showTab(RadioFragment())}
                R.id.nav_sibhah -> { showTab(SibhahFragment())}
                R.id.nav_hadith -> { showTab(HadithFragment())}
                R.id.nav_quran -> { showTab(QuranFrafment())}
            }
                return@setOnItemSelectedListener true
        }
    }

    private fun showTab(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
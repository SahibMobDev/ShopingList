package com.example.shopinglist.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceManager
import com.example.shopinglist.R
import com.example.shopinglist.databinding.ActivityMainBinding
import com.example.shopinglist.dialogs.NewListDialog
import com.example.shopinglist.fragments.FragmentManager
import com.example.shopinglist.fragments.NoteFragment
import com.example.shopinglist.fragments.ShoppingListNamesFragment
import com.example.shopinglist.settings.SettingsActivity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity(), NewListDialog.Listener {
    lateinit var binding: ActivityMainBinding
    private lateinit var defPref: SharedPreferences
    private var currentMenuItemId = R.id.notes
    private var currentTheme = ""
    private var iAd: InterstitialAd? = null
    private var adShowCounter = 0
    private var adShowCounterMax = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        defPref = PreferenceManager.getDefaultSharedPreferences(this)
        setTheme(getSelectedTheme())
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        currentTheme = defPref.getString("theme_key", "blue").toString()
        setContentView(binding.root)
        FragmentManager.setFragment(ShoppingListNamesFragment.newInstance(), this)
        setBottomNavListener()
        loadInterAd()

    }

    private fun loadInterAd() {
        val request = AdRequest.Builder().build()
        InterstitialAd.load(this, getString(R.string.inter_ad_id), request, object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(ad: InterstitialAd) {
                iAd = ad
            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                iAd = null
            }
        })
    }

    private fun showInterAd(adListener: AdListener) {
        if (iAd != null && adShowCounter > adShowCounterMax) {
            iAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    iAd = null
                    loadInterAd()
                    adListener.onFinish()
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    iAd = null
                    loadInterAd()
                }

                override fun onAdShowedFullScreenContent() {
                    iAd = null
                    loadInterAd()
                }
            }
            adShowCounter = 0
            iAd?.show(this)
        } else {
            adShowCounter++
            adListener.onFinish()
        }
    }

    private fun setBottomNavListener() {
        binding.bNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.settings -> {
                    showInterAd(object : AdListener {
                        override fun onFinish() {
                            startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
                        }

                    })
                }
                R.id.notes -> {
                    showInterAd(object : AdListener {
                        override fun onFinish() {
                            currentMenuItemId = R.id.notes
                            FragmentManager.setFragment(NoteFragment.newInstance(), this@MainActivity)
                        }
                    })
                }
                R.id.shop_list -> {
                    currentMenuItemId = R.id.shop_list
                    FragmentManager.setFragment(ShoppingListNamesFragment.newInstance(), this)
                }
                R.id.new_item -> {
                    FragmentManager.currentFrag?.onClickNew()

                }
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        binding.bNav.selectedItemId = currentMenuItemId
        if (defPref.getString("theme_key", "blue") !== currentTheme) recreate()
    }

    private fun getSelectedTheme(): Int {
        return if (defPref.getString("theme_key", "blue") == "blue") {
            R.style.Theme_ShoppingListBlue
        } else {
            R.style.Theme_ShoppingListRed
        }
    }

    override fun onClick(name: String) {
        Log.d("MyLog", "name: $name")
    }

    interface AdListener {
        fun onFinish()
    }
}
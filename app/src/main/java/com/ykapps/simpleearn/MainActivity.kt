package com.ykapps.simpleearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ykapps.simpleearn.data.AppState
import com.ykapps.simpleearn.ui.navigation.AppNavigation
import com.ykapps.simpleearn.ui.theme.SimpleEarnTheme

class MainActivity : ComponentActivity() {
    
    private val appState = AppState()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            // Initialize Mobile Ads
            com.google.android.gms.ads.MobileAds.initialize(this)

            setContent {
                SimpleEarnTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        AppNavigation(
                            appState = appState,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

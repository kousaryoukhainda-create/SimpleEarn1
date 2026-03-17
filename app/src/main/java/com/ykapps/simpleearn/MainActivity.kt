package com.ykapps.simpleearn

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ykapps.simpleearn.data.AppState
import com.ykapps.simpleearn.ui.navigation.AppNavigation
import com.ykapps.simpleearn.ui.theme.SimpleEarnTheme

class MainActivity : ComponentActivity() {

    private val appState = AppState()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mobile Ads initialization removed - causes crashes on some devices
        // Can be added back later with proper error handling if needed

        setContent {
            try {
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
            } catch (e: Exception) {
                Log.e("MainActivity", "Error in app content", e)
                ErrorScreen(e)
            }
        }
    }
}

@Composable
fun ErrorScreen(exception: Exception) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3F3)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = "⚠️",
                fontSize = 48.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "An Error Occurred",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFC62828)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = exception.message ?: "Unknown error",
                fontSize = 14.sp,
                color = Color(0xFF666666)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Check logcat for details",
                fontSize = 12.sp,
                color = Color(0xFF999999)
            )
        }
    }
}

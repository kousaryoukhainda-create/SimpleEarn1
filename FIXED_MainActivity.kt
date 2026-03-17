package com.ykapps.simpleearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            // Remove Google Mobile Ads initialization for now
            // MobileAds.initialize(this) can cause crashes if not properly configured
            
            setContent {
                EarnRewardsApp()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

@Composable
fun EarnRewardsApp() {
    var selectedTab by remember { mutableStateOf(0) }
    
    try {
        Scaffold(
            bottomBar = {
                NavigationBar(
                    containerColor = Color.White,
                    contentColor = Color(0xFF0066FF)
                ) {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home", fontSize = 10.sp) },
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Star, contentDescription = "Earn") },
                        label = { Text("Earn", fontSize = 10.sp) },
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Card, contentDescription = "Rewards") },
                        label = { Text("Rewards", fontSize = 10.sp) },
                        selected = selectedTab == 2,
                        onClick = { selectedTab = 2 }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Favorite, contentDescription = "History") },
                        label = { Text("History", fontSize = 10.sp) },
                        selected = selectedTab == 3,
                        onClick = { selectedTab = 3 }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                        label = { Text("Profile", fontSize = 10.sp) },
                        selected = selectedTab == 4,
                        onClick = { selectedTab = 4 }
                    )
                }
            }
        ) { paddingValues ->
            when (selectedTab) {
                0 -> HomeScreen(modifier = Modifier.padding(paddingValues))
                1 -> EarnScreen(modifier = Modifier.padding(paddingValues))
                2 -> RewardsScreen(modifier = Modifier.padding(paddingValues))
                3 -> HistoryScreen(modifier = Modifier.padding(paddingValues))
                4 -> ProfileScreen(modifier = Modifier.padding(paddingValues))
            }
        }
    } catch (e: Exception) {
        ErrorScreen(e)
    }
}

@Composable
fun ErrorScreen(exception: Exception) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "⚠️ Error",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF3B30)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                exception.message ?: "Unknown error occurred",
                fontSize = 14.sp,
                color = Color(0xFF6B7280),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "SimpleEarn",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0066FF)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            "Welcome back!",
            fontSize = 18.sp,
            color = Color(0xFF1A1A1A)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0066FF))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Total Points", color = Color.White, fontSize = 14.sp)
                Text(
                    "2,295",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text("⭐ Silver Member", color = Color.White, fontSize = 12.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SimpleCard("5,745", "Earned")
            SimpleCard("3,450", "Redeemed")
            SimpleCard("2", "Referrals")
        }
    }
}

@Composable
fun SimpleCard(value: String, label: String) {
    Card(
        modifier = Modifier
            .weight(1f)
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(value, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(label, fontSize = 11.sp, color = Color(0xFF6B7280))
        }
    }
}

@Composable
fun EarnScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(16.dp)
    ) {
        Text("Earn Points", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        
        TaskItem("Watch Video", 50)
        Spacer(modifier = Modifier.height(8.dp))
        TaskItem("Take Survey", 150)
        Spacer(modifier = Modifier.height(8.dp))
        TaskItem("Try App", 200)
        Spacer(modifier = Modifier.height(8.dp))
        TaskItem("Refer Friend", 300)
    }
}

@Composable
fun TaskItem(title: String, points: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text("Tap to complete", fontSize = 11.sp, color = Color(0xFF6B7280))
            }
            Text("+$points", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF00C853))
        }
    }
}

@Composable
fun RewardsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(16.dp)
    ) {
        Text("Rewards", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0066FF))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Available Balance", color = Color.White, fontSize = 12.sp)
                Text("2,295 pts", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        RewardItem("Amazon", "$5", 500)
        Spacer(modifier = Modifier.height(8.dp))
        RewardItem("PayPal", "$2.5", 250)
        Spacer(modifier = Modifier.height(8.dp))
        RewardItem("Google Play", "$5", 500)
    }
}

@Composable
fun RewardItem(title: String, value: String, cost: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(title, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text(value, fontSize = 11.sp, color = Color(0xFF6B7280))
            }
            Text("$cost pts", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0066FF))
        }
    }
}

@Composable
fun HistoryScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(16.dp)
    ) {
        Text("History", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        
        HistoryItem("Daily Check-in", "+25", "Mar 15")
        Spacer(modifier = Modifier.height(8.dp))
        HistoryItem("Video Watched", "+50", "Mar 14")
        Spacer(modifier = Modifier.height(8.dp))
        HistoryItem("Survey Completed", "+150", "Mar 13")
        Spacer(modifier = Modifier.height(8.dp))
        HistoryItem("Amazon Gift Card", "-500", "Mar 12")
    }
}

@Composable
fun HistoryItem(title: String, amount: String, date: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(title, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                Text(date, fontSize = 11.sp, color = Color(0xFF6B7280))
            }
            Text(amount, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(16.dp)
    ) {
        Text("Profile", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("SimpleEarn User", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text("user@email.com", fontSize = 12.sp, color = Color(0xFF6B7280))
                Spacer(modifier = Modifier.height(8.dp))
                Text("⭐ Silver • Member for 37 days", fontSize = 11.sp, color = Color(0xFF0066FF))
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        SettingItem("Edit Profile")
        SettingItem("Change Password")
        SettingItem("Notifications")
        SettingItem("Privacy Policy")
        
        Spacer(modifier = Modifier.weight(1f))
        
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF3B30))
        ) {
            Text("Sign Out", color = Color.White)
        }
    }
}

@Composable
fun SettingItem(title: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, fontSize = 14.sp, modifier = Modifier.weight(1f))
            Text("›", fontSize = 16.sp, color = Color(0xFFBDBDBD))
        }
    }
}

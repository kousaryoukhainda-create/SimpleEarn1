package com.ykapps.simpleearn.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ykapps.simpleearn.data.AppState
import com.ykapps.simpleearn.data.Video
import com.ykapps.simpleearn.ui.screens.home.EnhancedHomeScreen
import com.ykapps.simpleearn.ui.theme.*

@Composable
fun MainScreenContent(
    appState: AppState,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    onVideoClick: (Video) -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            ModernNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = onTabSelected
            )
        }
    ) { paddingValues ->
        when (selectedTab) {
            0 -> EnhancedHomeScreen(
                appState = appState,
                onNavigateToVideoPlayer = onVideoClick,
                modifier = Modifier.padding(paddingValues)
            )
            1 -> EarnScreen(
                appState = appState,
                onVideoClick = onVideoClick,
                modifier = Modifier.padding(paddingValues)
            )
            2 -> RewardsScreen(
                appState = appState,
                modifier = Modifier.padding(paddingValues)
            )
            3 -> HistoryScreen(
                appState = appState,
                modifier = Modifier.padding(paddingValues)
            )
            4 -> ProfileScreen(
                appState = appState,
                onLogout = onLogout,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
fun ModernNavigationBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            icon = { TabIcon(Icons.Default.Dashboard, selectedTab == 0) },
            label = { TabText("Home", selectedTab == 0) },
            selected = selectedTab == 0,
            onClick = { onTabSelected(0) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Primary,
                selectedTextColor = Primary,
                indicatorColor = Primary.copy(alpha = 0.1f)
            )
        )
        NavigationBarItem(
            icon = { TabIcon(Icons.Default.PlayCircle, selectedTab == 1) },
            label = { TabText("Earn", selectedTab == 1) },
            selected = selectedTab == 1,
            onClick = { onTabSelected(1) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Primary,
                selectedTextColor = Primary,
                indicatorColor = Primary.copy(alpha = 0.1f)
            )
        )
        NavigationBarItem(
            icon = { TabIcon(Icons.Default.CardGiftcard, selectedTab == 2) },
            label = { TabText("Rewards", selectedTab == 2) },
            selected = selectedTab == 2,
            onClick = { onTabSelected(2) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Primary,
                selectedTextColor = Primary,
                indicatorColor = Primary.copy(alpha = 0.1f)
            )
        )
        NavigationBarItem(
            icon = { TabIcon(Icons.Default.History, selectedTab == 3) },
            label = { TabText("History", selectedTab == 3) },
            selected = selectedTab == 3,
            onClick = { onTabSelected(3) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Primary,
                selectedTextColor = Primary,
                indicatorColor = Primary.copy(alpha = 0.1f)
            )
        )
        NavigationBarItem(
            icon = { TabIcon(Icons.Default.AccountCircle, selectedTab == 4) },
            label = { TabText("Profile", selectedTab == 4) },
            selected = selectedTab == 4,
            onClick = { onTabSelected(4) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Primary,
                selectedTextColor = Primary,
                indicatorColor = Primary.copy(alpha = 0.1f)
            )
        )
    }
}

@Composable
fun TabIcon(icon: ImageVector, isSelected: Boolean) {
    Icon(
        icon,
        contentDescription = null,
        modifier = Modifier.size(24.dp)
    )
}

@Composable
fun TabText(text: String, isSelected: Boolean) {
    Text(
        text = text,
        fontSize = 10.sp,
        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
    )
}

// Placeholder screens for tabs 1-4
@Composable
fun EarnScreen(
    appState: AppState,
    onVideoClick: (Video) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundLight)
            .padding(16.dp)
    ) {
        Text(
            text = "Earn Points",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Watch videos and earn real money",
            fontSize = 14.sp,
            color = TextSecondary
        )
        
        Spacer(modifier = Modifier.height(20.dp))
        
        // List all videos
        appState.videos.value.forEach { video ->
            EarnVideoItem(video, onVideoClick)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun EarnVideoItem(video: Video, onVideoClick: (Video) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onVideoClick(video) }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Primary, Secondary)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = video.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = null,
                        tint = EarningGold,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "+${video.rewardPoints} pts • ${video.duration}s",
                        fontSize = 12.sp,
                        color = Success,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            
            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                tint = TextSecondary
            )
        }
    }
}

@Composable
fun RewardsScreen(
    appState: AppState,
    modifier: Modifier = Modifier
) {
    var showMessage by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundLight)
            .padding(16.dp)
    ) {
        Text(
            text = "Rewards",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Redeem your points for exciting rewards",
            fontSize = 14.sp,
            color = TextSecondary
        )
        
        Spacer(modifier = Modifier.height(20.dp))

        // Balance Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(Primary, PrimaryDark)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Available Balance",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 12.sp
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "${String.format("%.0f", appState.currentBalance.value)} pts",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        
        // Rewards List
        appState.rewards.value.forEach { reward ->
            RewardItem(
                reward = reward,
                onRedeem = {
                    val result = appState.redeemReward(reward.id)
                    isError = result.isFailure
                    message = if (result.isSuccess) {
                        "Reward redeemed successfully!"
                    } else {
                        result.exceptionOrNull()?.message ?: "Redemption failed"
                    }
                    showMessage = true
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun RewardItem(
    reward: com.ykapps.simpleearn.data.Reward,
    onRedeem: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Secondary, SecondaryLight)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🎁", fontSize = 30.sp)
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Column {
                    Text(
                        text = reward.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                    Text(
                        text = reward.description,
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
            }
            
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "${reward.costPoints.toInt()} pts",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Primary
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Button(
                    onClick = onRedeem,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary
                    )
                ) {
                    Text(
                        "Redeem",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun HistoryScreen(
    appState: AppState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundLight)
            .padding(16.dp)
    ) {
        Text(
            text = "Transaction History",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Track your earnings and redemptions",
            fontSize = 14.sp,
            color = TextSecondary
        )
        
        Spacer(modifier = Modifier.height(20.dp))
        
        // Transactions List
        appState.transactions.value.forEach { transaction ->
            TransactionItem(transaction)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun TransactionItem(transaction: com.ykapps.simpleearn.data.Transaction) {
    val (icon, iconBackground, amountColor) = when (transaction.type) {
        com.ykapps.simpleearn.data.TransactionType.EARNING ->
            Triple(Icons.Default.AddCircle, Success, Success)
        com.ykapps.simpleearn.data.TransactionType.REDEMPTION ->
            Triple(Icons.Default.RemoveCircle, Error, Error)
        com.ykapps.simpleearn.data.TransactionType.BONUS ->
            Triple(Icons.Default.Star, EarningGold, EarningGold)
        com.ykapps.simpleearn.data.TransactionType.REFERRAL ->
            Triple(Icons.Default.People, Info, Info)
    }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(
                            iconBackground.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        icon,
                        contentDescription = null,
                        tint = iconBackground,
                        modifier = Modifier.size(24.dp)
                    )
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Column {
                    Text(
                        text = transaction.description,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                    Text(
                        text = android.text.format.DateFormat.format(
                            "MMM dd, yyyy • HH:mm",
                            transaction.timestamp
                        ).toString(),
                        fontSize = 11.sp,
                        color = TextSecondary
                    )
                }
            }
            
            Text(
                text = "${if (transaction.amount > 0) "+" else ""}${String.format("%.0f", transaction.amount)} pts",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = amountColor
            )
        }
    }
}

@Composable
fun ProfileScreen(
    appState: AppState,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val user = appState.currentUser.value
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundLight)
            .padding(16.dp)
    ) {
        Text(
            text = "Profile",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Profile Header
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Avatar
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Primary, Secondary)
                            ),
                            shape = RoundedCornerShape(50.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = user?.name?.firstOrNull()?.toString() ?: "U",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = user?.name ?: "User",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                
                Text(
                    text = user?.email ?: "user@example.com",
                    fontSize = 14.sp,
                    color = TextSecondary
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Tier Badge
                if (user != null) {
                    com.ykapps.simpleearn.ui.screens.home.TierBadge(user.tier)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        
        // Stats
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ProfileStatCard("5,745", "Total Earned")
            ProfileStatCard("3,450", "Redeemed")
            ProfileStatCard("${user?.referralCount ?: 0}", "Referrals")
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        
        // Settings
        Text(
            text = "Settings",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        ProfileMenuItem(Icons.Default.Person, "Edit Profile") { }
        ProfileMenuItem(Icons.Default.Lock, "Change Password") { }
        ProfileMenuItem(Icons.Default.Notifications, "Notifications") { }
        ProfileMenuItem(Icons.Default.PrivacyTip, "Privacy Policy") { }
        ProfileMenuItem(Icons.Default.Help, "Help & Support") { }
        
        Spacer(modifier = Modifier.weight(1f))
        
        // Logout Button
        Button(
            onClick = onLogout,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Error
            )
        ) {
            Icon(
                Icons.Default.Logout,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Sign Out",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun ProfileStatCard(value: String, label: String) {
    Card(
        modifier = Modifier.weight(1f),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Primary
            )
            Text(
                text = label,
                fontSize = 10.sp,
                color = TextSecondary,
                maxLines = 2
            )
        }
    }
}

@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = TextSecondary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = TextPrimary,
                modifier = Modifier.weight(1f)
            )
            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                tint = TextSecondary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

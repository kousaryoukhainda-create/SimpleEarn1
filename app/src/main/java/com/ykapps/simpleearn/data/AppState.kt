package com.ykapps.simpleearn.data

import androidx.compose.runtime.mutableStateOf
import java.util.UUID

class AppState {
    // Authentication State
    var isLoggedIn = mutableStateOf(false)
    var currentUser = mutableStateOf<User?>(null)
    
    // User Data (Mock - In production, use database/API)
    private val mockUsers = mutableListOf(
        User(
            id = "user1",
            email = "demo@simpleearn.com",
            name = "Alex Johnson",
            tier = UserTier.SILVER,
            totalEarned = 1250.0,
            totalWatched = 45,
            referralCount = 8
        )
    )
    
    // Videos Data (Mock)
    val videos = mutableStateOf(listOf(
        Video(
            id = "v1",
            title = "Amazing Tech Innovations 2026",
            thumbnailUrl = "",
            videoUrl = "https://www.w3schools.com/html/mov_bbb.mp4",
            duration = 30,
            rewardPoints = 5.0,
            category = VideoCategory.TECH,
            views = 1250
        ),
        Video(
            id = "v2",
            title = "Top 10 Gaming Moments",
            thumbnailUrl = "",
            videoUrl = "https://www.w3schools.com/html/movie.mp4",
            duration = 45,
            rewardPoints = 8.0,
            category = VideoCategory.GAMING,
            views = 3420
        ),
        Video(
            id = "v3",
            title = "Learn Cooking Basics",
            thumbnailUrl = "",
            videoUrl = "https://www.w3schools.com/html/mov_bbb.mp4",
            duration = 60,
            rewardPoints = 10.0,
            category = VideoCategory.LIFESTYLE,
            views = 890
        ),
        Video(
            id = "v4",
            title = "Music Hits Compilation",
            thumbnailUrl = "",
            videoUrl = "https://www.w3schools.com/html/movie.mp4",
            duration = 40,
            rewardPoints = 6.0,
            category = VideoCategory.MUSIC,
            views = 5600
        ),
        Video(
            id = "v5",
            title = "Educational Science Facts",
            thumbnailUrl = "",
            videoUrl = "https://www.w3schools.com/html/mov_bbb.mp4",
            duration = 35,
            rewardPoints = 7.0,
            category = VideoCategory.EDUCATION,
            views = 2100
        ),
        Video(
            id = "v6",
            title = "Entertainment Weekly Roundup",
            thumbnailUrl = "",
            videoUrl = "https://www.w3schools.com/html/movie.mp4",
            duration = 50,
            rewardPoints = 9.0,
            category = VideoCategory.ENTERTAINMENT,
            views = 4300
        )
    ))
    
    // Rewards Data (Mock)
    val rewards = mutableStateOf(listOf(
        Reward(
            id = "r1",
            title = "Amazon Gift Card",
            description = "$5 Amazon Gift Card",
            imageUrl = "",
            costPoints = 500.0,
            costDollars = 5.0,
            category = RewardCategory.GIFT_CARD,
            stockCount = 100
        ),
        Reward(
            id = "r2",
            title = "PayPal Cash",
            description = "$10 PayPal Cash Out",
            imageUrl = "",
            costPoints = 1000.0,
            costDollars = 10.0,
            category = RewardCategory.CASH_OUT,
            stockCount = 50
        ),
        Reward(
            id = "r3",
            title = "Google Play Card",
            description = "$5 Google Play Credit",
            imageUrl = "",
            costPoints = 500.0,
            costDollars = 5.0,
            category = RewardCategory.GIFT_CARD,
            stockCount = 75
        ),
        Reward(
            id = "r4",
            title = "Mobile Recharge",
            description = "$3 Mobile Balance",
            imageUrl = "",
            costPoints = 300.0,
            costDollars = 3.0,
            category = RewardCategory.MOBILE_RECHARGE,
            stockCount = 200
        ),
        Reward(
            id = "r5",
            title = "Starbucks Voucher",
            description = "$10 Starbucks Gift Card",
            imageUrl = "",
            costPoints = 1000.0,
            costDollars = 10.0,
            category = RewardCategory.VOUCHER,
            stockCount = 30
        )
    ))
    
    // Transactions (Mock)
    val transactions = mutableStateOf(listOf(
        Transaction(
            id = UUID.randomUUID().toString(),
            type = TransactionType.EARNING,
            amount = 5.0,
            description = "Watched: Amazing Tech Innovations",
            timestamp = System.currentTimeMillis() - 3600000
        ),
        Transaction(
            id = UUID.randomUUID().toString(),
            type = TransactionType.EARNING,
            amount = 8.0,
            description = "Watched: Top 10 Gaming Moments",
            timestamp = System.currentTimeMillis() - 7200000
        ),
        Transaction(
            id = UUID.randomUUID().toString(),
            type = TransactionType.REDEMPTION,
            amount = -500.0,
            description = "Amazon Gift Card",
            timestamp = System.currentTimeMillis() - 86400000
        ),
        Transaction(
            id = UUID.randomUUID().toString(),
            type = TransactionType.BONUS,
            amount = 50.0,
            description = "Daily Login Bonus",
            timestamp = System.currentTimeMillis() - 90000000
        ),
        Transaction(
            id = UUID.randomUUID().toString(),
            type = TransactionType.REFERRAL,
            amount = 100.0,
            description = "Friend Referral Bonus",
            timestamp = System.currentTimeMillis() - 172800000
        )
    ))
    
    // Current Balance
    var currentBalance = mutableStateOf(2295.0)
    var currentEarningSession = mutableStateOf<EarningSession?>(null)
    
    // Auth Functions
    fun login(email: String, password: String): Result<User> {
        // Mock login - accept any valid email format
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Result.failure(Exception("Invalid email address"))
        }
        if (password.length < 6) {
            return Result.failure(Exception("Password must be at least 6 characters"))
        }
        
        val user = mockUsers.find { it.email == email } ?: User(
            id = UUID.randomUUID().toString(),
            email = email,
            name = email.substringBefore("@"),
            tier = UserTier.BRONZE
        )
        
        currentUser.value = user
        isLoggedIn.value = true
        return Result.success(user)
    }
    
    fun signup(email: String, password: String, name: String): Result<User> {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Result.failure(Exception("Invalid email address"))
        }
        if (password.length < 6) {
            return Result.failure(Exception("Password must be at least 6 characters"))
        }
        if (name.isBlank()) {
            return Result.failure(Exception("Name cannot be empty"))
        }
        
        val newUser = User(
            id = UUID.randomUUID().toString(),
            email = email,
            name = name,
            tier = UserTier.BRONZE
        )
        
        mockUsers.add(newUser)
        currentUser.value = newUser
        isLoggedIn.value = true
        return Result.success(newUser)
    }
    
    fun logout() {
        currentUser.value = null
        isLoggedIn.value = false
        currentEarningSession.value = null
    }
    
    // Earning Functions
    fun startEarningSession(videoId: String) {
        val video = videos.value.find { it.id == videoId } ?: return
        currentEarningSession.value = EarningSession(
            videoId = videoId,
            startTime = System.currentTimeMillis(),
            currentTime = 0L
        )
    }
    
    fun updateEarningProgress(currentTimeMs: Long) {
        val session = currentEarningSession.value ?: return
        val video = videos.value.find { it.id == session.videoId } ?: return
        
        val progress = currentTimeMs.toFloat() / (video.duration * 1000)
        val earned = video.rewardPoints * progress
        
        currentEarningSession.value = session.copy(
            currentTime = currentTimeMs,
            earnedPoints = earned
        )
    }
    
    fun completeEarningSession() {
        val session = currentEarningSession.value ?: return
        val video = videos.value.find { it.id == session.videoId } ?: return
        
        // Add points to balance
        currentBalance.value += session.earnedPoints
        
        // Add transaction
        transactions.value = listOf(
            Transaction(
                id = UUID.randomUUID().toString(),
                type = TransactionType.EARNING,
                amount = session.earnedPoints,
                description = "Watched: ${video.title}"
            ),
            *transactions.value.toTypedArray()
        )
        
        // Update user stats
        currentUser.value?.let { user ->
            currentUser.value = user.copy(
                totalEarned = user.totalEarned + session.earnedPoints,
                totalWatched = user.totalWatched + 1
            )
        }
        
        currentEarningSession.value = null
    }
    
    fun cancelEarningSession() {
        currentEarningSession.value = null
    }
    
    // Reward Functions
    fun redeemReward(rewardId: String): Result<Unit> {
        val reward = rewards.value.find { it.id == rewardId } ?: return Result.failure(Exception("Reward not found"))
        
        if (currentBalance.value < reward.costPoints) {
            return Result.failure(Exception("Insufficient balance"))
        }
        
        currentBalance.value -= reward.costPoints
        
        transactions.value = listOf(
            Transaction(
                id = UUID.randomUUID().toString(),
                type = TransactionType.REDEMPTION,
                amount = -reward.costPoints,
                description = reward.title
            ),
            *transactions.value.toTypedArray()
        )
        
        return Result.success(Unit)
    }
}

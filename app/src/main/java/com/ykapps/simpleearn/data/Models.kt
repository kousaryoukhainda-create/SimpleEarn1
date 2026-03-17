package com.ykapps.simpleearn.data

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String = "",
    val email: String = "",
    val name: String = "",
    val avatarUrl: String = "",
    val tier: UserTier = UserTier.BRONZE,
    val joinDate: Long = System.currentTimeMillis(),
    val totalEarned: Double = 0.0,
    val totalWatched: Int = 0,
    val referralCount: Int = 0
)

@Serializable
enum class UserTier {
    BRONZE, SILVER, GOLD, PLATINUM, DIAMOND
}

@Serializable
data class Video(
    val id: String = "",
    val title: String = "",
    val thumbnailUrl: String = "",
    val videoUrl: String = "",
    val duration: Int = 0, // in seconds
    val rewardPoints: Double = 0.0,
    val category: VideoCategory = VideoCategory.ENTERTAINMENT,
    val isWatched: Boolean = false,
    val views: Int = 0
)

@Serializable
enum class VideoCategory {
    ENTERTAINMENT, EDUCATION, GAMING, TECH, LIFESTYLE, MUSIC
}

@Serializable
data class Transaction(
    val id: String = "",
    val type: TransactionType = TransactionType.EARNING,
    val amount: Double = 0.0,
    val description: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val status: TransactionStatus = TransactionStatus.COMPLETED
)

@Serializable
enum class TransactionType {
    EARNING, REDEMPTION, BONUS, REFERRAL
}

@Serializable
enum class TransactionStatus {
    PENDING, COMPLETED, FAILED, CANCELLED
}

@Serializable
data class Reward(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val costPoints: Double = 0.0,
    val costDollars: Double = 0.0,
    val category: RewardCategory = RewardCategory.GIFT_CARD,
    val isAvailable: Boolean = true,
    val stockCount: Int = 0
)

@Serializable
enum class RewardCategory {
    GIFT_CARD, CASH_OUT, MOBILE_RECHARGE, VOUCHER, MERCHANDISE
}

@Serializable
data class EarningSession(
    val videoId: String = "",
    val startTime: Long = 0L,
    val currentTime: Long = 0L,
    val earnedPoints: Double = 0.0,
    val isComplete: Boolean = false
)

# Keep Google Ads
-keep class com.google.android.gms.ads.** { *; }
-keep class com.google.android.gms.common.** { *; }

# Keep Kotlin
-keep class kotlin.** { *; }
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**

# Keep Jetpack
-keep class androidx.** { *; }
-dontwarn androidx.**

# Keep app classes
-keep class com.ykapps.earnrewards.** { *; }

# Remove logging
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
}

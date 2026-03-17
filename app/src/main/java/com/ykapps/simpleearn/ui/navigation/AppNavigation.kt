package com.ykapps.simpleearn.ui.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ykapps.simpleearn.data.AppState
import com.ykapps.simpleearn.data.Video
import com.ykapps.simpleearn.ui.screens.auth.LoginScreen
import com.ykapps.simpleearn.ui.screens.auth.SignupScreen
import com.ykapps.simpleearn.ui.screens.auth.SplashScreen
import com.ykapps.simpleearn.ui.screens.home.EnhancedHomeScreen
import com.ykapps.simpleearn.ui.screens.video.VideoPlayerScreen

@Composable
fun AppNavigation(
    appState: AppState,
    modifier: Modifier = Modifier
) {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) }
    var selectedVideo by remember { mutableStateOf<Video?>(null) }
    var selectedTab by remember { mutableStateOf(0) }
    
    // Screen definitions
    sealed class Screen {
        object Splash : Screen()
        object Login : Screen()
        object Signup : Screen()
        object Main : Screen()
        object VideoPlayer : Screen()
    }
    
    when (val screen = currentScreen) {
        is Screen.Splash -> {
            SplashScreen(
                onSplashComplete = {
                    currentScreen = if (appState.isLoggedIn.value) {
                        Screen.Main
                    } else {
                        Screen.Login
                    }
                },
                modifier = modifier
            )
        }
        
        is Screen.Login -> {
            LoginScreen(
                appState = appState,
                onLoginSuccess = { currentScreen = Screen.Main },
                onNavigateToSignup = { currentScreen = Screen.Signup },
                modifier = modifier
            )
        }
        
        is Screen.Signup -> {
            SignupScreen(
                appState = appState,
                onSignupSuccess = { currentScreen = Screen.Main },
                onNavigateToLogin = { currentScreen = Screen.Login },
                modifier = modifier
            )
        }
        
        is Screen.Main -> {
            if (selectedVideo != null) {
                VideoPlayerScreen(
                    video = selectedVideo!!,
                    appState = appState,
                    onBackClick = { selectedVideo = null },
                    onVideoComplete = { selectedVideo = null },
                    modifier = modifier
                )
            } else {
                MainAppScreen(
                    appState = appState,
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it },
                    onVideoClick = { video ->
                        selectedVideo = video
                        currentScreen = Screen.VideoPlayer
                    },
                    onLogout = {
                        appState.logout()
                        currentScreen = Screen.Login
                    },
                    modifier = modifier
                )
            }
        }
        
        is Screen.VideoPlayer -> {
            // Handled above
        }
    }
}

@Composable
fun MainAppScreen(
    appState: AppState,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    onVideoClick: (Video) -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    // This will be the main app with bottom navigation
    // Importing here to avoid circular dependency
    com.ykapps.simpleearn.ui.screens.main.MainScreenContent(
        appState = appState,
        selectedTab = selectedTab,
        onTabSelected = onTabSelected,
        onVideoClick = onVideoClick,
        onLogout = onLogout,
        modifier = modifier
    )
}

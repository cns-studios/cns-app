package com.cns.cnsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cns.cnsapp.ui.components.NavFooter
import com.cns.cnsapp.ui.navigation.SubScreen
import com.cns.cnsapp.ui.screens.AppSettingsScreen
import com.cns.cnsapp.ui.screens.CalendarScreen
import com.cns.cnsapp.ui.screens.ConfigureAccountScreen
import com.cns.cnsapp.ui.screens.ConnectedAppsScreen
import com.cns.cnsapp.ui.screens.HomeScreen
import com.cns.cnsapp.ui.screens.NewRequestsScreen
import com.cns.cnsapp.ui.screens.RecentActivitiesScreen
import com.cns.cnsapp.ui.screens.ShareitScreen
import com.cns.cnsapp.ui.theme.CNSAppTheme
import com.cns.cnsapp.ui.theme.GoogleSansFlex

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CNSAppTheme {
                var currentPage by remember { mutableIntStateOf(2) }
                var subScreen by remember { mutableStateOf<SubScreen?>(null) }

                BackHandler(enabled = subScreen != null) {
                    subScreen = null
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    when (subScreen) {
                        SubScreen.RecentActivities -> {
                            RecentActivitiesScreen(
                                onBack = { subScreen = null },
                            )
                        }
                        SubScreen.ConnectedApps -> {
                            ConnectedAppsScreen(
                                onBack = { subScreen = null },
                            )
                        }
                        SubScreen.NewRequests -> {
                            NewRequestsScreen(
                                onBack = { subScreen = null },
                            )
                        }
                        SubScreen.ConfigureAccount -> {
                            ConfigureAccountScreen(
                                onBack = { subScreen = null },
                            )
                        }
                        SubScreen.AppSettings -> {
                            AppSettingsScreen(
                                onBack = { subScreen = null },
                            )
                        }
                        null -> {
                            when (currentPage) {
                                1 -> ShareitScreen()
                                2 -> HomeScreen(
                                    onNavigate = { subScreen = it },
                                )
                                3 -> CalendarScreen()
                                else -> PlaceholderScreen()
                            }
                        }
                    }

                    if (subScreen == null) {
                        NavFooter(
                            activePage = currentPage,
                            onPageSelected = { currentPage = it },
                            modifier = Modifier.align(Alignment.BottomCenter),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PlaceholderScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Coming soon",
                fontFamily = GoogleSansFlex,
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                text = "This page isn't finished yet. In future updates, more features will gradually be added",
                fontFamily = GoogleSansFlex,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = 12.dp),
            )
        }
    }
}

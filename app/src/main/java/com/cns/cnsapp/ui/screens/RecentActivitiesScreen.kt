package com.cns.cnsapp.ui.screens

import android.view.HapticFeedbackConstants
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cns.cnsapp.R
import com.cns.cnsapp.ui.theme.CNSAppTheme
import com.cns.cnsapp.ui.theme.GoogleSansFlex

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RecentActivitiesScreenPreview() {
    CNSAppTheme {
        RecentActivitiesScreen(onBack = {})
    }
}

@Composable
fun RecentActivitiesScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val view = LocalView.current

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 44.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
                                onBack()
                            },
                        )
                        .align(Alignment.CenterStart),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_left),
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(28.dp),
                    )
                }

                Text(
                    text = "Recent activities",
                    fontFamily = GoogleSansFlex,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.align(Alignment.Center),
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 48.dp),
            ) {
                repeat(5) {
                    ActivityEntry()
                    if (it != 4) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp)
                                .height(2.dp)
                                .background(Color(0xFF8C8C8C)),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ActivityEntry() {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "New Login",
                fontFamily = GoogleSansFlex,
                fontWeight = FontWeight.Medium,
                fontSize = 23.sp,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                text = "Today, 19:23",
                fontFamily = GoogleSansFlex,
                fontWeight = FontWeight.Normal,
                fontSize = 21.sp,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "IP adress",
                fontFamily = GoogleSansFlex,
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                text = "192.168.14.19",
                fontFamily = GoogleSansFlex,
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Device/Type",
                fontFamily = GoogleSansFlex,
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                text = "Android/CNS App",
                fontFamily = GoogleSansFlex,
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
            )

        }
        
    }
}

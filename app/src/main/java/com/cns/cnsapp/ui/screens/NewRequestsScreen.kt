package com.cns.cnsapp.ui.screens

import android.view.HapticFeedbackConstants
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
private fun NewRequestsScreenPreview() {
    CNSAppTheme {
        NewRequestsScreen(onBack = {})
    }
}

@Composable
fun NewRequestsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val view = LocalView.current

    var expandedIndex by remember { mutableIntStateOf(0) }

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
                    text = "New Requests",
                    fontFamily = GoogleSansFlex,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.align(Alignment.Center),
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            RequestCard(
                appName = "ShareIt",
                date = "18.02, 19:04",
                infoLines = listOf(
                    "Approve this new ShareIt device?",
                    "This device wants access to your files",
                ),
                deviceName = "Aarons Nothing",
                expanded = expandedIndex == 0,
                onToggle = { expandedIndex = if (expandedIndex == 0) -1 else 0 },
            )

            Spacer(modifier = Modifier.height(16.dp))

            RequestCard(
                appName = "CNS Auth",
                date = "13.06, 12:35",
                infoLines = listOf(
                    "Approve this login?",
                    "This device wants to Log into your CNS Account",
                ),
                deviceName = "Aarons CachyOS",
                expanded = expandedIndex == 1,
                onToggle = { expandedIndex = if (expandedIndex == 1) -1 else 1 },
            )
        }
    }
}

@Composable
private fun RequestCard(
    appName: String,
    date: String,
    infoLines: List<String>,
    deviceName: String,
    expanded: Boolean,
    onToggle: () -> Unit,
) {
    val view = LocalView.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = if (expanded) 10.dp else 16.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
                            onToggle()
                        },
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = appName,
                    fontFamily = GoogleSansFlex,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = date,
                        fontFamily = GoogleSansFlex,
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Icon(
                        painter = painterResource(
                            if (expanded) R.drawable.ic_chevron_down
                            else R.drawable.ic_chevron_right
                        ),
                        contentDescription = if (expanded) "Collapse" else "Expand",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(start = 4.dp),
                    )
                }
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = "Info",
                        fontFamily = GoogleSansFlex,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    infoLines.forEach { line ->
                        Text(
                            text = line,
                            fontFamily = GoogleSansFlex,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Device",
                        fontFamily = GoogleSansFlex,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = deviceName,
                        fontFamily = GoogleSansFlex,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(24),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF3A3A3A),
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 0.dp),
                    ) {
                        Text(
                            text = "Cancel",
                            fontFamily = GoogleSansFlex,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            color = Color.White,
                            modifier = Modifier.padding(start = 8.dp),
                        )
                    }

                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(24),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4F4F4F),
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 0.dp),
                    ) {
                        Text(
                            text = "Confirm",
                            fontFamily = GoogleSansFlex,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            color = Color.White,
                            modifier = Modifier.padding(start = 8.dp),
                        )
                    }
                }
            }
        }
    }
}

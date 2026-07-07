package com.cns.cnsapp.ui.screens

import android.view.HapticFeedbackConstants
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cns.cnsapp.R
import com.cns.cnsapp.ui.theme.CNSAppTheme
import com.cns.cnsapp.ui.theme.GoogleSansFlex

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CalendarScreenPreview() {
    CNSAppTheme {
        CalendarScreen()
    }
}

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
) {
    val view = LocalView.current

    val weekdays = listOf("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su")
    val nextMonth = listOf(1, 2, 3, 4, 5)

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 64.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "June 2026",
                    fontFamily = GoogleSansFlex,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground,
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    painter = painterResource(R.drawable.ic_notification),
                    contentDescription = "Notifications",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .size(22.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) {
                            view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
                        },
                )

                Spacer(modifier = Modifier.width(16.dp))

                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .size(22.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) {
                            view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
                        },
                )

                Spacer(modifier = Modifier.width(16.dp))

                Icon(
                    painter = painterResource(R.drawable.ic_sliders),
                    contentDescription = "Preferences",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .size(22.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) {
                            view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
                        },
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                weekdays.forEach { day ->
                    Box(
                        modifier = Modifier.width(32.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = day,
                            fontFamily = GoogleSansFlex,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            val weeks = listOf(
                1..7,
                8..14,
                15..21,
                22..28,
            )

            weeks.forEachIndexed { weekIndex, days ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                        .height(if (weekIndex == 0) 4.dp else 2.dp)
                        .background(if (weekIndex == 0) Color(0xFF8B8B8B) else Color(0xFF474747)),
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    days.forEach { date ->
                        Box(
                            modifier = Modifier.width(32.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = date.toString(),
                                fontFamily = GoogleSansFlex,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .height(2.dp)
                    .background(Color(0xFF474747)),
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Box(
                    modifier = Modifier.width(32.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "29",
                        fontFamily = GoogleSansFlex,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
                Box(
                    modifier = Modifier.width(32.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "30",
                        fontFamily = GoogleSansFlex,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
                nextMonth.forEach { date ->
                    Box(
                        modifier = Modifier.width(32.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = date.toString(),
                            fontFamily = GoogleSansFlex,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.White.copy(alpha = 0.3f),
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Appointments",
                    fontFamily = GoogleSansFlex,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground,
                )

                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF242424)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_plus),
                        contentDescription = "Create new",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(20.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            val titles = listOf("Math exam", "Team Meeting", "Gym Session")
            val times = listOf("09:00 - 10:00", "14:00 - 15:30", "18:00 - 19:00")

            titles.forEachIndexed { index, title ->
                var done by remember { mutableStateOf(false) }

                val titleColor by animateColorAsState(
                    targetValue = if (done) Color.White.copy(alpha = 0.4f) else MaterialTheme.colorScheme.onSurface,
                    animationSpec = tween(300),
                    label = "titleColor",
                )
                val timeColor by animateColorAsState(
                    targetValue = if (done) Color.White.copy(alpha = 0.3f) else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    animationSpec = tween(300),
                    label = "timeColor",
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .padding(bottom = 12.dp)
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(10.dp))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) {
                            view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
                            done = !done
                        }
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = title,
                            fontFamily = GoogleSansFlex,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = titleColor,
                            textDecoration = if (done) TextDecoration.LineThrough else TextDecoration.None,
                        )

                        Text(
                            text = times[index],
                            fontFamily = GoogleSansFlex,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = timeColor,
                            textDecoration = if (done) TextDecoration.LineThrough else TextDecoration.None,
                        )
                    }
                }
            }
        }
    }
}

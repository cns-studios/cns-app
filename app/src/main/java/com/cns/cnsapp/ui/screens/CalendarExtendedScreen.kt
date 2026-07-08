package com.cns.cnsapp.ui.screens

import android.view.HapticFeedbackConstants
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cns.cnsapp.R
import com.cns.cnsapp.ui.theme.CNSAppTheme
import com.cns.cnsapp.ui.theme.GoogleSansFlex

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CalendarExtendedScreenPreview() {
    CNSAppTheme {
        CalendarExtendedScreen(onCollapse = {})
    }
}

@Composable
fun CalendarExtendedScreen(
    onCollapse: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val view = LocalView.current
    val weekdays = listOf("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su")
    val weeks = listOf(
        1..7,
        8..14,
        15..21,
        22..28,
    )
    val lastWeekDays = listOf(29, 30)
    val nextMonthDays = listOf(1, 2, 3, 4, 5)

    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) {
                view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
                onCollapse()
            },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Spacer(modifier = Modifier.height(44.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "June 2026",
                    fontFamily = GoogleSansFlex,
                    fontSize = 23.sp,
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

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                weekdays.forEach { day ->
                    Box(
                        modifier = Modifier.width(48.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = day,
                            fontFamily = GoogleSansFlex,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            weeks.forEach { days ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    days.forEach { date ->
                        DaySquare(date = date, isActive = date == 29)
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                lastWeekDays.forEach { date ->
                    DaySquare(date = date, isActive = date == 29)
                }
                nextMonthDays.forEach { date ->
                    DaySquare(date = date, isNextMonth = true)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 80.dp)
                    .padding(bottom = 140.dp)
                    .height(42.dp),
            ) {
                Text(
                    text = "Create Appointment",
                    fontFamily = GoogleSansFlex,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    painter = painterResource(R.drawable.ic_plus),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(22.dp),
                )
            }
        }
    }
}

@Composable
private fun DaySquare(
    date: Int,
    isActive: Boolean = false,
    isNextMonth: Boolean = false,
) {
    data class CalendarEvent(val name: String, val bgColor: Color, val mainColor: Color)

    val events = mapOf(
        5 to CalendarEvent("Yoga", Color(0xFF270F45), Color(0xFF8A30F8)),
        13 to CalendarEvent("Swim", Color(0xFF152931), Color(0xFF1E7297)),
        20 to CalendarEvent("Garden", Color(0xFF011D00), Color(0xFF097803)),
        27 to CalendarEvent("Music", Color(0xFF382609), Color(0xFFB57A19)),
    )

    val event = if (!isNextMonth) events[date] else null

    Box(
        modifier = Modifier
            .width(48.dp)
            .height(90.dp)
            .then(
                if (isActive) Modifier.border(2.dp, Color(0xFF1E7297), RoundedCornerShape(6.dp))
                else Modifier
            )
            .background(
                if (event != null) event.bgColor else MaterialTheme.colorScheme.surface,
                RoundedCornerShape(6.dp),
            ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                text = date.toString(),
                fontFamily = GoogleSansFlex,
                fontSize = 16.sp,
                fontWeight = if (isActive) FontWeight.ExtraBold else FontWeight.Normal,
                color = if (isNextMonth) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                        else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                textAlign = TextAlign.Center,
            )
            if (event != null) {
                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(event.mainColor)
                        .padding(vertical = 1.dp, horizontal = 6.dp),
                ) {
                    Text(
                        text = event.name,
                        color = Color.White,
                        fontSize = 9.sp,
                        lineHeight = 9.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }
    }
}

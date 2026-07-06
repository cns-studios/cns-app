package com.cns.cnsapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cns.cnsapp.ui.theme.NavFooterActive
import com.cns.cnsapp.ui.theme.NavFooterBg

private val navIcons = listOf(
    R.drawable.ic_mail,
    R.drawable.ic_upload,
    R.drawable.ic_home,
    R.drawable.ic_calendar,
)

@Composable
fun NavFooter(
    activePage: Int,
    onPageSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val footerFraction = 201f / 917f
    val activeHeightFraction = 173f / 201f

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
    ) {
        val footerHeight = maxWidth * footerFraction

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(footerHeight)
                .background(NavFooterBg, RoundedCornerShape(footerHeight / 2))
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                navIcons.forEachIndexed { index, iconRes ->
                    NavItem(
                        iconRes = iconRes,
                        isActive = index == activePage,
                        onClick = { onPageSelected(index) },
                        activeHeight = footerHeight * activeHeightFraction,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
private fun NavItem(
    iconRes: Int,
    isActive: Boolean,
    onClick: () -> Unit,
    activeHeight: Dp,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        if (isActive) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(activeHeight)
                    .background(NavFooterActive, RoundedCornerShape(100))
            )
        }
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(28.dp),
        )
    }
}

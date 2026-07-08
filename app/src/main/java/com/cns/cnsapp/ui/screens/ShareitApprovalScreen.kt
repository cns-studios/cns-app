package com.cns.cnsapp.ui.screens

import android.util.Log
import android.view.HapticFeedbackConstants
import android.widget.Spinner
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cns.cnsapp.R
import com.cns.cnsapp.ui.navigation.SubScreen
import com.cns.cnsapp.ui.theme.CNSAppTheme
import com.cns.cnsapp.ui.theme.GoogleSansCode
import com.cns.cnsapp.ui.theme.GoogleSansFlex
import java.util.Dictionary

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ShareitApprovalScreenScreenPreview() {
    CNSAppTheme {
        ShareitApprovalScreen()
    }
}

@Composable
fun ShareitApprovalScreen(
    modifier: Modifier = Modifier,
) {
    val view = LocalView.current

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(20.dp), // Makes sure the column fills width to center items properly
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally // Centers items horizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_lock),
                contentDescription = "Shareit Logo",
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Waiting for approval",
                fontFamily = GoogleSansFlex,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 30.dp),
                textAlign = TextAlign.Center // Keeps text block aligned if it wraps
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "This session must be approved by an existing device.",
                fontFamily = GoogleSansFlex,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 30.dp),
                textAlign = TextAlign.Left
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Device name:",
                fontFamily = GoogleSansFlex,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 30.dp),
                textAlign = TextAlign.Left
            )
            Text(
                text = "ShareitABC",
                fontFamily = GoogleSansCode,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 30.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(30.dp))
            CircularProgressIndicator(
                modifier = Modifier.width(32.dp),
                color = MaterialTheme.colorScheme.onBackground,
                strokeWidth = 3.dp,
                trackColor = MaterialTheme.colorScheme.surface,
            )

            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Wipe data and start new. ",
                fontFamily = GoogleSansFlex,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textDecoration = TextDecoration.Underline,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 30.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}
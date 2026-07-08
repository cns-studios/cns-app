package com.cns.cnsapp.ui.screens

import android.util.Log
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.cns.cnsapp.ui.navigation.SubScreen
import com.cns.cnsapp.ui.theme.CNSAppTheme
import com.cns.cnsapp.ui.theme.GoogleSansFlex
import java.util.Dictionary

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ShareitScreenScreenPreview() {
    CNSAppTheme {
        ShareitScreen()
    }
}

@Composable
fun ShareitScreen(
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 64.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Your files",
                    fontFamily = GoogleSansFlex,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground,
                )

            }

            Spacer(modifier = Modifier.height(32.dp))


            val files = listOf(
                mapOf(
                    "name" to "document.pdf",
                    "expire" to "3d",
                ),
                mapOf(
                    "name" to "presentation.pptx",
                    "expire" to "15d"
                ),
                mapOf(
                    "name" to "spreadsheet.xlsx",
                    "expire" to "1y"
                ),
                mapOf(
                    "name" to "image.jpg",
                    "expire" to "3d"
                ),
            )

            files.forEachIndexed { fileindex, file ->



                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = file["name"] as String,
                        fontFamily = GoogleSansFlex,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground,
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Text(
                            text = "Expires in " + file["expire"] as String,
                            fontFamily = GoogleSansFlex,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = {
                                view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF242424),
                                contentColor = MaterialTheme.colorScheme.onBackground,
                            ),
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_download),
                                contentDescription = "Download",
                                modifier = Modifier.size(20.dp),
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(7.dp))


                if (fileindex != (files.size - 1)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp)
                            .height(2.dp)
                            .background(Color(0xFF474747)),
                    )
                }

                Spacer(modifier = Modifier.height(7.dp))


            }
        }
    }
    Column(modifier = Modifier.fillMaxSize().padding(bottom = 135.dp), verticalArrangement = Arrangement.Bottom) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 64.dp),
            verticalAlignment = Alignment.Bottom,
        ) {
            Button(
                onClick = {  },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                modifier = Modifier
                    .padding(horizontal = 80.dp)
                    .fillMaxWidth()
                    .height(55.dp),
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_upload),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                )
                Text(
                    text = "Upload file",
                    fontFamily = GoogleSansFlex,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
        }
    }

}

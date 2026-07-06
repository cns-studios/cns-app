package com.cns.cnsapp.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cns.cnsapp.ui.theme.CNSAppTheme
import kotlin.math.PI
import kotlin.math.sin

private const val TWO_PI = PI.toFloat() * 2f

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AnimatedWaveBackgroundPreview() {
    CNSAppTheme {
        AnimatedWaveBackground()
    }
}

@Composable
fun AnimatedWaveBackground(
    modifier: Modifier = Modifier,
) {
    val infiniteTransition = rememberInfiniteTransition()
    val scrollOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 8000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .blur(75.dp),
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
        ) {
            val waveCount = 2
            val cycleWidth = size.width / waveCount
            val pixelShift = scrollOffset * cycleWidth

            val baseY = size.height * 0.6f
            val amp = size.height * 0.15f
            val drawExtra = cycleWidth * 3

            val wavePath = Path()
            wavePath.moveTo(-drawExtra, baseY)

            val drawSpan = size.width + drawExtra * 2
            val steps = drawSpan / 2f
            for (i in 0..steps.toInt()) {
                val t = i.toFloat() / steps
                val screenX = -drawExtra + t * drawSpan
                val waveX = screenX + pixelShift
                val phase = waveX / cycleWidth * TWO_PI
                val envPhase = screenX / cycleWidth * TWO_PI * 0.85f
                val ampFactor = 1.0f + 0.6f * sin(envPhase)
                val y = baseY + sin(phase) * amp * ampFactor
                wavePath.lineTo(screenX, y)
            }

            wavePath.lineTo(size.width + drawExtra, size.height)
            wavePath.lineTo(-drawExtra, size.height)
            wavePath.close()

            drawRect(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF501992), Color(0xFF1E7297), Color(0xFF0C2E5B)),
                ),
                size = size,
            )

            drawPath(path = wavePath, color = Color.Black)
        }
    }
}

package com.cns.cnsapp.ui.screens

import android.view.HapticFeedbackConstants
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cns.cnsapp.R
import com.cns.cnsapp.ui.theme.AttentionRed
import com.cns.cnsapp.ui.theme.CNSAppTheme
import com.cns.cnsapp.ui.theme.Error
import com.cns.cnsapp.ui.theme.GoogleSansFlex
import kotlinx.coroutines.delay

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ConfigureAccountScreenPreview() {
    CNSAppTheme {
        ConfigureAccountScreen(onBack = {})
    }
}

@Composable
private fun EditableField(
    initialValue: String,
    isPassword: Boolean = false,
    onPasswordSaveRequested: (() -> Unit)? = null,
    saveResetCount: Int = 0,
    modifier: Modifier = Modifier,
) {
    var text by remember { mutableStateOf(initialValue) }
    var isEditing by remember { mutableStateOf(false) }
    var isSaving by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    val view = LocalView.current

    LaunchedEffect(isSaving) {
        if (isSaving && onPasswordSaveRequested == null) {
            delay(2000L)
            isSaving = false
            isEditing = false
        }
    }

    LaunchedEffect(saveResetCount) {
        if (saveResetCount > 0) {
            isSaving = false
            isEditing = false
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
            .padding(start = 16.dp, end = 4.dp, top = 12.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicTextField(
            value = text,
            onValueChange = { if (isEditing) text = it },
            readOnly = !isEditing,
            singleLine = true,
            textStyle = TextStyle(
                fontFamily = GoogleSansFlex,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface,
            ),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
            modifier = Modifier.weight(1f),
        )

        Spacer(modifier = Modifier.width(8.dp))

        if (isPassword && isEditing) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {
                        view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
                        passwordVisible = !passwordVisible
                    },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(
                        if (passwordVisible) R.drawable.ic_eye_opened
                        else R.drawable.ic_eye_closed
                    ),
                    contentDescription = if (passwordVisible) "Hide password" else "Show password",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(20.dp),
                )
            }
        }

        Box(
            modifier = Modifier.size(36.dp),
            contentAlignment = Alignment.Center,
        ) {
            when {
                isSaving -> CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.onSurface,
                    strokeWidth = 2.dp,
                )
                isEditing -> Icon(
                    painter = painterResource(R.drawable.ic_save),
                    contentDescription = "Save",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) {
                            view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
                            isSaving = true
                            if (isPassword) {
                                onPasswordSaveRequested?.invoke()
                            }
                        },
                )
                else -> Icon(
                    painter = painterResource(R.drawable.ic_edit),
                    contentDescription = "Edit",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) {
                            view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
                            isEditing = true
                        },
                )
            }}
    }
}

@Composable
fun ConfigureAccountScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val view = LocalView.current

    var showDeleteDialog by remember { mutableStateOf(false) }
    var showPasswordDialog by remember { mutableStateOf(false) }
    var showPwConfirmDialog by remember { mutableStateOf(false) }
    var pwSaveResetCount by remember { mutableIntStateOf(0) }

    Box(
        modifier = modifier.fillMaxSize(),
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
                text = "CNS Account",
                fontFamily = GoogleSansFlex,
                fontSize = 23.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.Center),
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Text(
                text = "Username",
                fontFamily = GoogleSansFlex,
                fontSize = 23.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
            )

            Spacer(modifier = Modifier.height(8.dp))

            EditableField(initialValue = "Aaron G.")

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Password",
                fontFamily = GoogleSansFlex,
                fontSize = 23.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
            )

            Spacer(modifier = Modifier.height(8.dp))

            EditableField(
                initialValue = "currentpassword123",
                isPassword = true,
                onPasswordSaveRequested = { showPwConfirmDialog = true },
                saveResetCount = pwSaveResetCount,
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_external_link),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(22.dp),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Configure 2FA",
                    fontFamily = GoogleSansFlex,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }

            Spacer(modifier = Modifier.height(56.dp))

            Text(
                text = "Danger Zone",
                fontFamily = GoogleSansFlex,
                fontSize = 23.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { showDeleteDialog = true },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Error,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
            ) {
                Text(
                    text = "Delete your CNS account",
                    fontFamily = GoogleSansFlex,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onError,
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }

        if (showDeleteDialog) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.6f))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { showDeleteDialog = false },
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {},
                        ),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                    ) {
                        Text(
                            text = "Are you sure?",
                            fontFamily = GoogleSansFlex,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 22.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "This action is permanent. All data associated to your CNS account will be deleted immediately and cannot be undone",
                            fontFamily = GoogleSansFlex,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            Button(
                                onClick = { showDeleteDialog = false },
                                shape = RoundedCornerShape(24),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF3A3A3A),
                                ),
                                modifier = Modifier.weight(1f),
                            ) {
                                Text(
                                    text = "Cancel",
                                    fontFamily = GoogleSansFlex,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp,
                                    color = Color.White,
                                )
                            }

                            Button(
                                onClick = {
                                    showDeleteDialog = false
                                    showPasswordDialog = true
                                },
                                shape = RoundedCornerShape(24),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFB11818),
                                ),
                                modifier = Modifier.weight(1f),
                            ) {
                                Text(
                                    text = "Confirm",
                                    fontFamily = GoogleSansFlex,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp,
                                    color = Color.White,
                                )
                            }
                        }
                    }
                }
            }
        }

        if (showPasswordDialog) {
            var password by remember { mutableStateOf("") }
            var passwordVisible by remember { mutableStateOf(false) }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.6f))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { showPasswordDialog = false },
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {},
                        ),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                    ) {
                        Text(
                            text = "Enter your current Password",
                            fontFamily = GoogleSansFlex,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 22.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "This is a destructible action. Please authenticate to confirm",
                            fontFamily = GoogleSansFlex,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF4F4F4F), RoundedCornerShape(12.dp))
                                .padding(start = 16.dp, end = 4.dp, top = 12.dp, bottom = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            BasicTextField(
                                value = password,
                                onValueChange = { password = it },
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontFamily = GoogleSansFlex,
                                    fontSize = 18.sp,
                                    color = Color.White,
                                ),
                                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                modifier = Modifier.weight(1f),
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                    ) {
                                        view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
                                        passwordVisible = !passwordVisible
                                    },
                                contentAlignment = Alignment.Center,
                            ) {
                                Icon(
                                    painter = painterResource(
                                        if (passwordVisible) R.drawable.ic_eye_opened
                                        else R.drawable.ic_eye_closed
                                    ),
                                    contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                    tint = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.size(20.dp),
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            Button(
                                onClick = { showPasswordDialog = false },
                                shape = RoundedCornerShape(24),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF3A3A3A),
                                ),
                                modifier = Modifier.weight(1f),
                            ) {
                                Text(
                                    text = "Cancel",
                                    fontFamily = GoogleSansFlex,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp,
                                    color = Color.White,
                                )
                            }

                            Button(
                                onClick = {
                                    showPasswordDialog = false
                                },
                                shape = RoundedCornerShape(24),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFB11818),
                                ),
                                modifier = Modifier.weight(1f),
                            ) {
                                Text(
                                    text = "Delete",
                                    fontFamily = GoogleSansFlex,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp,
                                    color = Color.White,
                                )
                            }
                        }
                    }
                }
            }
        }

        if (showPwConfirmDialog) {
            var currentPassword by remember { mutableStateOf("") }
            var currentPasswordVisible by remember { mutableStateOf(false) }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.6f))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { showPwConfirmDialog = false },
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {},
                        ),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                    ) {
                        Text(
                            text = "Confirm Password",
                            fontFamily = GoogleSansFlex,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 22.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Enter your current password to confirm the changes.",
                            fontFamily = GoogleSansFlex,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF4F4F4F), RoundedCornerShape(12.dp))
                                .padding(start = 16.dp, end = 4.dp, top = 12.dp, bottom = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            BasicTextField(
                                value = currentPassword,
                                onValueChange = { currentPassword = it },
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontFamily = GoogleSansFlex,
                                    fontSize = 18.sp,
                                    color = MaterialTheme.colorScheme.onSurface,
                                ),
                                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                                visualTransformation = if (currentPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                modifier = Modifier.weight(1f),
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                    ) {
                                        view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
                                        currentPasswordVisible = !currentPasswordVisible
                                    },
                                contentAlignment = Alignment.Center,
                            ) {
                                Icon(
                                    painter = painterResource(
                                        if (currentPasswordVisible) R.drawable.ic_eye_opened
                                        else R.drawable.ic_eye_closed
                                    ),
                                    contentDescription = if (currentPasswordVisible) "Hide password" else "Show password",
                                    tint = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.size(20.dp),
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            Button(
                                onClick = {
                                    showPwConfirmDialog = false
                                    pwSaveResetCount++
                                },
                                shape = RoundedCornerShape(24),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF3A3A3A),
                                ),
                                modifier = Modifier.weight(1f),
                            ) {
                                Text(
                                    text = "Cancel",
                                    fontFamily = GoogleSansFlex,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp,
                                    color = Color.White,
                                )
                            }

                            Button(
                                onClick = {
                                    showPwConfirmDialog = false
                                    pwSaveResetCount++
                                },
                                shape = RoundedCornerShape(24),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF4F4F4F),
                                ),
                                modifier = Modifier.weight(1f),
                            ) {
                                Text(
                                    text = "Save",
                                    fontFamily = GoogleSansFlex,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp,
                                    color = Color.White,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

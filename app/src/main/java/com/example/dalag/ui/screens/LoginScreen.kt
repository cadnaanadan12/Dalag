package com.example.dalag.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFF9FAFB),
                            Color.White,
                            Color(0xFFF9FAFB)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 28.dp, vertical = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                
                // Header
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFF2E7D32).copy(alpha = 0.1f), RoundedCornerShape(14.dp))
                            .padding(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Agriculture,
                            contentDescription = null,
                            tint = Color(0xFF2E7D32),
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Dalag",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32),
                        letterSpacing = 1.sp
                    )
                }
                
                Spacer(modifier = Modifier.height(40.dp))
                
                Text(
                    text = "Welcome Back",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B5E20),
                    letterSpacing = (-0.5).sp
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "Access your agricultural market intelligence dashboard",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    lineHeight = 24.sp
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                // Phone field
                LoginTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = "Phone Number",
                    placeholder = "e.g. +252 63 XXXXXXXX",
                    leadingIcon = Icons.Outlined.Phone,
                    keyboardType = KeyboardType.Phone
                )
                
                Spacer(modifier = Modifier.height(18.dp))
                
                // Password field
                LoginTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    leadingIcon = Icons.Outlined.Lock,
                    isPassword = true,
                    passwordVisible = passwordVisible,
                    onPasswordToggle = { passwordVisible = !passwordVisible }
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Forgot Password
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    Text(
                        text = "Forgot Password?",
                        color = Color(0xFF2E7D32),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        modifier = Modifier.clickable { /* Handle forgot password */ }
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Remember me
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = { rememberMe = it },
                        colors = CheckboxDefaults.colors(checkedColor = Color(0xFF2E7D32))
                    )
                    Text(
                        text = "Stay signed in for 30 days",
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Medium
                    )
                }
                
                Spacer(modifier = Modifier.height(28.dp))
                
                // Login Button
                Button(
                    onClick = onLoginSuccess,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32)),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Login",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.5.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(20.dp))
                    }
                }
                
                Spacer(modifier = Modifier.height(28.dp))
                
                // Divider
                Row(verticalAlignment = Alignment.CenterVertically) {
                    HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray)
                    Text(
                        text = "OR CONTINUE WITH",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.sp
                    )
                    HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray)
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Social Buttons
                Row(modifier = Modifier.fillMaxWidth()) {
                    SocialButton(icon = Icons.Default.GTranslate, label = "Google", modifier = Modifier.weight(1f)) { }
                    Spacer(modifier = Modifier.width(12.dp))
                    SocialButton(icon = Icons.Default.PhoneIphone, label = "Apple", modifier = Modifier.weight(1f)) { }
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                // Create account
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "New to Dalag?", fontSize = 15.sp, color = Color.Gray)
                    TextButton(onClick = onNavigateToRegister) {
                        Text(
                            text = "Create an Account",
                            color = Color(0xFF2E7D32),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(40.dp))
                
                // Footer
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = Color(0xFF2E7D32)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "SECURE MARKETPLACE ACCESS",
                            fontSize = 11.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 1.5.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "© 2024 Dalag Intelligence. All rights reserved.",
                        fontSize = 11.sp,
                        color = Color.LightGray
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun LoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector,
    placeholder: String = "",
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onPasswordToggle: () -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(16.dp), spotColor = Color.Black.copy(alpha = 0.04f)),
        shape = RoundedCornerShape(16.dp),
        color = Color.White
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(label, color = Color.Gray, fontWeight = FontWeight.Medium) },
            placeholder = { if (placeholder.isNotEmpty()) Text(placeholder, fontSize = 14.sp, color = Color.Gray) },
            leadingIcon = { Icon(leadingIcon, contentDescription = null, tint = Color(0xFF2E7D32), modifier = Modifier.size(22.dp)) },
            trailingIcon = if (isPassword) {
                {
                    IconButton(onClick = onPasswordToggle) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            } else null,
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )
    }
}

@Composable
fun SocialButton(
    icon: ImageVector,
    label: String,
    modifier: Modifier = Modifier,
    onTap: () -> Unit
) {
    Surface(
        modifier = modifier
            .clickable { onTap() }
            .shadow(2.dp, RoundedCornerShape(14.dp), spotColor = Color.Black.copy(alpha = 0.02f)),
        shape = RoundedCornerShape(14.dp),
        color = Color.White,
        border = BorderStroke(1.5.dp, Color(0xFFEEEEEE))
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 14.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(22.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )
        }
    }
}

// ui/screens/SettingsScreen.kt
package com.example.dalag.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dalag.ui.viewmodel.UserViewModel

@Composable
fun SettingsScreen(userViewModel: UserViewModel) {
    val language = userViewModel.currentLanguage.value
    val isSomali = language == "so"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Profile
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color(0xFFE8F5E9), RoundedCornerShape(50))
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(32.dp), tint = Color(0xFF2E7D32))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(userViewModel.userName.value.ifEmpty { "User" }, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("Hargeisa, Somaliland", fontSize = 14.sp, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Preferences heading
        Text(
            if (isSomali) "DOOKHYADA" else "PREFERENCES",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))

        SettingsItem(
            icon = Icons.Default.Notifications,
            title = if (isSomali) "Dookhyada Wargelinta" else "Notification Settings",
            subtitle = if (isSomali) "Wargelinta qiimaha iyo cusboonaysiinta suuqa" else "Price alerts and market updates",
            onClick = {}
        )
        // Language toggle
        SettingsItem(
            icon = Icons.Default.Language,
            title = if (isSomali) "Luuqada" else "Language",
            subtitle = if (isSomali) "Soomaali / Ingiriisi" else "English / Somali",
            trailing = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        if (isSomali) "SOOMAALI" else "ENGLISH",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Switch(
                        checked = isSomali,
                        onCheckedChange = {
                            userViewModel.toggleLanguage()
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color(0xFF2E7D32),
                            checkedTrackColor = Color(0xFFA5D6A7)
                        )
                    )
                }
            },
            onClick = {}
        )
        SettingsItem(
            icon = Icons.Default.LocationCity,
            title = if (isSomali) "Dookhyada Magaalooyinka" else "City Preferences",
            subtitle = "Hargeisa, Burco, Berbera",
            onClick = {}
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Support heading
        Text(
            if (isSomali) "TAAGERADA" else "SUPPORT",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))

        SettingsItem(
            icon = Icons.Default.Help,
            title = if (isSomali) "Caawimaad & Taageero" else "Help & Support",
            subtitle = if (isSomali) "Su'aalaha badana la isweydiiyo iyo la xiriir taageero" else "FAQs and contact support",
            onClick = {}
        )
        SettingsItem(
            icon = Icons.Default.PrivacyTip,
            title = if (isSomali) "Siyaasadda Qarsoodiga" else "Privacy Policy",
            subtitle = if (isSomali) "Shuruudaha iyo isticmaalka xogta" else "Terms and data usage",
            onClick = {}
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Logout button
        Button(
            onClick = { /* Logout - u gudub login */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            border = BorderStroke(1.dp, Color.Red)
        ) {
            Text(
                if (isSomali) "Ka Bax" else "Logout",
                color = Color.Red,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            "App Version 2.4.0 (Dalag Stable)",
            fontSize = 12.sp,
            color = Color.LightGray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun SettingsItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    trailing: @Composable (() -> Unit)? = null,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(title) },
        supportingContent = { Text(subtitle, fontSize = 13.sp, color = Color.Gray) },
        leadingContent = { Icon(icon, contentDescription = null, tint = Color(0xFF2E7D32)) },
        trailingContent = trailing ?: { Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp)) },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    )
}

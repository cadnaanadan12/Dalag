// ui/screens/HomeScreen.kt
package com.example.dalag.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dalag.ui.viewmodel.UserViewModel

@Composable
fun HomeScreen(userViewModel: UserViewModel) {
    val userName = userViewModel.userName.value
    val language = userViewModel.currentLanguage.value
    val greeting = if (language == "so") "Soo dhawow" else "Welcome back"

    val trendingData = listOf(
        TrendingItem("Tomatoes", "Per 25kg Box", "$18.50", "+12%", Color(0xFFFFCDD2)),
        TrendingItem("Basal (Onions)", "Per 50kg Sack", "$24.00", "-5%", Color(0xFFFFE0B2)),
        TrendingItem("Potatoes", "Per 50kg Sack", "$21.20", "+2.1%", Color(0xFFC8E6C9))
    )

    val activityData = listOf(
        ActivityItem("Carrots", "Bag", "$12.00", "+2.1%", Color(0xFF4CAF50)),
        ActivityItem("Peppers", "Crate", "$15.50", "-1.4%", Color(0xFFF44336)),
        ActivityItem("Cabbage", "Piece", "$0.80", "0.0%", Color(0xFF9E9E9E))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Header with user name
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(greeting, fontSize = 14.sp, color = Color.Gray)
                Text(
                    userName.ifEmpty { "User" },
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B5E20)
                )
                Text(
                    text = if (language == "so") "Qiimaha suuqa ee Hargeisa waa deggan yahay" else "Market prices in Hargeisa are stable today.",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFFE8F5E9))
                    .border(1.dp, Color(0xFFA5D6A7), RoundedCornerShape(50))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = null, tint = Color(0xFF2E7D32))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Daily Forecast Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(Color(0xFF2E7D32), Color(0xFF4CAF50))
                        )
                    )
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            if (language == "so") "SAADAHA MAALINLADA" else "DAILY FORECAST",
                            fontSize = 12.sp,
                            color = Color.White.copy(alpha = 0.7f),
                            fontWeight = FontWeight.W500
                        )
                        Text("32°C", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Text(
                            if (language == "so") "Qiimaha suuqa ee Hargeisa waa deggan yahay" else "Market prices in Hargeisa are stable today.",
                            fontSize = 13.sp,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                    Icon(Icons.Default.WbSunny, contentDescription = null, tint = Color.White, modifier = Modifier.size(48.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Trending Prices
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                if (language == "so") "Qiimaha Soo Kordhaya" else "Trending Prices",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B5E20)
            )
            TextButton(onClick = { /* View all */ }) {
                Text(if (language == "so") "Dhammaan →" else "View All →", color = Color(0xFF2E7D32))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.height(140.dp)
        ) {
            items(trendingData) { item ->
                TrendingCard(item)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Market Activity
        Text(
            if (language == "so") "Dhaqdhaqaaqa Suuqa" else "Market Activity",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Table Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE8F5E9))
                        .padding(vertical = 8.dp, horizontal = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val headerWeight = 1f
                    Text(if (language == "so") "Badeecad" else "Item", Modifier.weight(headerWeight), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Text(if (language == "so") "Cutub" else "Unit", Modifier.weight(headerWeight), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Text(if (language == "so") "Qiimo" else "Price", Modifier.weight(headerWeight), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Text(if (language == "so") "Isbeddel" else "Change", Modifier.weight(headerWeight), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
                
                activityData.forEach { item ->
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp, horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val cellWeight = 1f
                        Text(item.name, Modifier.weight(cellWeight), fontSize = 14.sp)
                        Text(item.unit, Modifier.weight(cellWeight), fontSize = 14.sp)
                        Text(item.price, Modifier.weight(cellWeight), fontSize = 14.sp)
                        Text(
                            item.change,
                            modifier = Modifier.weight(cellWeight),
                            color = item.changeColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

data class TrendingItem(val name: String, val unit: String, val price: String, val change: String, val bgColor: Color)
data class ActivityItem(val name: String, val unit: String, val price: String, val change: String, val changeColor: Color)

@Composable
fun TrendingCard(item: TrendingItem) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = item.bgColor),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(item.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(item.unit, fontSize = 12.sp, color = Color.Gray)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(item.price, fontWeight = FontWeight.W700, fontSize = 18.sp)
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (item.change.startsWith("+")) Color(0xFFA5D6A7) else Color(0xFFFFAB91)
                ) {
                    Text(
                        item.change,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = if (item.change.startsWith("+")) Color(0xFF2E7D32) else Color(0xFFC62828)
                    )
                }
            }
        }
    }
}

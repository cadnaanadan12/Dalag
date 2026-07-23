// ui/screens/MarketsScreen.kt
package com.example.dalag.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dalag.ui.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketsScreen(userViewModel: UserViewModel) {
    val language = userViewModel.currentLanguage.value
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Vegetables", "Fruits", "Grains", "Oils", "Poultry", "Spices")
    val tabsSomali = listOf("Khudaar", "Miro", "Hadhuudh", "Saliid", "Digag", "Cusbo")

    val marketItems = listOf(
        MarketItem("Tomatoes", "Yaanyo (Local)", "1kg", "8,500", "~ 2.4"),
        MarketItem("Onions", "Basal (Imported)", "1kg", "6,200", "~ 1.2%"),
        MarketItem("Potatoes", "Baradhada (Local)", "1kg", "5,400", "Stable"),
        MarketItem("Peppers", "Basbaas (Green)", "Bag (S)", "12,000", "~ 5.8%"),
        MarketItem("Carrots", "Karooto (Bunch)", "Bundle", "7,500", "~ 3.1%"),
        MarketItem("Watermelon", "Qarre (Large)", "Piece", "25", "~ 5.8%")
    )

    var selectedCity by remember { mutableStateOf("Hargeisa") }
    val cities = listOf("Hargeisa", "Burco", "Berbera")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            if (language == "so") "Qiimaha Suuqa Tooska Ah" else "Live Market Rates",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20)
        )
        Spacer(modifier = Modifier.height(8.dp))

        // City chips
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            cities.forEach { city ->
                FilterChip(
                    selected = selectedCity == city,
                    onClick = { selectedCity = city },
                    label = { Text(city) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFF2E7D32).copy(alpha = 0.15f),
                        selectedLabelColor = Color(0xFF2E7D32)
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tabs
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 0.dp,
            containerColor = Color.Transparent,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    height = 3.dp,
                    color = Color(0xFF2E7D32)
                )
            }
        ) {
            tabs.forEachIndexed { index, tab ->
                val label = if (language == "so") tabsSomali[index] else tab
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            label,
                            color = if (selectedTabIndex == index) Color(0xFF2E7D32) else Color.Gray,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search field
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text(if (language == "so") "Raadi khudaar (tusaale: Basal, Yaanyo)" else "Search vegetables (e.g., Basal, Yaanyo)") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // List of items
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(marketItems) { item ->
                MarketListItem(item)
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                // Market Sentiment
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            if (language == "so") "Dareenka Suuqa" else "Market Sentiment",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color(0xFF0D47A1)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            if (language == "so") "Qiimaha ayaa la filayaa inuu hoos u dhaco Hargeisa usbuuca soo socda sababtoo ah sahay badan oo ka timaadda Wajaale." else "Expect price drops in Hargeisa next week due to high supply from Wajaale.",
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Price Comparison
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            if (language == "so") "Isbarbardhigga Qiimaha" else "Price Comparison",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color(0xFFE65100)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("• Yaanyo (Tomatoes)", fontWeight = FontWeight.Medium)
                        Text("  Burco: 9,200  |  Berbera: 8,800", fontSize = 13.sp, color = Color.Gray)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("• Basal (Onions)", fontWeight = FontWeight.Medium)
                        Text("  Burco: 6,000  |  Berbera: 6,500", fontSize = 13.sp, color = Color.Gray)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

data class MarketItem(val produce: String, val variety: String, val unit: String, val price: String, val change: String)

@Composable
fun MarketListItem(item: MarketItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(item.produce, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text("${item.variety}  •  ${item.unit}", fontSize = 14.sp, color = Color.Gray)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text("${item.price} SLSH", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(item.change, fontSize = 12.sp, color = if (item.change.contains("Stable")) Color.Gray else Color(0xFF2196F3))
            }
        }
    }
}

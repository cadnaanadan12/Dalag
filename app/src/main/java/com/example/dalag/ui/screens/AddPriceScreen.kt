// ui/screens/AddPriceScreen.kt
package com.example.dalag.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dalag.ui.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPriceScreen(userViewModel: UserViewModel) {
    val language = userViewModel.currentLanguage.value
    var selectedVegetable by remember { mutableStateOf("TOMATO") }
    var vegetableExpanded by remember { mutableStateOf(false) }
    
    var selectedCity by remember { mutableStateOf("Hargeisa") }
    var cityExpanded by remember { mutableStateOf(false) }
    
    var price by remember { mutableStateOf("") }
    
    var selectedUnit by remember { mutableStateOf("KG") }
    var unitExpanded by remember { mutableStateOf(false) }

    val vegetables = listOf("TOMATO", "BASAL", "PEPPER")
    val cities = listOf("Hargeisa", "Burco", "Berbera", "Wajaale", "Gebiley", "Boorama")
    val units = listOf("KG", "Piece", "Box", "Sack")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            if (language == "so") "Cusboonaysii Qiimaha Suuqa" else "Update Market Price",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20)
        )
        Text(
            if (language == "so") "Ka qayb qaad xogta waqtiga dhabta ah si aad u caawiso beeraleyda iyo ganacsatada deegaanka." else "Contribute real-time data to help local farmers and traders stay informed.",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Vegetable dropdown
        ExposedDropdownMenuBox(
            expanded = vegetableExpanded,
            onExpandedChange = { vegetableExpanded = !vegetableExpanded }
        ) {
            TextField(
                value = selectedVegetable,
                onValueChange = {},
                readOnly = true,
                label = { Text(if (language == "so") "DOORO KHUDAAR" else "SELECT VEGETABLE") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = vegetableExpanded) },
                modifier = Modifier.fillMaxWidth().menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = vegetableExpanded,
                onDismissRequest = { vegetableExpanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                vegetables.forEach { veg ->
                    DropdownMenuItem(
                        text = { Text(veg) },
                        onClick = { 
                            selectedVegetable = veg
                            vegetableExpanded = false
                        }
                    )
                }
            }
        }

        // City dropdown
        ExposedDropdownMenuBox(
            expanded = cityExpanded,
            onExpandedChange = { cityExpanded = !cityExpanded }
        ) {
            TextField(
                value = selectedCity,
                onValueChange = {},
                readOnly = true,
                label = { Text(if (language == "so") "MAGAALADA SUUQA" else "MARKET CITY") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = cityExpanded) },
                modifier = Modifier.fillMaxWidth().menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = cityExpanded,
                onDismissRequest = { cityExpanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                cities.forEach { city ->
                    DropdownMenuItem(
                        text = { Text(city) },
                        onClick = { 
                            selectedCity = city
                            cityExpanded = false
                        }
                    )
                }
            }
        }

        // Price and Unit row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text(if (language == "so") "QIIMO (SLSH)" else "PRICE (SLSH)") },
                modifier = Modifier.weight(2f),
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            ExposedDropdownMenuBox(
                expanded = unitExpanded,
                onExpandedChange = { unitExpanded = !unitExpanded },
                modifier = Modifier.weight(1f)
            ) {
                TextField(
                    value = selectedUnit,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(if (language == "so") "CUTUB" else "UNIT") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = unitExpanded) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = unitExpanded,
                    onDismissRequest = { unitExpanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    units.forEach { unit ->
                        DropdownMenuItem(
                            text = { Text(unit) },
                            onClick = { 
                                selectedUnit = unit
                                unitExpanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Submit price */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
        ) {
            Text(
                if (language == "so") "Soo Gudbi Qiimaha" else "Submit Price",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            "© 2024 Dalag Intelligence",
            fontSize = 12.sp,
            color = Color.LightGray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

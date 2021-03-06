package com.fatah.pixar.feature_search.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fatah.pixar.Screen
import java.util.*

@Composable
fun BottomMenu(
    items: List<BottomContentMenu>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = Color.Black,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Color.LightGray,
    initialSelectedItemIndex: Int = 0,
    navController: NavController
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor,
                navController = navController,
                navigateTo = item.title.lowercase(Locale.getDefault()) + "_screen"
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomContentMenu,
    activeHighlightColor: Color = Color.Black,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Color.LightGray,
    navController: NavController,
    navigateTo: String,
    onItemClick:() ->Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable {
               navController.navigate(navigateTo)
            }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (item.active) activeHighlightColor else Color.Transparent)
                .padding(8.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (item.active) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = if (item.active) activeTextColor else inactiveTextColor
        )
    }
}

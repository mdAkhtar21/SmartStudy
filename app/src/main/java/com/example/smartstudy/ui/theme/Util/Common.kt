package com.example.smartstudy.ui.theme.Util

import androidx.compose.ui.graphics.Color
import com.example.smartstudy.ui.theme.Green
import com.example.smartstudy.ui.theme.Orange
import com.example.smartstudy.ui.theme.Red

enum class Priority(val title: String, val color: Color, val value: Int) {
    Low(title = "Low", color = Green, value = 0),
    Medium(title = "Medium", color = Orange, value = 1),
    High(title = "High", color = Red, value = 2);

    companion object {
        fun fromInt(value: Int) = values().firstOrNull { it.value == value } ?: Medium
    }
}

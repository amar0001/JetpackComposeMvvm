package com.test.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextListWidget(string: String)
{
    Text(text = string,  modifier = Modifier.padding(start = 8.dp, top = 12.dp, end = 8.dp),
        style = MaterialTheme.typography.titleMedium)
}
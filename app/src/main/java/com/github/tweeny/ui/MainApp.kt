package com.github.tweeny.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.tweeny.uiState.ApiState
import com.github.tweeny.viewmodel.MainViewModel

@Composable
fun MainApp() {
    val viewModel = viewModel(modelClass = MainViewModel::class.java)
    val state by viewModel._postStateFlow.collectAsState()
    LazyColumn {
        when (state) {
            is ApiState.Failure -> {
                item { Text(text = "API Error") }
            }
            is ApiState.Loading -> {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
            }
            is ApiState.Success -> {
                item {
                    Text(text = "Success", modifier = Modifier.fillMaxSize().wrapContentSize(
                        Alignment.Center))
                }
            }
            is ApiState.Empty -> {
            }
        }
    }
}

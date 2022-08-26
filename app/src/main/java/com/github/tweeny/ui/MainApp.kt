package com.github.tweeny.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.tweeny.model.Post
import com.github.tweeny.uiState.ApiState
import com.github.tweeny.viewmodel.MainViewModel

@Composable
fun MainApp() {
    val viewModel = viewModel(modelClass = MainViewModel::class.java)
    val state by viewModel._postStateFlow.collectAsState()
    Surface(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is ApiState.Failure -> {
                Text(text = "API Error")
            }
            is ApiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }
            is ApiState.Success -> {
                PostList((state as ApiState.Success).data)
            }
            is ApiState.Empty -> {
            }
        }
    }
}

@Composable
fun PostList(data: List<Post>) {
    Card(modifier = Modifier.fillMaxSize()) {
        Text(text = "Posts")
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(data) { item ->
                Text(text = item.body)
            }
        }
    }
}

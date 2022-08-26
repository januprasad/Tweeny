package com.github.tweeny.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.tweeny.model.Post
import com.github.tweeny.uiState.ApiState
import com.github.tweeny.viewmodel.MainViewModel

@Composable
fun MainApp() {
    val viewModel = viewModel(modelClass = MainViewModel::class.java)
    val state = viewModel._postStateFlow.collectAsState().value
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
            is ApiState.Success -> PostList(data = state.data)
            is ApiState.Empty -> {
            }
        }
    }
}

@Composable
fun PostList(data: List<Post>) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(8.dp)
        ) {
            item {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Posts", fontSize = 20.sp)
                }
            }
            items(data) { item ->
                Text(text = item.body, modifier = Modifier.padding(bottom = 10.dp))
            }
        }
    }
}

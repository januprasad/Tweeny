package com.github.tweeny.uiState

import com.github.tweeny.model.Post

sealed class ApiState {
    object Loading : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    class Success(val data: List<Post>) : ApiState()
    object Empty : ApiState()
}

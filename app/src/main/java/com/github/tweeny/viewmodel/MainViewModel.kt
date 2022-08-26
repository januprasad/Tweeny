package com.github.tweeny.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.tweeny.repo.MainRepository
import com.github.tweeny.uiState.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val postStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)

    val _postStateFlow: StateFlow<ApiState> = postStateFlow

    init {
        getPost()
    }

    private fun getPost() = viewModelScope.launch {
        postStateFlow.value = ApiState.Loading
        mainRepository.getPost()
            .catch { e ->
                postStateFlow.value = ApiState.Failure(e)
            }.collect { data ->
                postStateFlow.value = ApiState.Success(data)
            }
    }
}

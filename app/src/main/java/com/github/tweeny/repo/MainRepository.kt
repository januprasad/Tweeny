package com.github.tweeny.repo

import com.github.tweeny.api.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository
@Inject
constructor(private val apiServiceImpl: ApiServiceImpl) {

    fun getPost() = flow {
        emit(apiServiceImpl.getPost())
    }.flowOn(Dispatchers.IO)
}

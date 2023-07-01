package com.mikaelarmonia.core.data.datasource

import kotlinx.coroutines.flow.Flow

interface DataSource<T> {
    fun streamData(): Flow<List<T>>
    suspend fun storeData(data: List<T>): Result<Unit>
}
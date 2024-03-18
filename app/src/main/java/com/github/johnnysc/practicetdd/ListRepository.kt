package com.github.johnnysc.practicetdd

interface ListRepository {
    suspend fun load(): LoadResult
    class CloudFirst(
        private val cacheDataSource: CacheDataSource,
        private val cloudDataSource: CloudDataSource,
        private val handleError: HandleError
    ) : ListRepository {
        override suspend fun load(): LoadResult =
            try {
                LoadResult.Success(cloudDataSource.load().also {
                    cacheDataSource.save(it)
                })
            } catch (e: Exception) {
                val list = cacheDataSource.load()
                if(list.isEmpty()){
                    LoadResult.Error(handleError.handle(e))
                }
                else
                    LoadResult.Success(list)
            }


    }

    class CacheFirst(
        private val cacheDataSource: CacheDataSource,
        private val cloudDataSource: CloudDataSource,
        private val handleError: HandleError
    ) : ListRepository {
        override suspend fun load(): LoadResult {
            val list = cacheDataSource.load()
            return if (list.isNotEmpty())
                LoadResult.Success(list)
            else
                try {
                    LoadResult.Success(cloudDataSource.load().also {
                        cacheDataSource.save(it)
                    })
                } catch (e: Exception) {
                    LoadResult.Error(handleError.handle(e))
                }
        }

    }

}

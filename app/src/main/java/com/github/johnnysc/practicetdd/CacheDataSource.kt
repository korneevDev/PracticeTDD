package com.github.johnnysc.practicetdd

interface CacheDataSource {

    fun add(item: SimpleData)

    fun data(): List<SimpleData>
    class Timed(
        private val now: Now, private val lifeTimeMillis: Int
    ) : CacheDataSource {
        private var map: MutableMap<Pair<SimpleData, Int>, Long> = hashMapOf()

        override fun add(item: SimpleData) {
            map[Pair(item, map.size)] = now.now()
        }

        override fun data(): List<SimpleData> {
            removeExpired()

            return map.keys.toList()
                .sortedBy { it.second }
                .map { it.first }
        }

        private fun removeExpired() {
            map = map.filter {
                now.now() - it.value < lifeTimeMillis
            }.toMutableMap()
        }
    }
}

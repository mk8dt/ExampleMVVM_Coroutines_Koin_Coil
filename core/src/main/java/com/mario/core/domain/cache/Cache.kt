package com.mario.core.domain.cache

abstract class Cache<T>(var validityTime: Long = VALIDITY_LONG_TIME) {

    companion object {
        const val VALIDITY_LONG_TIME = 1200000L
    }

    private var data: T? = null
    private var timestamp: Long = 0

    fun load(): T? = if (data != null && isValidTime()) data else null

    fun save(value: T) {
        data = value
        updateTimestamp()
    }

    private fun clear() {
        data = null
    }

    private fun updateTimestamp() {
        timestamp = System.currentTimeMillis()
    }

    private fun isValidTime(): Boolean {
        return System.currentTimeMillis() - timestamp < validityTime
    }
}
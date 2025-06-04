package config


import client.RedisClient

import service.RedisCacheService

class CacheModule(private val host: String, private val port: Int) {
    private val jedisClient by lazy { RedisClient.connect(host, port) }
    fun provideRedisCacheService(): RedisCacheService = RedisCacheService(jedisClient)
}
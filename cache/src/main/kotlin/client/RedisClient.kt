package client

import redis.clients.jedis.Jedis

object RedisClient {
    fun connect(host: String, port: Int): Jedis {
       val jedis = Jedis(host, port)
        require(jedis.ping() == "PONG") { "Redis connection failed at $host:$port" }
        return jedis
    }
}
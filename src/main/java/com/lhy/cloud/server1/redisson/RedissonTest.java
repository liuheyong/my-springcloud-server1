package com.lhy.cloud.server1.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author: wenyixicodedog
 * @create: 2020-09-20
 * @description:
 */
public class RedissonTest {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        config.useSingleServer().setPassword("root");
        final RedissonClient client = Redisson.create(config);

        RLock lock = client.getLock("RedissonLock");
        try {
            lock.lock();
        } finally {
            lock.unlock();
        }
    }

}

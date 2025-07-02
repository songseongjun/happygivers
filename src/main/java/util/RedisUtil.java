package util;

import redis.clients.jedis.JedisPooled;

public class RedisUtil {
    private static final JedisPooled JEDIS_POOLED = new JedisPooled("localhost", 6380);

    // 기본 10분
    public static void set(String key, String value) {
        set(key, value, 600);
    }

    public static void set(String key, String value, int expiry) {
        JEDIS_POOLED.setex(key, expiry, value);
    }

    public static String get(String key) {
        return JEDIS_POOLED.get(key);
    }

    public static Long ttl(String key) { 
        return JEDIS_POOLED.ttl(key);
    }
    
    public static void remove(String key) {
    	JEDIS_POOLED.del(key); //이건해당키값해제시키는거임 로그아웃할때사용할수있음?
    }
    public static boolean exists(String key) {  //존재하는가여부
        return JEDIS_POOLED.exists(key);
    }
}


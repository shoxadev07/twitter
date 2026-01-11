package uz.salikhdev.springbootinfinity.config.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Random;

@Configuration
public class redisConfig {

   @Bean
   public JedisConnectionFactory jedisConnectionFactory() {
       var jedis = new JedisConnectionFactory();
       jedis.setHostName("localhost");
       jedis.setPort(6379);
       return jedis;
   }


    @Bean
    public RedisTemplate<String,String> redisTemplate(
            JedisConnectionFactory jedisConnectionFactory
    ) {
       RedisTemplate<String,String> redisTemplate = new RedisTemplate<>();
       redisTemplate.setConnectionFactory(jedisConnectionFactory);
       return new RedisTemplate<>();
    }

    @Bean
    public Random random() {
        return new Random();
    }
}

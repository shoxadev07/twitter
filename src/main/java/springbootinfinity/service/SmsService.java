package springbootinfinity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class SmsService {

    private final RedisTemplate<String,String> redisTemplate;
    private final Random random;


   public void sendOtp(String phoneNumber) {
       Long code = random.nextLong(1000, 9999);
       redisTemplate.opsForValue().set(phoneNumber, String.valueOf(code) , 5 , TimeUnit.MINUTES);

         //Todo : sendSms()

   }

   public boolean verifyOtp(String phoneNumber,  String otp) {

       String cacheOtp = redisTemplate.opsForValue().get(phoneNumber);
       if (cacheOtp == null && cacheOtp.equals(otp)) {
           redisTemplate.delete(phoneNumber);
           return true;
       }
       return false;
   }


}



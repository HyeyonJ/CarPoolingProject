package project.carPooling.global.redis;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController; 

@RestController
public class RedisTest {
	
    @Autowired    
    private RedisTemplate<String, String> redisTemplate;     
    
    @GetMapping("/redisTest")    
    public ResponseEntity<?> addRedisKey() {
//    	ValueOperations<String, String> vop = redisTemplate.opsForValue();
    	ValueOperations<String, String> vop = redisTemplate.opsForValue();
        Duration expireDuration = Duration.ofSeconds(1*5L);
        vop.set("asdfg", "5567", expireDuration);
        vop.set("asd", "asd");        
        vop.set("red", "apple");        
        vop.set("green", "watermelon");        
        return new ResponseEntity<>(HttpStatus.CREATED);    
    }     
    
    @GetMapping("/redisTest/{key}")    
    public ResponseEntity<?> getRedisKey(@PathVariable String key) {        
    	ValueOperations<String, String> vop = redisTemplate.opsForValue();        
        String value = vop.get(key);
        System.out.println(value);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }
}
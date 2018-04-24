package com.paier.word.base;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.paier.word.util.custom.SerializeUtil;



@Resource
public class RedisService {
	public static Logger payLog = LoggerFactory.getLogger("p2pPayFile");
	@Resource
	public StringRedisTemplate redisTemplate;
		
	public void setRedisTemplate(StringRedisTemplate redisTemplate) {  
        this.redisTemplate = redisTemplate;  
    }
	
	public Boolean select(final Integer dbKey){
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(dbKey);
				return true;
			}
		});
		return result;
	}
	
	public Boolean setNX(final Integer dbKey, final String key,final Object obj){
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(dbKey);
				boolean set_result = connection.setNX(key.getBytes(),SerializeUtil.serializeObj(obj));
				return set_result;
			}
		});
		return result;
	}
	
	public Boolean set(final Integer dbKey, final String key,final Object obj){
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(dbKey);
				connection.set(key.getBytes(),SerializeUtil.serializeObj(obj));
				return true;
			}
		});
		return result;
	}
	
	public String get(final Integer dbKey, final String key){
		String result = redisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(dbKey);
				byte[] result = connection.get(key.getBytes()) ;
				payLog.info("进入redis处理方法：dbKey：" + dbKey + ",key:" + key + ", result:" + SerializeUtil.unserializeObj(result));
				if (null == result || null == SerializeUtil.unserializeObj(result)) {
					return "";
				}
				return result == null ? "" : SerializeUtil.unserializeObj(result).toString();
			}
		});
		return result;
	}
	
	public Boolean sadd(final Integer dbKey,final String key,final String obj){
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(dbKey);
				Long sadd_result = connection.sAdd(key.getBytes(), obj.getBytes());
				return sadd_result > 0 ? true : false;
			}
			
		});
		return result;
	}
	
	public Boolean srem(final Integer dbKey,final String key, final String val){
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(dbKey);
				Long srem_result = connection.sRem(key.getBytes(), val.getBytes());
				return srem_result > 0 ? true : false;
			}
		});
		return result;
	}
	
	public Boolean expire(final Integer dbKey,final String key,final Integer time){
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(dbKey);
				boolean set_result = connection.expire(key.getBytes(), time);
				return set_result;
			}
		});
		return result;
	}
	
	public Boolean del(final Integer dbKey,final String key){
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(dbKey);
				connection.del(key.getBytes());
				return true;
			}
		});
		return true;
	}
	
}

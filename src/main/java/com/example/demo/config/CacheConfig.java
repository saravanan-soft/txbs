package com.example.demo.config;

import java.time.Duration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.Configuration;
import javax.cache.spi.CachingProvider;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Expirations.ExpiryBuilder;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;



@SpringBootApplication  
@EnableCaching  
public class CacheConfig {
	
	@Bean
	public CacheManager getCacheManager() {
		CachingProvider cachingProvider=Caching.getCachingProvider();
		CacheManager cacheManager=cachingProvider.getCacheManager();
		
		CacheConfiguration<Integer,Double> config=CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, Double.class, ResourcePoolsBuilder.heap(100).offheap(10, MemoryUnit.MB)).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(10))).build();
	
		 
        Configuration<Integer,Double> cacheConfig=Eh107Configuration.fromEhcacheCacheConfiguration(config);
        
        cacheManager.createCache("test", cacheConfig);
        
        return cacheManager;
        
	
	}

}

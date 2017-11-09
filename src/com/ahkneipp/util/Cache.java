package com.ahkneipp.util;

import java.util.HashMap;

public class Cache
{
	HashMap<String, Cachable> cache = new HashMap<>();
	
	public boolean keyIsCached(String key)
	{
		return this.keyLastUpdated(key) == 0;
	}
	
	public long keyLastUpdated(String key)
	{
		return this.cache.get(key) == null ? 0 : this.cache.get(key).getLastUpdated();
	}
	
	public boolean add(Cachable thingToCache)
	{
		if(this.cache.containsKey(thingToCache.getUniqueKey()))
		{
			return false;
		}
		return this.cache.put(thingToCache.getUniqueKey(), thingToCache) == null;
	}
	
	public Cachable get(String key)
	{
		return this.cache.get(key);
	}
}

package com.ahkneipp.util;

public interface Cachable
{
	public long getLastUpdated();
	public Cachable update(String json);
	public String getUniqueKey();
}

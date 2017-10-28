package com.ahkneipp.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class WebsiteReader extends Reader
{
	private InputStreamReader reader;
	
	public WebsiteReader(InputStream in)
	{
		this.reader = new InputStreamReader(in);
	}
	
	public WebsiteReader(InputStreamReader in)
	{
		this.reader = in;
	}
	
	public String readWebsite() throws IOException
	{
		StringBuilder serverReturn = new StringBuilder();
		char[] buffer = new char[4096];
		int len;
		while ((len = this.reader.read(buffer)) > 0) 
		{
		    serverReturn.append(buffer, 0, len);
		}
		return serverReturn.toString();
	}
	
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException
	{
		return this.reader.read(cbuf, off, len);
	}

	@Override
	public void close() throws IOException
	{
		this.reader.close();
	}

}

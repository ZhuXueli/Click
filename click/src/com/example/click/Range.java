package com.example.click;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.graphics.Point;

public class Range {
	
	private HashMap<String, List<Point>> m_infoHashMap = new HashMap<String, List<Point>>();
	private InputStream m_in;
	
	public Range(InputStream in)
	{
		m_in = in;
		loadRange();
	}
	
	private void loadRange()
	{
//		XMLPointService dps = new XMLPointService();
		JsonPointService jps = new JsonPointService();
		try {
//			dps.getPoints(m_in, m_infoHashMap);
			jps.getPoints(m_in, m_infoHashMap);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String RangeJudge(Point p)
	{
		Iterator iterator = m_infoHashMap.entrySet().iterator();
		while(iterator.hasNext())
		{
			Map.Entry entry = (Map.Entry)iterator.next();
			List<Point> lp = (List<Point>)entry.getValue();
			if(RangeJudge(p, lp))
			{
				return (String)entry.getKey();
			}
		}
		return null;
	}
	
	
	private boolean RangeJudge(Point p, List<Point> lp)
	{
		int maxX = 0;
		int maxY = 0;
		int minX = 10000;
		int minY = 10000;
		Iterator<Point> iterator = lp.iterator();
		while(iterator.hasNext())
		{
			Point point = iterator.next();
			if(maxX < point.x)
				maxX = point.x;
			if(maxY < point.y)
				maxY = point.y;
			if(minX > point.x)
				minX = point.x;
			if(minY > point.y)
				minY = point.y;
		}
		if(p.x > minX && p.x < maxX && p.y > minY && p.y < maxY)
			return true;
		else
			return false;
	}
	
	public String RangeJudgeToString(Point p)
	{
		return RangeJudge(p);
	}
	
	public List<Point> RangeJudgeToList(Point p)
	{
		return m_infoHashMap.get(RangeJudge(p));
	}
	
	public HashMap<String, List<Point>> getinfoMap()
	{
		return m_infoHashMap;
	}
}

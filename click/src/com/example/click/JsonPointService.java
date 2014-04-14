package com.example.click;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.graphics.Point;

public class JsonPointService {
	
	private String instreamToString(InputStream inStream)
	{
		StringBuilder strBuilder = new StringBuilder();
		int tempbyte;
		try {
			while((tempbyte = inStream.read()) != -1)
				strBuilder.append((char)tempbyte);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String jsonString = strBuilder.toString();
		return jsonString;
	}
	
	public void getPoints(InputStream inStream,HashMap<String, List<Point>> infoHashMap)
	{
		String jsonString = instreamToString(inStream);
		JSONTokener jsonParser = new JSONTokener(jsonString);
		JSONObject info;
		try {
			while((info = (JSONObject)jsonParser.nextValue()) != JSONObject.NULL)
			{
				String name = info.getString("name");
				JSONArray points = info.getJSONArray("points");
				List<Point> lp = new ArrayList<Point>();
				for(int i=0;i<points.length();i+=2)
				{
					Point p = new Point();
					p.x = points.getInt(i);
					p.y = points.getInt(i + 1);
					lp.add(p);
				}
				infoHashMap.put(name, lp);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

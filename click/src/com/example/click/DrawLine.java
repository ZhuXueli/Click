package com.example.click;

import java.util.Iterator;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Paint.Style;
import android.widget.ImageView;

public class DrawLine {
	
	private Canvas m_canvas;
	private Paint m_paint;
	private Bitmap m_bitmap;
	
	public DrawLine(ImageView iv)
	{
		m_canvas = new Canvas();
		m_paint = new Paint();
		m_paint.setStyle(Style.STROKE);
		m_paint.setStrokeWidth(5);
		m_paint.setColor(Color.WHITE);
		m_paint.setAntiAlias(true);
		m_paint.setAlpha(100);
		m_bitmap = Bitmap.createBitmap(iv.getWidth(), iv.getHeight(), Bitmap.Config.ARGB_8888);	
		m_canvas.setBitmap(m_bitmap);
	}
	
//	private void DrawALine(Point p1,Point p2)
//	{
//		m_canvas.drawLine(p1.x, p1.y, p2.x, p2.y, m_paint);
//	}
	
	
	public Bitmap DrawRange(List<Point> lp)
	{
		m_paint.setStyle(Paint.Style.FILL);
		Path path = new Path();
		Iterator<Point> iterator = lp.iterator();
		if(iterator.hasNext())
		{
			Point p;
			p = iterator.next();
			path.moveTo(p.x, p.y);
			while(iterator.hasNext())
			{
				p = iterator.next();
				path.lineTo(p.x, p.y);
			}
		}
		path.close();
		m_canvas.drawPath(path, m_paint);
		return m_bitmap;
	}
	
//	public Bitmap DrawRange(List<Point> lp)
//	{
//		Iterator<Point> iterator = lp.iterator();
//		Point pStart = null;
//		Point p1 = null;
//		Point p2 = null;
//		if(iterator.hasNext())
//		{
//			pStart = iterator.next();
//			p1 = pStart;
//			if(iterator.hasNext())
//			{
//				p2 = iterator.next();
//				DrawALine(p1, p2);
//			}
//			//DrawRange(p1);
//		}
//		while(iterator.hasNext())
//		{
//			p1 = p2;
//			p2 = iterator.next();
//			DrawALine(p1, p2);
//			//DrawRange(p1);
//		}
//		DrawALine(p2, pStart);
//		//DrawRange(p2);
//		
//		
//		return m_bitmap;
//	}
}

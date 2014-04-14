package com.example.click;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.graphics.Point;


public class XMLPointService {
    

	public void getPoints(InputStream inStream,HashMap<String, List<Point>> infoHashMap) throws Throwable{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inStream);
        Element root = document.getDocumentElement();
        NodeList positionNode = root.getElementsByTagName("people");
        for(int i = 0 ; i < positionNode.getLength() ; i++){
            Element positionElemt = (Element)positionNode.item(i);
            NodeList nameNode = positionElemt.getChildNodes();
            for(int j = 0;j < nameNode.getLength();j++)
            {
            	if(nameNode.item(j).getNodeType() == Node.ELEMENT_NODE)
            	{
            		Element nameElemt = (Element)nameNode.item(j);
            		NodeList pointNode = nameElemt.getChildNodes();
            		String name = nameElemt.getTagName();
                    List<Point> points = new ArrayList<Point>();
            		for(int k = 0; k < pointNode.getLength();k++)
            		{
            			if(pointNode.item(k).getNodeType() == Node.ELEMENT_NODE)
            			{
            				Point p = new Point();
            				Element pointElemt = (Element)pointNode.item(k);
            				p.x = new Integer(pointElemt.getAttribute("x"));
            				p.y = new Integer(pointElemt.getAttribute("y")); 
            				points.add(p);
            			}
            		}
            		infoHashMap.put(name,points);
            	}
            }
        }
        //return points; 
    }
    
}

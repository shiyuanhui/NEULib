package com.shiyuanhui.xml;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class BookContentHandler extends DefaultHandler{

	private String nodeName;
	private StringBuilder id;
	private StringBuilder name;
	private StringBuilder isower;
	public static ArrayList<String>data = new ArrayList<String>(0);
	@Override
	public void startDocument() throws SAXException {
		id = new StringBuilder();
		name = new StringBuilder();
		isower = new StringBuilder();
		
	}

	@Override
	public void endDocument() throws SAXException {
		
	}


	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		nodeName = localName;
		
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if("book".equals(localName))
		{
			data.add(name.toString().trim());
			//System.out.println(id.toString().trim());
			id.setLength(0);
			name.setLength(0);
			isower.setLength(0);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if("id".equals(nodeName)){
			id.append(ch,start,length);
		}
		else if("name".equals(nodeName)){
			name.append(ch,start,length);
		}
		else if("isower".equals(nodeName)){
			isower.append(ch,start,length);
		}
	}

}

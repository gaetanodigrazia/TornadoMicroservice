/**
* This is a library that provides ready methods to make web service applications building faster
* This is a class that help to extract data from database and convert it to XML
*
* @author  Gaetano Di Grazia
* @version 1.0
* @since   05-08-2021
* 
* 
*/

package com.tornado.helper.file.xml;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class XMLExtractor {

	/*
	 * Default blank constructor
	 */
	public XMLExtractor() {

	}


	/**
	 * Method that convert a string to document
	 * 
	 * @param xmlStr the xml string to convert to document
	 * 
	 * @return a document object
	 * 
	 */
	public Document convertStringToDocument(String xmlStr) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public NodeList getNodeList(Document doc, String tagName) {
        NodeList list = doc.getElementsByTagName(tagName);
        return list;
	}
	
	public Document getDocumentFromFile(String filPath) {

	      // Instantiate the Factory
	      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	      Document doc = null;
	          // optional, but recommended
	          // process XML securely, avoid attacks like XML External Entities (XXE)
	          try {
				dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		          // parse XML file
		          DocumentBuilder db = dbf.newDocumentBuilder();

		          doc = db.parse(new File(filPath));
		          doc.getDocumentElement().normalize();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	          // optional, but recommended
	          // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	          return doc;
	  }


	/**
	 * Method that convert a document to a string
	 * 
	 * @param doc the document to convert to a string
	 * 
	 * @return the document in string format
	 * 
	 */
	public static String toString(Document doc) {
		StringWriter sw = null;
		StreamResult sr = null;
		try {
			DOMSource domSource = new DOMSource(doc);
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			sw = new StringWriter();
			sr = new StreamResult(sw);
			transformer.transform(domSource, sr);
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}

		return sw.toString();
	}
}
/**
* This is a library that provides ready methods to make web service applications building faster
* This is a class that help to serialize a file
*
* @author  Gaetano Di Grazia
* @version 1.0
* @since   05-08-2021
* 
* 
*/
package com.tornado.helper.file.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class XMLParser {

	/**
	 * Default blank constructor
	 */
	public XMLParser() {

	}



	/**
	 * Method that serialize, save as a file or even store, a document  in a location
	 * 
	 * @param doc the xml document you want to serialize
	 * @param filename the filename you want the file will have
	 * 
	 */
	public static void serializeXML(Document doc, String filename)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, ClassCastException,
			TransformerConfigurationException, IOException, TransformerFactoryConfigurationError, TransformerException {
		DOMSource source = new DOMSource(doc);
		FileWriter writer = new FileWriter(
				new File(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory() + "/" + filename
						+ ".xml"), false);
		StreamResult result = new StreamResult(writer);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.transform(source, result);

	}

}

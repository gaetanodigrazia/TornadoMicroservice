
/**
* This is a library that provides ready methods to make web service applications building faster
* This is a class that help to serialize a bean
*
* @author  Gaetano Di Grazia
* @version 1.0
* @since   05-08-2021
* 
* 
*/
package com.tornado.helper.file;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tornado.custom.StringUtils;

@Component
public class Serialize {

	/**
	 * Default blank constructor
	 * 
	 */
	public Serialize() {

	}

	/**
	 * Method that append a string to a file
	 * 
	 * @param filename  the filename you want to write in
	 * @param to_append the string to append to the file
	 * 
	 * @throws IOException
	 */
	public static void appendString(String path, String filename, String to_append) throws IOException {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter out = null;
		filename = StringUtils.capitalize(filename);
		System.out.println(path);
		System.out.println(filename);
		File myObj = new File(path.replace(".", "/")+"/" + filename + ".java");
		fw = new FileWriter(path+"/" + filename + ".java", true);

		if (myObj.createNewFile()) {
			System.out.println("File created: " + myObj.getName());			
		} else {
			System.out.println("File already exists.");

		}
		bw = new BufferedWriter(fw);
		out = new PrintWriter(bw);
		try {
			out.println(to_append);
			out.close();
		} finally {
			if (out != null)
				out.close();
			try {
				if (bw != null)
					bw.close();
			} catch (IOException e) {

			}
			try {
				if (fw != null)
					fw.close();
			} catch (IOException e) {

			}
		}

	}

	
	public static void appendStringFromList(String path, String filename, List<String> to_append) throws IOException {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter out = null;
		filename = StringUtils.capitalize(filename);
		System.out.println(path);
		System.out.println(filename);
		String completePath = path.replace(".", "/")+"/" + filename + ".java";
		System.out.println(completePath);
		if(Files.exists(Paths.get(completePath))) {
			System.out.println("File already exists HERE.");
		} else {
			File myObj = new File(path.replace(".", "/")+"/" + filename + ".java");
			System.out.println("Created a new file");
			fw = new FileWriter(path+"/" + filename + ".java", true);
			bw = new BufferedWriter(fw);
			out = new PrintWriter(bw);
			try {
				for(int i = 0; i < to_append.size(); i++) {
					out.println(to_append.get(i));
				}
				out.close();
			} finally {
				if (out != null)
					out.close();
				try {
					if (bw != null)
						bw.close();
				} catch (IOException e) {

				}
				try {
					if (fw != null)
						fw.close();
				} catch (IOException e) {

				}
			}
		}
	}

	
	
	/**
	 * Method that create a file with filename.java, if a file already exists it will be wiped out and rewrited
	 * 
	 * @param filename is the tablename of the bean
	 *
	 *  
	 */
	public static void createBean(String path, String filename) {
		try {
			createDirectory();
			filename = StringUtils.capitalize(filename);
			File myObj = new File(path+"/" + filename + ".java");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				wipe(filename);
				System.out.println("File already exists.");
			}
			appendString(path, filename, "package bean;");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	/**
	 * Method that erase all the content of a specified file
	 * 
	 * @param filename is the filename we want to wipe
	 * 
	 * @throws FileNotFoundException
	 */
	public static void wipe(String filename) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter("src/bean/" + filename + ".java");
		writer.print("");
		writer.close();
	}

	/**
	 * Method that create the bean directory (package)
	 * 
	 */
	public static void createDirectory() {
		File theDir = new File("src/bean");
		if (!theDir.exists()) {
			theDir.mkdirs();
		}
	}
	

}
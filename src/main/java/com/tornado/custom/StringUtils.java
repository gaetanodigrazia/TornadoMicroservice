/**
 * This is a class that help to manage operations on string
 *
 * @author Gaetano Di Grazia
 * @version 1.0
 * @since 05-08-2021
 * 
 * 
 */
package com.tornado.custom;

public class StringUtils {
	static String static_string = null;
	private String string;

	/*
	 * Default blank constructor
	 */

	public StringUtils() {

	}

	/*
	 * 
	 * Parameterized constructor for a string**
	 * 
	 * @param string the string that you want to pass to constructor
	 */

	public StringUtils(String string) {
		setString(string);
	}

	/*
	 * 
	 * Method that check if a string starts with a lower case
	 * 
	 */
	public boolean isLower() {
		return Character.isLowerCase(this.getString().charAt(0));
	}

	/*
	 * 
	 * Static method that check if a string starts with a lower case
	 * 
	 * @param string the string that you want to test
	 */
	public static boolean isLower(String string) {
		return Character.isLowerCase(string.charAt(0));

	}

	/*
	 * 
	 * Method that check if a string starts with an upper case
	 * 
	 */
	public boolean isUpper() {
		return Character.isUpperCase(this.getString().charAt(0));
	}

	/*
	 * 
	 * Static method that check if a string starts with an upper case
	 * 
	 * @param string the string that you want to test
	 */
	public static boolean isUpper(String string) {
		return Character.isUpperCase(string.charAt(0));
	}

	/*
	 * 
	 * Method that retrieve a substring between two character, number or symbol**
	 * 
	 * @param string the string to split*
	 * 
	 * @param start_char the char at you want to start to split, at left of the*
	 * first interested
	 * 
	 * character (the left delimiter)
	 * 
	 * @param end_char the char at you want to finish to split, at right of the last
	 * interested
	 * 
	 * character (the right delimiter)**@return the selected substring
	 */

	public static String subString(String string, String start_char, String end_char) {
		return string.split(start_char)[1].split(end_char)[0];
	}

	/*
	 * 
	 * Method that retrieve a substring between two character, number or symbol**
	 * 
	 * @param start_char the char at you want to start to split, at left of the*
	 * first interested
	 * 
	 * character (the left delimiter)
	 * 
	 * @param end_char the char at you want to finish to split, at right of the last
	 * interested
	 * 
	 * character (the right delimiter)
	 * 
	 * @return the selected substring
	 */

	public String subString(String start_char, String end_char) {
		return getString().split(start_char)[1].split(end_char)[0];
	}

	/**
	 * Method that extract a substring from a text string
	 * 
	 * @param string   the string to substring
	 * @param interval an interval in the format start:end, start included end
	 *                 excluded
	 * 
	 * @return the substring
	 */
	public static String subStringPython(String string, String interval) {
		StringBuilder sb = new StringBuilder(interval);
		int start_pos = Integer.parseInt(String.valueOf((sb.charAt(0))));
		int end_pos = Integer.valueOf(String.valueOf((sb.charAt(2))));
		System.out.println(start_pos);
		System.out.println(end_pos);
		return string.substring(start_pos, end_pos);
	}

	/*
	 * 
	 * Method that retrieve a string with the first letter capitalized**
	 * 
	 * @param string the string to capitalize**@return the original string but with
	 * the first letter capitalized (uppercase)
	 */

	public static String capitalize(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}

	/*
	 * 
	 * Method that retrieve a string with the first letter capitalized***@return the
	 * original string but with the first letter capitalized (uppercase)
	 */

	public String capitalize() {
		return getString().substring(0, 1).toUpperCase() + getString().substring(1);
	}

	/*
	 * 
	 * Method that check if a string starts with a character
	 * 
	 * @param c c is the char you want to check if the string starts with
	 * 
	 * @param case_sensitive case_sensitive is a flag that enable or disable (true
	 * or false) case sensitive matching of the char
	 */
	public boolean startsWith(char c, boolean case_sensitive) {
		char c_string = this.getString().charAt(0);
		if (!case_sensitive) {
			c = Character.toLowerCase(c);
			c_string = Character.toLowerCase(c_string);
		}
		if (c_string == c) {
			return true;
		} else
			return false;
	}

	/*
	 * 
	 * Static method that check if a string starts with a character
	 * 
	 * @param string is the string to test
	 * 
	 * @param c c is the char you want to check if the string starts with
	 * 
	 * @param case_sensitive case_sensitive is a flag that enable or disable (true
	 * or false) case sensitive matching of the char
	 */
	public static boolean startsWith(String string, char c, boolean case_sensitive) {
		char c_string = string.charAt(0);
		if (!case_sensitive) {
			c = Character.toLowerCase(c);
			c_string = Character.toLowerCase(c_string);
		}
		if (c_string == c) {
			return true;
		} else
			return false;
	}

	/*
	 * 
	 * Method that check if a string end with a character
	 * 
	 * @param c c is the char you want to check if the string end with
	 * 
	 * @param case_sensitive case_sensitive is a flag that enable or disable (true
	 * or false) case sensitive matching of the char
	 */
	public boolean endsWith(char c, boolean case_sensitive) {
		char c_string = this.getString().charAt(this.getString().length() - 1);
		if (!case_sensitive) {
			c = Character.toLowerCase(c);
			c_string = Character.toLowerCase(c_string);
		}
		if (c_string == c) {
			return true;
		} else
			return false;
	}

	/*
	 * 
	 * Static method that check if a string end with a character
	 * 
	 * @param string is the string to test
	 * 
	 * @param c c is the char you want to check if the string end with
	 * 
	 * @param case_sensitive case_sensitive is a flag that enable or disable (true
	 * or false) case sensitive matching of the char
	 */
	public static boolean endWith(String string, char c, boolean case_sensitive) {
		char c_string = string.charAt(string.length() - 1);
		if (!case_sensitive) {
			c = Character.toLowerCase(c);
			c_string = Character.toLowerCase(c_string);
		}
		if (c_string == c) {
			return true;
		} else
			return false;
	}
	
	/*
	 * 
	 * Method that check if a string contains a character
	 * 	 * 
	 * @param c c is the char you want to search
	 * @param index is the position (starting from 1) where you want to find the character
	 * @param case_sensitive case_sensitive is a flag that enable or disable (true
	 * or false) case sensitive matching of the char
	 */
	public boolean contains(char c, int index, boolean case_sensitive) {
		char c_string = this.getString().charAt(--index);
		if (!case_sensitive) {
			c = Character.toLowerCase(c);
			c_string = Character.toLowerCase(c_string);
			if (c_string == c) {
				return true;
			}
		} else {
			if (c_string == c) {
				return true;
			}
		}
		return false;
	}

	
	
	/*
	 * 
	 * Static method that check if a string contains a character
	 * 
	 * @param string is the string to test
	 * 
	 * @param c c is the char you want to search
	 * @param index is the position (starting from 1) where you want to find the character
	 * @param case_sensitive case_sensitive is a flag that enable or disable (true
	 * or false) case sensitive matching of the char
	 */
	public static boolean contains(String string, char c, int index, boolean case_sensitive) {
		char c_string = string.charAt(--index);
		if (!case_sensitive) {
			c = Character.toLowerCase(c);
			c_string = Character.toLowerCase(c_string);
			if (c_string == c) {
				return true;
			}
		} else {
			if (c_string == c) {
				return true;
			}
		}
		return false;
	}

	
	/*
	 * 
	 * Static private method that check if a string contains a character internal use
	 * 
	 * 
	 * @param string is the string to test
	 * @param c c is the char you want to search
	 * @param index is the position (starting from 1) where you want to find the character
	 * @param case_sensitive case_sensitive is a flag that enable or disable (true
	 * or false) case sensitive matching of the char
	 * @param isInternal a flag
	 */
	private static boolean contains(String string, char c, int index, boolean case_sensitive, boolean isInternal) {
		char c_string = string.charAt(index);
		if (!case_sensitive) {
			c = Character.toLowerCase(c);
			c_string = Character.toLowerCase(c_string);
			if (c_string == c) {
				return true;
			}
		} else {
			if (c_string == c) {
				return true;
			}
		}
		return false;
	}

	/*
	 * 
	 * Static method that find all occurrence of a char in a string
	 * 
	 * @param string is the string to test
	 * @param c c is the char you want to search
	 * @param case_sensitive case_sensitive is a flag that enable or disable (true
	 * or false) case sensitive matching of the char
	 */
	public static int findAll(String string, char c, boolean case_sensitive) {
		int total = 0;
		for (int i = 0; i < string.length(); i++) {
			if (contains(string, c, i, case_sensitive, true)) {
				total += 1;
			}
		}
		return total;
	}

	/*
	 * 
	 * Method that find all occurrence of a char in a string
	 * 
	 * @param c c is the char you want to search
	 * @param case_sensitive case_sensitive is a flag that enable or disable (true
	 * or false) case sensitive matching of the char
	 */
	public int findAll(char c, boolean case_sensitive) {
		int total = 0;
		for (int i = 0; i < this.getString().length(); i++) {
			if (contains(this.getString(), c, i, case_sensitive, true)) {
				total += 1;

			}
		}
		return total;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/*
	 * Setter method for set a string
	 * 
	 * @param string the string to set
	 */
	public void setString(String string) {
		this.string = string;
	}

	/*
	 * 
	 * Method that retrieve the string instantiated in the constructor
	 */

	public String getString() {
		return this.string;
	}

}
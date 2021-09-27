package encryption;

import java.util.Arrays;
import java.util.List;

public interface VigenereCipher_Mwamba {
	public final static List<Character> ENGLISH_LOWERCASE_LETTERS_LIST = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
	
	public final static List<Character> ENGLISH_UPPERCASE_LETTERS_LIST = Arrays.asList('A', 'B', 'C', 'D', 'E',
			'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
	
	public final static int NUMBER_OF_LETTERS_IN_ALPHABET = 26;

	//This is the my helper method to get the index of a lower case letter
	public static int getLowercaseCharIndex(char theChar){
		int lowercaseCharIndex = 0;
		char lowercaseChar = ENGLISH_LOWERCASE_LETTERS_LIST.get(lowercaseCharIndex);
		
		while (lowercaseChar != theChar) {
			lowercaseCharIndex ++;
			lowercaseChar = ENGLISH_LOWERCASE_LETTERS_LIST.get(lowercaseCharIndex);
		}
		
		return lowercaseCharIndex;
	}
	
	//This is the my helper method to get the index of a lower case letter
	public static int getUppercaseCharIndex(char theChar){
		int uppercaseCharIndex = 0;
		char uppercaseChar = ENGLISH_UPPERCASE_LETTERS_LIST.get(uppercaseCharIndex);
		
		while (uppercaseChar != theChar) {
			uppercaseCharIndex ++;
			uppercaseChar = ENGLISH_UPPERCASE_LETTERS_LIST.get(uppercaseCharIndex);
		}
		return uppercaseCharIndex;
	}	
	
	//part of pre: plainMessage[i] != null, for i in [0, plainMessage.length)
	//part of pre: plainMessage[i].charAt(j) is in {'a', 'b', 'c', ...,'z'}, for i
	//			   in [0, plainMessage.length) and j in [0, plainMessage[i].length())
	//part of pre: key != null
	//part of pre: key.length() > 0
	//part of post: rv.charAt(j) is in {'A', 'B', 'C', ..., 'Z'} U {' '}, for j // in [0, rv.length()) 
	public static String encrypt(String[] plainMessage, String key){
		String encryptedMessage = "";
		int currentKeyIndex = 0;
		String currentKey = "";
		
		for (int i=0; i < plainMessage.length; i++) {
			//the for loop below generates the key of each word in the plainMessage list.
			for (int j = 0; j < plainMessage[i].length(); j++) {
				if (currentKeyIndex < key.length()) {
					currentKey += key.charAt(currentKeyIndex);
					currentKeyIndex ++;
				}
				else {
					currentKeyIndex = 0;
					currentKey += key.charAt(currentKeyIndex);
					currentKeyIndex ++;
				}
			}
			String encryptedWord = encrypt(plainMessage[i], currentKey);
			encryptedMessage += encryptedWord + " ";
			currentKey = "";
		}
		encryptedMessage = encryptedMessage.strip(); //to remove the extra space at the end of the string.
		return encryptedMessage;
	}
	
	//part of pre: plainText != null
	//part of pre: plainText.charAt(j) is in {'a', 'b', 'c', ...'z'}, for j 
	// 												in [0, plainText.length()) 
	//part of pre: key != null
	//part of pre: key.length() > 0
	//part of post: rv.charAt(j) is in {'A', 'B', 'C', ..., 'Z'}, for j
	// in [0, rv.length()) 
	public static String encrypt(String plainText, String key){
		String encryptedString = "";
		int keyCharIndex = 0;
		
		for (int i = 0; i < plainText.length(); i++) {
			if(keyCharIndex < key.length()) {
				char rowLetter = plainText.charAt(i);
				char columnLetter = key.charAt(keyCharIndex);
				char encryptedLetter = getMatrixEntry(rowLetter, columnLetter);
				keyCharIndex ++;
				encryptedString += encryptedLetter;
			}
			else {
				keyCharIndex = 0;
				char rowLetter = plainText.charAt(i);
				char columnLetter = key.charAt(keyCharIndex);
				char encryptedLetter = getMatrixEntry(rowLetter, columnLetter);
				keyCharIndex ++;
				encryptedString += encryptedLetter;
			}
		}
		return encryptedString;
	}
	
	//part of pre: encryptedMessage[i] != null, for i in [0, encryptedMessage.length)
	//part of pre: encryptedMessage[i].charAt(j) is in {'A', 'B', 'C', ...,'Z'},
	//				in [0, encryptedMessage.length) and j in [0, encryptedMessage[i].length())
	//part of pre: key != null
	//part of pre: key.length() > 0
	//part of post: rv.charAt(j) is in {'a', 'b', 'c', ..., 'z'} U {' '}, for j
	//									in [0, rv.length())
	//part of post: left out, but need to express exactly where the spaces are in rv 
	public static String decrypt(String[] encryptedMessage, String key){
		String decryptedMessage = "";
		int currentKeyIndex = 0;
		String currentKey = "";
		
		for (int i=0; i < encryptedMessage.length; i++) {
			//the for loop below generates the key of each word in the encryptedMessage list.
			for (int j = 0; j < encryptedMessage[i].length(); j++) {
				if (currentKeyIndex < key.length()) {
					currentKey += key.charAt(currentKeyIndex);
					currentKeyIndex ++;
				}
				else {
					currentKeyIndex = 0;
					currentKey += key.charAt(currentKeyIndex);
					currentKeyIndex ++;
				}
			}
			String decryptedWord = decrypt(encryptedMessage[i], currentKey);
			decryptedMessage += decryptedWord + " ";
			currentKey = "";
		}
		decryptedMessage = decryptedMessage.strip(); //to remove the extra space at the end of the string.
		return decryptedMessage;
	}
	
	//part of pre: encryptedText != null
	//part of pre: encryptedText.charAt(j) is in {'A', 'B', 'C', ...,'Z'}, for j
	// in [0, encryptedText.length()) //part of pre: key != null
	//part of pre: key.length() > 0
	//part of post: rv.charAt(j) is in {'a', 'b', 'c', ..., 'z'}, for j in [0, rv.length()) 
	public static String decrypt(String encryptedText, String key){
		String decryptedString = "";
		int keyCharIndex = 0;
		
		for (int i = 0; i < encryptedText.length(); i++) {
			if(keyCharIndex < key.length()) {
				char matrixLetter = encryptedText.charAt(i);
				char rowLetter = key.charAt(keyCharIndex);
				char decryptedLetter = getColumn(rowLetter, matrixLetter);
				decryptedString += decryptedLetter;
				keyCharIndex += 1;
			}
			else {
				keyCharIndex = 0;
				char matrixLetter = encryptedText.charAt(i);
				char rowLetter = key.charAt(keyCharIndex);
				char decryptedLetter = getColumn(rowLetter, matrixLetter);
				decryptedString += decryptedLetter;
			}
		}
		return decryptedString; 
	}

	//part of pre: row is in {'a', 'b', 'c', ...'z'}
	//part of pre: column is in {'a', 'b', 'c', ...'z'} //part of post: rv is in {'A', 'B', 'C', ..., 'Z'}
	public static char getMatrixEntry(char row, char column){
		int rowIndex = getLowercaseCharIndex(row);
		int columnIndex = getLowercaseCharIndex(column);
		int testMatrixIndex = rowIndex + columnIndex;
		char matrixChar;
		
		if (testMatrixIndex < 26) matrixChar = ENGLISH_UPPERCASE_LETTERS_LIST.get(testMatrixIndex);
		else matrixChar = ENGLISH_UPPERCASE_LETTERS_LIST.get(testMatrixIndex - 26);
		
		return matrixChar;
	}
	
	//part of pre: row is in {'a', 'b', 'c', ...'z'}
	//part of pre: matrixEntry is in {'A', 'B', 'C', ..., 'Z'} 
	//part of post: rv is in {'a', 'b', 'c', ...'z'}
	public static char getColumn(char row, char matrixEntry) {	
		int rowIndex = getLowercaseCharIndex(row);
		int matrixIndex = getUppercaseCharIndex(matrixEntry);
		char returnColumnChar;
				
		if (rowIndex <= matrixIndex) {
			int returnColumnCharIndex = matrixIndex - rowIndex;
			returnColumnChar = ENGLISH_LOWERCASE_LETTERS_LIST.get(returnColumnCharIndex);
		}
		else {
			int returnColumnCharIndex = 26 - (rowIndex - matrixIndex);
			returnColumnChar = ENGLISH_LOWERCASE_LETTERS_LIST.get(returnColumnCharIndex);
		}
		
		return returnColumnChar;
	}
}

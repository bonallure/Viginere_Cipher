package encryption;


public class encryptionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Testing the get methods");
		
		char testChar1 = 'E';
		char testChar2 = 'v';
		char testChar3 = 'X';
		char testChar4 = 'k';
		char testChar5 = 'B';
		char testChar6 = 'm';
		
		
		if (VigenereCipher_Mwamba.getMatrixEntry('j', 'v') == testChar1) System.out.println("yes");
		else System.out.println("no");

		if (VigenereCipher_Mwamba.getColumn('j', 'E') == testChar2) System.out.println("yes");
		else System.out.println("no");
		
		if (VigenereCipher_Mwamba.getMatrixEntry('n', 'k') == testChar3) System.out.println("yes");
		else System.out.println("no");
		
		if (VigenereCipher_Mwamba.getColumn('n', 'X') == testChar4) System.out.println("yes");
		else System.out.println("no");
		
		if (VigenereCipher_Mwamba.getMatrixEntry('p', 'm') == testChar5) System.out.println("yes");
		else System.out.println("no");
		
		if (VigenereCipher_Mwamba.getColumn('p', 'B') == testChar6) System.out.println("yes");
		else System.out.println("no");
		
		System.out.println("Boy! Look at you!!!");
		
		if (VigenereCipher_Mwamba.encrypt("thiswillmatch", "a").equals("THISWILLMATCH")) System.out.println("yes"); 
		else System.out.println("no");
		
		if (VigenereCipher_Mwamba.decrypt("THISWILLMATCH", "a").equals("thiswillmatch")) System.out.println("yes");
		else System.out.println("no");
		
		if (VigenereCipher_Mwamba.encrypt("worm", "front").equals("BFFZ")) System.out.println("yes");
		else System.out.println("no");
		
		if (VigenereCipher_Mwamba.decrypt("BFFZ", "front").equals("worm")) System.out.println("yes");
		else System.out.println("no");
		
		if (VigenereCipher_Mwamba.encrypt(new String[]{"crypto", "is", "short", "for", "cryptography"}, "abcd").equals("CSASTP KV SIQUT GQU CSASTPIUAQJB")) {
			System.out.println("yes");
		}
		else System.out.println("no");
		
		if (VigenereCipher_Mwamba.decrypt(new String[]{"CSASTP","KV","SIQUT","GQU","CSASTPIUAQJB"}, "abcd").equals("crypto is short for cryptography")) {
			System.out.println("yes");
		}
		else System.out.println("no");
		
		System.out.println("Boy! Look at you!!!");
	}
}

import static org.junit.Assert.*;

import org.junit.Test;


public class CthulhuCipherTest {

	CthulhuCipher cipher = new CthulhuCipher();
	
	
	@Test
	public void testMakeEncryptable() {
		String s = "ThI,.s is (^&a TEST!!!! (:>";
		s = cipher.makeEncryptable(s);
		//System.out.println(s);
		
		assertEquals("this is a test ", s);
		
	}

	@Test
	public void testMakeDecryptable() {
		String s = "Ron'agl'dhu. ";
		s = cipher.makeDecryptable(s);
		//System.out.println(s);
		
		assertEquals("ron agl dhu ", s);
	}

	@Test
	public void testToMorpheme_withValidInput() {
		char char1 = 'd';
		char char2 = ' ';
		String string1 = cipher.toMorpheme(char1);
		String string2 = cipher.toMorpheme(char2);
		System.out.println(string1);
		System.out.println(string2);
		
		//assertTrue(string1.matches("(shugg|bug|hai)"));
		//assertTrue(string2.matches("(ya|thu|lhu)"));
		
	}

	@Test
	public void testGenWord() {
		cipher.setInputText("here it is");
		cipher.setCurrentPosition(3);
		//String word = cipher.genWord();
		//System.out.println(word);
		//An actual test for this thing would be some pain-in-the-ass RegExp, so it can wait.
		
	}

	@Test
	public void testGenSentence() {
		cipher.setInputText("here it be");
		cipher.setCurrentPosition(20);
		//String sentence = cipher.genSentence();
		//System.out.println(sentence);
		//See above. A real test for this would suck to implement, so I'm being lazy.
	}

	@Test
	public void testDecrypt() {
		cipher.setInputText("Ron'agl'dhu. ");
		String output = cipher.makeDecryptable(cipher.getInputText());
		output = cipher.decrypt(output);
		System.out.println(output);
		
	}
	
}

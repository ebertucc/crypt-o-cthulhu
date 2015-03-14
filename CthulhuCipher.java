import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class CthulhuCipher {
	//VARIABLES
	
	/* This HashMap contains data used to encrypt the letters a-z and the single space character to R'lyehian.
	 * Thus, it needs to be size 27, and takes a char as a key and maps it to
	 * a String array, signifying the possible encodings for that character.
	 */
	private HashMap<Character, String[]> encoderMap = new HashMap<Character, String[]>(27) {
		private static final long serialVersionUID = 1L;
	{
		put('a', new String[] {"ag", "fth", "ch"});
		put('b', new String[] {"yogg", "geb", "ee"});
		put('c', new String[] {"shogg", "f", "oth"});
		put('d', new String[] {"shugg", "bug", "hai"});
		put('e', new String[] {"r", "agl", "eth"});
		put('f', new String[] {"fta", "eh", "hrii"});
		put('g', new String[] {"nog", "kn", "ek"});
		put('h', new String[] {"nyth", "ron", "tagh"});
		put('i', new String[] {"phle", "s", "gla"});
		put('j', new String[] {"t", "gil", "hup"});
		put('k', new String[] {"luh", "uhn", "trok"});
		put('l', new String[] {"om", "sit", "yem"});
		put('m', new String[] {"sol", "mor", "fra"});
		put('n', new String[] {"gth", "lm", "tro"});
		put('o', new String[] {"ptha", "sll", "do"});
		put('p', new String[] {"oo", "esh", "nak"});
		put('q', new String[] {"yar", "mg", "thg"});
		put('r', new String[] {"ii", "dgh", "lo"});
		put('s', new String[] {"mna", "hras", "ai"});
		put('t', new String[] {"fha", "c", "enf"});
		put('u', new String[] {"fhta", "gn", "nel"});
		put('v', new String[] {"irgh", "nil", "bogg"});
		put('w', new String[] {"throd", "shth", "yol"});
		put('x', new String[] {"sho", "goka", "ep"});
		put('y', new String[] {"ra", "sla", "dhu"});
		put('z', new String[] {"orr", "yet", "fogg"});
		put(' ', new String[] {"ya", "thu", "lhu"});
	}};
	
	// We'll use this String as our input text to be encrypted. We can get it from a Scanner or something.
	private String inputText;
	
	//This int keeps track of where we are in the input text so we can actually generate stuff.
	private int currentPosition = 0;
	
	//CONSTRUCTORS
	
	//METHODS
	
	//inputText accessor
	public String getInputText(){
		return inputText;
	}
	
	//inputText mutator
	public void setInputText(String newInput){
		inputText = newInput;
	}
	
	//currentPosition accessor
	public int getCurrentPosition(){
		return currentPosition;
	}
	
	//currentPosition mutator
	public void setCurrentPosition(int newPos){
		currentPosition = newPos;
	}
	
	//Converts plaintext into a format the cipher can handle, i.e. just lowercase letters and spaces.
	//Removes all other characters (including numbers! Watch out!)
	public String makeEncryptable(String s){
		s = s.toLowerCase();
		s = s.replaceAll("[^a-z ]", "");
		return s;
	}
	
	//Converts ciphertext into a format that can be decoded easily: removes ending punctuation and
	//turns apostrophes into spaces.
	public String makeDecryptable(String s){
		s = s.toLowerCase();
		s = s.replace('\'', ' ');
		s = s.replaceAll("[\\.\\?!]", "");
		return s;
	}
	
	/* Takes in a character and returns one of three morpheme Strings. Uses the encoderMap variable
	 * to find the appropriate keys and values.
	 */
	public String toMorpheme(char c){
		
		String morpheme = encoderMap.get(c)[new Random().nextInt(3)];
		return morpheme;
	}
	
	/* Generates a 1-3 morpheme-long word based on the current position in the input text.
	 * This chooses randomly whether the word is 1, 2, or 3 morphemes long. 2+ morpheme words
	 * have apostrophes in between the morphemes. This method also increments currentPosition
	 * by the number of morphemes of the generated word, and if there aren't enough characters
	 * left in the input text, this prevents any more morphemes from being generated.
	 */
	public String genWord(){
		int wordLength = new Random().nextInt(3) + 1;
		
		if(wordLength > inputText.length() - currentPosition)
			wordLength = inputText.length() - currentPosition;
			
		String word = "";
		
		for(int i = 0; i < wordLength; i++) {
			word+= toMorpheme(inputText.charAt(currentPosition));
			currentPosition++;
			
			if(i < wordLength - 1)
				word+= "'";
		}
		return word;
	}
	
	/*
	 * Generates a 1-6 word-long sentence. Capitalizes the first word and adds punctuation and whitespace.
	 * The logic for printing spaces might be improvable; remove the if-statement and then just delete the
	 * last char of the sentence once the loop ends (which will be a space). But whatever.
	 */
	public String genSentence(){
		int sentenceLength = new Random().nextInt(6) + 1;
		String sentence = "";
		
		for(int i = 0; i < sentenceLength; i++){
			sentence+= genWord();
			
			//Makes sure we don't add unnecessary whitespace to the end
			if(sentenceLength > inputText.length() - currentPosition)
				sentenceLength = inputText.length() - currentPosition;
			
			if(i < sentenceLength - 1)
				sentence+= " ";
		}
		sentence+= ". ";
		
		//Capitalizes the first letter. Even R'lyehians care about that :)
		sentence = sentence.substring(0,1).toUpperCase() + sentence.substring(1);
		return sentence;
	}
	
	public String decrypt(String s){
		String output = "";
		Scanner in = new Scanner(s);
		
		while(in.hasNext())
		{
			String currentToken = in.next();
			for(HashMap.Entry<Character, String[]> entry : encoderMap.entrySet()){
				for (int i = 0; i < 3; i++) {
					//Remember to use .equals() instead of == to compare two Strings...
					if (entry.getValue()[i].equals(currentToken)) {
						output+= entry.getKey();
					}
				}
			}
		}
		
		in.close();
		return output;
		
	}

}

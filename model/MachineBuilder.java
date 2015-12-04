
package model;
public class MachineBuilder {
	
	// Enigma machine parts
	private Reflector reflector;
	private int[] plugboard;
	private Rotor rightRotor;
	private Rotor middleRotor;
	private Rotor leftRotor;
	
	/*
	 * Rotor and reflctor wiring configuration: 
	 * http://enigmamuseum.com/rotwirg.htm
	 * https://en.wikipedia.org/wiki/Enigma_rotor_details
	 * 
	 * Decrypted Secrets: Methods and Maxims of Cryptology (p.124)
     * By Friedrich L. Bauer
	 */
	
	// Reflectors
		public static final String A = "EJMZALYXVBWFCRQUONTSPIKHGD";
		public static final String B = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
		public static final String C = "FVPJIAOYEDRZXWGCTKUQSBNMHL";
	
	// Rotors
	public static final String[] I = {"EKMFLGDQVZNTOWYHXUSPAIBRCJ", "Q"};
	public static final String[] II = {"AJDKSIRUXBLHWTMCQGZNPYFVOE", "E"};
	public static final String[] III = {"BDFHJLCPRTXVZNYEIWGAKMUSQO", "V"};
	public static final String[] IV = {"ESOVPZJAYQUIRHXLNFTGKDCMWB", "J"};
	public static final String[] V = {"VZBRGITYUPSDNHLXAWMJQOFECK", "Z"};
	
	
	
	/**
	 * Constructor to build the enigma machine from its parts (rotors and reflector)
	 * @param leftRotor
	 * @param middleRotor
	 * @param rightRotor
	 * @param reflector
	 */
	public MachineBuilder(String[] leftRotor, String[] middleRotor, String[] rightRotor, String reflector){
		
		//if a rotor is used more than once in the rotor configuration
				if (leftRotor.equals(middleRotor)||leftRotor.equals(rightRotor)||middleRotor.equals(rightRotor))
					throw new RuntimeException("A ROTOR CAN ONLY BE USED ONCE!");
	
		// Assign rotors and reflector
		this.leftRotor = new Rotor(leftRotor[0], leftRotor[1].charAt(0));
		this.middleRotor = new Rotor(middleRotor[0], middleRotor[1].charAt(0));
		this.rightRotor = new Rotor(rightRotor[0], rightRotor[1].charAt(0));
		this.reflector = new Reflector(reflector);
		
		// Construct the plugboard array
		plugboard = new int[26];
		
		// Initialize the plugboard connections
		initialisePlugboard();
	}
	
	
	
	
	
	
	/**
	 * Map the letter using the scrambling wiring of  the selected rotors and the plugboard
	 * @param input
	 * @return encoded input
	 */
	public char mapLetterThroughRotors(char plainLetter){
		
		/*
		 * Rotating the rotors before any encryption takes place
		 */
		
		// if  the middle rotor is at the notch position, rotate both middle rotor and left rotor (double stepping)
		if (middleRotor.getRotorNotch() == middleRotor.getRotorPosition()){
			leftRotor.rotate();
			middleRotor.rotate();
		}

		// if the right rotor is at notch position rotate the middle rotor
		if (rightRotor.getRotorNotch() == rightRotor.getRotorPosition())
			middleRotor.rotate();

		// Rotate the right rotor with every letter encrypted
		rightRotor.rotate();
		
		/*
		 * ***************************************************************************
		 */
		
		
		
		
		int staticInput = plainLetter - 'A';
		
		//  pass it through the plugboard
		if(plugboard[staticInput] != -1)
			staticInput = plugboard[staticInput];

		//  pass it through the rotors from right to left
		int rightRotorOutput = rightRotor.mapForward(staticInput);
		int middleRotorOutput = middleRotor.mapForward(rightRotorOutput);
		int leftRotorOutput = leftRotor.mapForward(middleRotorOutput);

		//  pass it through the reflector
		int reflectorOutput = reflector.reflectorMap(leftRotorOutput);

		//  pass it in a reverse way from the left rotor to the right rotor
		int leftRotorInput = leftRotor.mapReverse(reflectorOutput);
		int middleRotorInput = middleRotor.mapReverse(leftRotorInput);
		int rightRotorInput = rightRotor.mapReverse(middleRotorInput);
		
		//  pass it through the plugboard again
		if(plugboard[rightRotorInput] != -1)
			rightRotorInput = plugboard[rightRotorInput];
		
		// Finally, pass it through the static entry rotor and convert it from an integer into a letter
		return (char) (rightRotorInput + 'A');
	}
	
	/**
	 * Encrypt/decrypt the text passed 
	 * @param text
	 * @return encrypted  text
	 * */
	public String encrypt(String text){
		String encryptedText = "";
		for (int i=0; i < text.length(); i++){
			
			// check if the letter is upper case
			if(text.charAt(i) >= 'A' && text.charAt(i) <= 'Z')
				encryptedText += mapLetterThroughRotors(text.charAt(i));
			
			// if space or new line pass them as they are without encryption
			else if(text.charAt(i) == ' ' || text.charAt(i) == '\n')
				encryptedText += text.charAt(i);
			
			// ignore any other characters in the text and throw an exception if lower case letters are used
			else
				throw new RuntimeException("USE CAPITAL LETTERS ONLY!");
		}
		return encryptedText;
	}
	
	
	
	
	
	
	
	/**
	 * Right rotor getter
	 * @return right rotor
	 */
	public Rotor getRightRotor(){
		return rightRotor;
	}
	/**
	 * Middle rotor getter
	 * @return middle rotor
	 */
	public Rotor getMiddleRotor(){
		return middleRotor;
	}
	
	/**
	 * Left rotor getter
	 * @return left rotor
	 */
	public Rotor getLeftRotor(){
		return leftRotor;
	}
	
	/**
	 * Reflector getter 
	 * @return reflector
	 */
	public Reflector getReflector(){
		return reflector;
	}
	

	/**
	 * Reset the rotors to the ground position A-A-A
	 */
	public void resetRotors(){
		leftRotor.reset();
		middleRotor.reset();
		rightRotor.reset();
	}
	
	
	/**
	 * initialize the connections between letters
	 */
	public void initialisePlugboard(){
		for (int socket = 0; socket < 26; socket++)
			this.plugboard[socket] = -1;
	}
	
	/**
	 * Plugboard setter to pair two given letter a and b
	 * @param plugboard
	 */
	public void plugboardPair(char a, char b){
		
		if (a == b)// if the same letter is connected to itself
			throw new RuntimeException("A LETTER CANNOT BE CONNECTED TO ITSELF IN THE PLUGBOARD!");
		if (isPaired(a) || isPaired(b))//if a letter is connected to more than one letter
			throw new RuntimeException("A LETTER CANNOT BE CONNECTED TO MORE THAN ONE OTHER LETTER!");
		
		this.plugboard[ a - 'A' ] = b - 'A';
		this.plugboard[ b - 'A' ] = a - 'A';
	}
	
	/**
	 * Checks if a letter is connected to another letter in the plugboard
	 * @param c
	 * @return boolean
	 */
	public boolean isPaired(char c){
		return plugboard[c - 'A'] != -1;
	}
	
	/**
	 * Get the letter connected to letter  in the plugboard
	 * @param letter
	 * @return int
	 */
	public int getPairOf(int letter){
		return this.plugboard[letter];
	}
	
	

}

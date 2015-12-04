
package model;
public class Rotor {
	
	private int rotorPosition;
	private int ringPosition;
	
	private	int rotorForwardArray[] = new int[26];
	private int rotorReverseArray[] = new int[26];
	
	private char notch;
	private int displacement;
	
	/**
	 * The rotor constructor
	 * @param rotor
	 * @param notch
	 */
	public Rotor(String rotor, char notch){
		buildIntegerRotorArrays(new String[]{rotor, Character.toString(notch)});
	}

	
	
	/**
	 * Pass the index of the letter entering the rotor from the right
	 * @param letterPosition
	 * @return index
	 */
	public int mapForward(int letterPosition){
		//calculating the displacement between the rotor and the ring
		int rotorRingDiff = rotorPosition >= ringPosition ? rotorPosition - ringPosition : 26 - ringPosition + rotorPosition;
		
		//return the mapped  letter, taking into account the the shifts due to rotor movement and ring-rotor difference
		return (letterPosition + rotorForwardArray[ (letterPosition + displacement + rotorRingDiff) % 26]) % 26; 
	}
	
	/**
	 * Pass the index of the letter entering the rotor from the left
	 * @param letterPosition
	 * @return index
	 */
	public int mapReverse(int letterPosition){
		//calculating the displacement between the rotor and the ring
		int rotorRingDiff = rotorPosition >= ringPosition ? rotorPosition - ringPosition : 26 - ringPosition + rotorPosition;
		
		//return the mapped letter, taking into account the the shifts due to rotor movement and ring-rotor difference
		//the minus sign is because the rotor shift is in the opposite direction (left side) this time, 
		//since the current is flowing in the reverse way
		int positionDisplacement = letterPosition - rotorReverseArray[ (letterPosition + displacement + rotorRingDiff) % 26];
		
		//if the resulted positionDisplacement is negative, we need to compensate for it using the ring arithmetic
	    //to calculate the real letter index in the alphabet array
		return positionDisplacement > 0 ? (positionDisplacement % 26) : ( 26 + positionDisplacement) % 26; 
	}
	
	
	/**
	 *  rotor position getter
	 * @return rotor position
	 */
	public char getRotorPosition(){
		return (char) ('A' + (rotorPosition + displacement) % 26);
	}
	
	/**
	 * ring position getter
	 * @return ring position
	 */
	public char getRingPosition(){
		return (char) ('A' + (ringPosition + displacement) % 26);
	}
	
	/**
	 * Rotor notch getter
	 * @return notch
	 */
	public char getRotorNotch(){
		return notch;
	}
	
	
	
	
	/**
	 * Set the rotor  position and reset the displacement
	 * @param c
	 */
	public void setRotorPosition(char c){
		if(c < 'A' || c > 'Z')
			throw new RuntimeException("CAPITAL LETERS ONLY!");
		rotorPosition = c - 'A';
		displacement = 0;
	}
	
	/**
	 *  ring position setter
	 * @param c
	 */
	public void setRingPosition(char c){
		if(c < 'A' || c > 'Z')
			throw new RuntimeException("CAPITAL LETERS ONLY!");
		ringPosition = c - 'A';
	}
	
	/**
	 * Rotate the rotor
	 */
	public void rotate(){
		displacement = (displacement + 1) % 26;
	}
	
	/**
	 * Build rotor arrays
	 *
	 * @param rotorStringArray
	 */
	public void buildIntegerRotorArrays(String[] rotorStringArray){
		this.notch = rotorStringArray[1].charAt(0);
		for (int i = 0; i < 26; i++){
			int letter = (char) ('A' + i);//Letter in the alphabet
			int mappedOutput = rotorStringArray[0].charAt(i);//Mapping of that letter in the rotor configuration  array
			
			//each entry of the rotorForwardArray represents the distance by which the letter is shifted by the
			//rotor configuration  array when entering the rotor from the right side (normal current flow)
			rotorForwardArray[i] = letter < mappedOutput ? mappedOutput - letter : (26 - (letter - mappedOutput)) % 26;
			
			//each entry of the rotorReverseArray represents the distance by which the letter is shifted by 
			//rotor cofiguration  array when entering the rotor from the left side (reverse current flow)
			rotorReverseArray[ (i + rotorForwardArray[i]) % 26] = rotorForwardArray[i];
		}
	}
	
	/**
	 * Reset rotor position to the initial position A
	 */
	public void reset(){
		displacement = 0;
	}
	
	
}

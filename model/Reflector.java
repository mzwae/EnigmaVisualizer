
package model;
public class Reflector {
	
	// build the reflector array
	private int reflectorArray[] = new int[26];
	
	/**
	 * Reflector Constructor
	 * @param reflector
	 */
	public Reflector(String reflector){
		buildIntegerReflectorArray(reflector);
	}
	
	/**
	 * Build the reflector array
	 * @param reflector
	 */
	public void buildIntegerReflectorArray(String reflector){
		for (int i = 0; i < 26; i++){
			int letter = (char) ('A' + i);//letter in the alphabet
			int mappedOutput = reflector.charAt(i);//mapping of that letter in the reflector configuration array
			
			//each entry of the reflectorArray represents the distance by which the letter is shifted by the
			//reflector configuration  array when entering the reflector 
			reflectorArray[i] = letter < mappedOutput ? mappedOutput - letter : (26 - (letter - mappedOutput)) % 26;
		}
	}
	
	/**
	 * Map the input letter to the letter in reflector array
	 * @param position
	 * @return the index of the letter in the alphabet array where 0 is A and 25 is Z
	 */
	public int reflectorMap(int position){
		//return the mapped  letter, taking into account the the shift caused by the reflector configuration array
		return (position + reflectorArray[position]) % 26;
	}
	

}

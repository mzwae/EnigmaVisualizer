package test;

import static org.junit.Assert.*;
import model.Reflector;

import org.junit.Test;

public class ReflectorTest {
	String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z" };
	
	Reflector reflector;
	
	@Test
	public void testReflectorMapA() {
		String A = "EJMZALYXVBWFCRQUONTSPIKHGD";
		reflector = new Reflector(A);
		for (int i = 0; i < alphabet.length; i++) {
			int output = reflector.reflectorMap(i);
			int expected = A.charAt(i) - 'A';
			assertEquals(expected, output);
	
		}
		
	}
	
	@Test
	public void testReflectorMapB() {
		String B = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
		 reflector = new Reflector(B);
		for (int i = 0; i < alphabet.length; i++) {
			int output = reflector.reflectorMap(i);
			
			int expected = B.charAt(i) - 'A';
			assertEquals(expected, output);

		}
	}
	
	@Test
	public void testReflectorMapC() {
		String C = "FVPJIAOYEDRZXWGCTKUQSBNMHL";		
		reflector = new Reflector(C);
		for (int i = 0; i < alphabet.length; i++) {
			int output = reflector.reflectorMap(i);
			
			int expected = C.charAt(i) - 'A';
			assertEquals(expected, output);

		}
	}


}

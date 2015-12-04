
package test;

import static org.junit.Assert.*;
import model.MachineBuilder;
import model.Rotor;

import org.junit.Test;

public class MachineBuilderTest {

	MachineBuilder enigmaMachine;

	@Test
	public void testPlugboardPair13Sockets() {
		enigmaMachine = new MachineBuilder(MachineBuilder.V, MachineBuilder.IV, MachineBuilder.I, MachineBuilder.B);

		enigmaMachine.getLeftRotor().setRotorPosition('X');
		enigmaMachine.getMiddleRotor().setRotorPosition('Y');
		enigmaMachine.getRightRotor().setRotorPosition('Z');

		enigmaMachine.getLeftRotor().setRingPosition('R');
		enigmaMachine.getMiddleRotor().setRingPosition('O');
		enigmaMachine.getRightRotor().setRingPosition('F');

		enigmaMachine.plugboardPair('A', 'B');
		enigmaMachine.plugboardPair('C', 'D');
		enigmaMachine.plugboardPair('E', 'F');
		enigmaMachine.plugboardPair('G', 'H');
		enigmaMachine.plugboardPair('I', 'J');
		enigmaMachine.plugboardPair('K', 'L');
		enigmaMachine.plugboardPair('M', 'N');
		enigmaMachine.plugboardPair('O', 'P');
		enigmaMachine.plugboardPair('Q', 'R');
		enigmaMachine.plugboardPair('S', 'T');
		enigmaMachine.plugboardPair('U', 'V');
		enigmaMachine.plugboardPair('W', 'X');
		enigmaMachine.plugboardPair('Y', 'Z');

		String actual = enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String expected = "ZPVVXKPJLVMZKBJHIGOJRGOVHL";
		assertEquals(expected, actual);

	}
	@Test
	public void testPlugboardPair10Sockets() {

		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.II, MachineBuilder.III, MachineBuilder.B);

		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('A');
		enigmaMachine.getRightRotor().setRotorPosition('A');

		enigmaMachine.getLeftRotor().setRingPosition('A');
		enigmaMachine.getMiddleRotor().setRingPosition('A');
		enigmaMachine.getRightRotor().setRingPosition('A');

		enigmaMachine.plugboardPair('A', 'B');
		enigmaMachine.plugboardPair('C', 'D');
		enigmaMachine.plugboardPair('E', 'F');
		enigmaMachine.plugboardPair('G', 'H');
		enigmaMachine.plugboardPair('I', 'J');
		enigmaMachine.plugboardPair('K', 'L');
		enigmaMachine.plugboardPair('M', 'N');
		enigmaMachine.plugboardPair('O', 'P');
		enigmaMachine.plugboardPair('Q', 'R');
		enigmaMachine.plugboardPair('S', 'T');

		String output = enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String expected = "BCIAHMSKJUPDSQCVOGHSSMDENF";
		assertEquals(expected, output);
	}

	@Test
	public void testPlugboardPair5Sockets() {
		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.II, MachineBuilder.III, MachineBuilder.B);

		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('A');
		enigmaMachine.getRightRotor().setRotorPosition('A');

		enigmaMachine.getLeftRotor().setRingPosition('A');
		enigmaMachine.getMiddleRotor().setRingPosition('A');
		enigmaMachine.getRightRotor().setRingPosition('A');

		enigmaMachine.plugboardPair('Y', 'Z');
		enigmaMachine.plugboardPair('W', 'X');
		enigmaMachine.plugboardPair('U', 'V');
		enigmaMachine.plugboardPair('S', 'T');
		enigmaMachine.plugboardPair('Q', 'R');

		String output = enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String expected = "BJELQRYUJXAQWTNBPHGSCAPSHL";
		assertEquals(expected, output);

	}

	@Test
	public void testPlugboardPair3Sockets() {
		enigmaMachine = new MachineBuilder(MachineBuilder.IV, MachineBuilder.II, MachineBuilder.V, MachineBuilder.C);

		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('B');
		enigmaMachine.getRightRotor().setRotorPosition('C');

		enigmaMachine.getLeftRotor().setRingPosition('D');
		enigmaMachine.getMiddleRotor().setRingPosition('E');
		enigmaMachine.getRightRotor().setRingPosition('F');

		enigmaMachine.plugboardPair('A', 'Z');
		enigmaMachine.plugboardPair('B', 'Y');
		enigmaMachine.plugboardPair('C', 'X');


		String output = enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String expected = "MAGWVTEBNHUCWYYBLQDNKQYDGH";
		assertEquals(expected, output);

	}

	@Test
	public void testPlugboardPair4Sockets() {
		enigmaMachine = new MachineBuilder(MachineBuilder.V, MachineBuilder.IV, MachineBuilder.I, MachineBuilder.B);

		enigmaMachine.getLeftRotor().setRotorPosition('X');
		enigmaMachine.getMiddleRotor().setRotorPosition('Y');
		enigmaMachine.getRightRotor().setRotorPosition('Z');

		enigmaMachine.getLeftRotor().setRingPosition('R');
		enigmaMachine.getMiddleRotor().setRingPosition('O');
		enigmaMachine.getRightRotor().setRingPosition('F');

		enigmaMachine.plugboardPair('C', 'A');
		enigmaMachine.plugboardPair('D', 'R');
		enigmaMachine.plugboardPair('S', 'E');
		enigmaMachine.plugboardPair('Q', 'W');


		String actual = enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String expected = "NGJRUDXXLTFTEEVANSQCIDYLOJ";
		assertEquals(expected, actual);

	}

	@Test
	public void testPlugboardPair6Sockets() {
		enigmaMachine = new MachineBuilder(MachineBuilder.IV, MachineBuilder.I, MachineBuilder.II, MachineBuilder.B);

		enigmaMachine.getLeftRotor().setRotorPosition('X');
		enigmaMachine.getMiddleRotor().setRotorPosition('Y');
		enigmaMachine.getRightRotor().setRotorPosition('Z');

		enigmaMachine.getLeftRotor().setRingPosition('R');
		enigmaMachine.getMiddleRotor().setRingPosition('O');
		enigmaMachine.getRightRotor().setRingPosition('F');

		enigmaMachine.plugboardPair('C', 'A');
		enigmaMachine.plugboardPair('D', 'R');
		enigmaMachine.plugboardPair('S', 'E');
		enigmaMachine.plugboardPair('Q', 'W');
		enigmaMachine.plugboardPair('B', 'F');
		enigmaMachine.plugboardPair('T', 'M');

		String actual = enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String expected = "CRDWDYURWWWNEVRUHTWVCYKURT";
		assertEquals(expected, actual);

	}

	@Test
	public void testPlugboardPair7Sockets() {
		enigmaMachine = new MachineBuilder(MachineBuilder.IV, MachineBuilder.I, MachineBuilder.II, MachineBuilder.B);

		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('B');
		enigmaMachine.getRightRotor().setRotorPosition('C');

		enigmaMachine.getLeftRotor().setRingPosition('D');
		enigmaMachine.getMiddleRotor().setRingPosition('E');
		enigmaMachine.getRightRotor().setRingPosition('F');

		enigmaMachine.plugboardPair('C', 'A');
		enigmaMachine.plugboardPair('D', 'R');
		enigmaMachine.plugboardPair('S', 'E');
		enigmaMachine.plugboardPair('Q', 'W');
		enigmaMachine.plugboardPair('B', 'F');
		enigmaMachine.plugboardPair('T', 'M');
		enigmaMachine.plugboardPair('Z', 'K');

		String actual = enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String expected = "QMMUNITMTOTGYFCYCNYDMKIIMC";
		assertEquals(expected, actual);

	}

	@Test
	public void testPlugboardPair8Sockets() {
		enigmaMachine = new MachineBuilder(MachineBuilder.IV, MachineBuilder.I, MachineBuilder.II, MachineBuilder.B);

		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('B');
		enigmaMachine.getRightRotor().setRotorPosition('C');

		enigmaMachine.getLeftRotor().setRingPosition('D');
		enigmaMachine.getMiddleRotor().setRingPosition('E');
		enigmaMachine.getRightRotor().setRingPosition('F');

		enigmaMachine.plugboardPair('C', 'A');
		enigmaMachine.plugboardPair('D', 'R');
		enigmaMachine.plugboardPair('S', 'E');
		enigmaMachine.plugboardPair('Q', 'W');
		enigmaMachine.plugboardPair('B', 'F');
		enigmaMachine.plugboardPair('T', 'M');
		enigmaMachine.plugboardPair('Z', 'K');
		enigmaMachine.plugboardPair('Y', 'U');

		String actual = enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String expected = "QMMYNITMTOTGUFCUCNUDAKIIEC";
		assertEquals(expected, actual);

	}

	@Test
	public void testPlugboardPairNoSockets() {
		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.II, MachineBuilder.III, MachineBuilder.B);

		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('A');
		enigmaMachine.getRightRotor().setRotorPosition('A');

		enigmaMachine.getLeftRotor().setRingPosition('A');
		enigmaMachine.getMiddleRotor().setRingPosition('A');
		enigmaMachine.getRightRotor().setRingPosition('A');
		String output = enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String expected = "BJELRQZVJWARXSNBXORSTNCFME";
		assertEquals(expected, output);
	}
	
	
	@Test(expected = Exception.class)  
	public void testPlugboardPairSameLetterError() {
		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.II, MachineBuilder.III, MachineBuilder.B);

		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('A');
		enigmaMachine.getRightRotor().setRotorPosition('A');

		enigmaMachine.getLeftRotor().setRingPosition('A');
		enigmaMachine.getMiddleRotor().setRingPosition('A');
		enigmaMachine.getRightRotor().setRingPosition('A');
		enigmaMachine.plugboardPair('A', 'A');
	}
	
	@Test(expected = Exception.class)  
	public void testPlugboardPairLetterMoreThanOnceError() {
		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.II, MachineBuilder.III, MachineBuilder.B);

		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('A');
		enigmaMachine.getRightRotor().setRotorPosition('A');

		enigmaMachine.getLeftRotor().setRingPosition('A');
		enigmaMachine.getMiddleRotor().setRingPosition('A');
		enigmaMachine.getRightRotor().setRingPosition('A');
		enigmaMachine.plugboardPair('A', 'C');
		enigmaMachine.plugboardPair('A', 'B');
	}
	
	@Test(expected = Exception.class)  
	public void testRotorConfigSameRotorMoreThanOnceError() {
		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.I, MachineBuilder.III, MachineBuilder.B);

		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('A');
		enigmaMachine.getRightRotor().setRotorPosition('A');

		enigmaMachine.getLeftRotor().setRingPosition('A');
		enigmaMachine.getMiddleRotor().setRingPosition('A');
		enigmaMachine.getRightRotor().setRingPosition('A');
		
	}
	
	
	@Test(expected = Exception.class)  
	public void testUsingLowerCaseLetterError() {
		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.II, MachineBuilder.III, MachineBuilder.B);

		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('A');
		enigmaMachine.getRightRotor().setRotorPosition('A');

		enigmaMachine.getLeftRotor().setRingPosition('A');
		enigmaMachine.getMiddleRotor().setRingPosition('A');
		enigmaMachine.getRightRotor().setRingPosition('A');
		
		enigmaMachine.encrypt("a");
		
	}
	

	@Test
	public void testRotorConfigIaIIbIIIcReB() {
		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.II, MachineBuilder.III, MachineBuilder.B);
		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('B');
		enigmaMachine.getRightRotor().setRotorPosition('C');

		enigmaMachine.getLeftRotor().setRingPosition('X');
		enigmaMachine.getMiddleRotor().setRingPosition('Y');
		enigmaMachine.getRightRotor().setRingPosition('Z');
		String output = enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String expected = "VLFFZNSTRCCUKMSGOICXNWZJVL";
		assertEquals(expected, output);
	}


	@Test
	public void testRotorConfigVaIVbIcReC() {
		enigmaMachine = new MachineBuilder(MachineBuilder.V, MachineBuilder.IV, MachineBuilder.I, MachineBuilder.C);
		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('B');
		enigmaMachine.getRightRotor().setRotorPosition('C');

		enigmaMachine.getLeftRotor().setRingPosition('X');
		enigmaMachine.getMiddleRotor().setRingPosition('Y');
		enigmaMachine.getRightRotor().setRingPosition('Z');
		String output = enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String expected = "QTIUWNPECWGAASGKFGZLLMRHKR";
		assertEquals(expected, output);
	}

	@Test
	public void testRotorConfigIeIIdIIIvReB() {
		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.II, MachineBuilder.III, MachineBuilder.B);
		enigmaMachine.getLeftRotor().setRotorPosition('E');
		enigmaMachine.getMiddleRotor().setRotorPosition('D');
		enigmaMachine.getRightRotor().setRotorPosition('V');

		enigmaMachine.getLeftRotor().setRingPosition('R');
		enigmaMachine.getMiddleRotor().setRingPosition('S');
		enigmaMachine.getRightRotor().setRingPosition('T');
		String output = enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String expected = "NFATQSVJMRFQEHUHGHTIOJEBDD";
		assertEquals(expected, output);
	}


	@Test
	public void testRotorConfigIwVxIIyReC() {
		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.V, MachineBuilder.II, MachineBuilder.C);
		enigmaMachine.getLeftRotor().setRotorPosition('W');
		enigmaMachine.getMiddleRotor().setRotorPosition('X');
		enigmaMachine.getRightRotor().setRotorPosition('Y');

		enigmaMachine.getLeftRotor().setRingPosition('E');
		enigmaMachine.getMiddleRotor().setRingPosition('F');
		enigmaMachine.getRightRotor().setRingPosition('G');
		String output = enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String expected = "IODNKZZYHFVHHZYWWMGCHHSIUW";
		assertEquals(expected, output);
	}

	@Test
	public void testRotorConfigIIIrVsIVtReC() {
		enigmaMachine = new MachineBuilder(MachineBuilder.III, MachineBuilder.V, MachineBuilder.IV, MachineBuilder.C);
		enigmaMachine.getLeftRotor().setRotorPosition('R');
		enigmaMachine.getMiddleRotor().setRotorPosition('S');
		enigmaMachine.getRightRotor().setRotorPosition('T');

		enigmaMachine.getLeftRotor().setRingPosition('M');
		enigmaMachine.getMiddleRotor().setRingPosition('N');
		enigmaMachine.getRightRotor().setRingPosition('O');
		String output = enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String expected = "ZWUEQTOYDYXGYDEIYIEBONLCVR";
		assertEquals(expected, output);
	}

	@Test
	public void testRotorConfigIVuVvIwReB() {
		enigmaMachine = new MachineBuilder(MachineBuilder.IV, MachineBuilder.V, MachineBuilder.I, MachineBuilder.B);
		enigmaMachine.getLeftRotor().setRotorPosition('U');
		enigmaMachine.getMiddleRotor().setRotorPosition('V');
		enigmaMachine.getRightRotor().setRotorPosition('W');

		enigmaMachine.getLeftRotor().setRingPosition('C');
		enigmaMachine.getMiddleRotor().setRingPosition('D');
		enigmaMachine.getRightRotor().setRingPosition('E');
		String output = enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String expected = "CYGEGKOGSWMNRYQCLMUDOEFSXI";
		assertEquals(expected, output);
	}

	@Test
	public void testRotorConfigIIIfVgIIhReC() {
		enigmaMachine = new MachineBuilder(MachineBuilder.III, MachineBuilder.V, MachineBuilder.II, MachineBuilder.C);
		enigmaMachine.getLeftRotor().setRotorPosition('F');
		enigmaMachine.getMiddleRotor().setRotorPosition('G');
		enigmaMachine.getRightRotor().setRotorPosition('H');

		enigmaMachine.getLeftRotor().setRingPosition('I');
		enigmaMachine.getMiddleRotor().setRingPosition('J');
		enigmaMachine.getRightRotor().setRingPosition('K');
		String output = enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String expected = "TFXSMZNBWAZIJGEKBXOPZOCKHO";
		assertEquals(expected, output);
	}

	@Test
	public void testRotorConfigVlIImIVnReB() {
		enigmaMachine = new MachineBuilder(MachineBuilder.V, MachineBuilder.II, MachineBuilder.IV, MachineBuilder.B);
		enigmaMachine.getLeftRotor().setRotorPosition('L');
		enigmaMachine.getMiddleRotor().setRotorPosition('M');
		enigmaMachine.getRightRotor().setRotorPosition('N');

		enigmaMachine.getLeftRotor().setRingPosition('O');
		enigmaMachine.getMiddleRotor().setRingPosition('P');
		enigmaMachine.getRightRotor().setRingPosition('Q');
		String output = enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String expected = "YQHYKCNPCSRWPPJIMOLLZTANZP";
		assertEquals(expected, output);
	}

	@Test
	public void testRotorConfigIlIImIVnReA() {
		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.III, MachineBuilder.IV, MachineBuilder.A);
		enigmaMachine.getLeftRotor().setRotorPosition('L');
		enigmaMachine.getMiddleRotor().setRotorPosition('M');
		enigmaMachine.getRightRotor().setRotorPosition('N');

		enigmaMachine.getLeftRotor().setRingPosition('O');
		enigmaMachine.getMiddleRotor().setRingPosition('P');
		enigmaMachine.getRightRotor().setRingPosition('Q');
		String output = enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String expected = "BAMAKVRKELSMDCUAXURNTZRVIY";
		assertEquals(expected, output);
	}

	@Test
	public void testRotorConfigVuIIvIVwReA() {
		enigmaMachine = new MachineBuilder(MachineBuilder.V, MachineBuilder.II, MachineBuilder.IV, MachineBuilder.A);
		enigmaMachine.getLeftRotor().setRotorPosition('U');
		enigmaMachine.getMiddleRotor().setRotorPosition('V');
		enigmaMachine.getRightRotor().setRotorPosition('W');

		enigmaMachine.getLeftRotor().setRingPosition('X');
		enigmaMachine.getMiddleRotor().setRingPosition('Y');
		enigmaMachine.getRightRotor().setRingPosition('Z');
		String output = enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String expected = "QUWULSFYHRBYDOXJHWILWWIBMB";
		assertEquals(expected, output);
	}

	@Test
	public void testmapLetterThroughRotorsIaIIaIIIaReBLetterA() {
		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.II, MachineBuilder.III, MachineBuilder.B);

		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('A');
		enigmaMachine.getRightRotor().setRotorPosition('A');

		enigmaMachine.getLeftRotor().setRingPosition('A');
		enigmaMachine.getMiddleRotor().setRingPosition('A');
		enigmaMachine.getRightRotor().setRingPosition('A');

		char actual = enigmaMachine.mapLetterThroughRotors('A');
		char expected = 'B';
		assertEquals(expected, actual);

	}


	@Test
	public void testmapLetterThroughRotorsIaIIaIIIaReBLetterZ() {
		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.II, MachineBuilder.III, MachineBuilder.B);

		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('A');
		enigmaMachine.getRightRotor().setRotorPosition('A');

		enigmaMachine.getLeftRotor().setRingPosition('A');
		enigmaMachine.getMiddleRotor().setRingPosition('A');
		enigmaMachine.getRightRotor().setRingPosition('A');

		char actual = enigmaMachine.mapLetterThroughRotors('Z');
		char expected = 'U';
		assertEquals(expected, actual);

	}


	@Test
	public void testEncryptDecryptConfigVuIIvIVwReA() {
		enigmaMachine = new MachineBuilder(MachineBuilder.V, MachineBuilder.II, MachineBuilder.IV, MachineBuilder.A);
		String actual = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		enigmaMachine.getLeftRotor().setRotorPosition('U');
		enigmaMachine.getMiddleRotor().setRotorPosition('V');
		enigmaMachine.getRightRotor().setRotorPosition('W');

		enigmaMachine.getLeftRotor().setRingPosition('X');
		enigmaMachine.getMiddleRotor().setRingPosition('Y');
		enigmaMachine.getRightRotor().setRingPosition('Z');


		String encryptedMessage = enigmaMachine.encrypt(actual);

		enigmaMachine.getLeftRotor().setRotorPosition('U');
		enigmaMachine.getMiddleRotor().setRotorPosition('V');
		enigmaMachine.getRightRotor().setRotorPosition('W');

		enigmaMachine.getLeftRotor().setRingPosition('X');
		enigmaMachine.getMiddleRotor().setRingPosition('Y');
		enigmaMachine.getRightRotor().setRingPosition('Z');


		String expected = enigmaMachine.encrypt(encryptedMessage);;
		assertEquals(expected, actual);
	}

	@Test
	public void testEncryptDecryptConfigIeIVdIIvReB() {
		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.IV, MachineBuilder.II, MachineBuilder.B);
		String actual = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		enigmaMachine.getLeftRotor().setRotorPosition('E');
		enigmaMachine.getMiddleRotor().setRotorPosition('D');
		enigmaMachine.getRightRotor().setRotorPosition('V');

		enigmaMachine.getLeftRotor().setRingPosition('A');
		enigmaMachine.getMiddleRotor().setRingPosition('A');
		enigmaMachine.getRightRotor().setRingPosition('A');


		String encryptedMessage = enigmaMachine.encrypt(actual);

		enigmaMachine.getLeftRotor().setRotorPosition('E');
		enigmaMachine.getMiddleRotor().setRotorPosition('D');
		enigmaMachine.getRightRotor().setRotorPosition('V');

		enigmaMachine.getLeftRotor().setRingPosition('A');
		enigmaMachine.getMiddleRotor().setRingPosition('A');
		enigmaMachine.getRightRotor().setRingPosition('A');


		String expected = enigmaMachine.encrypt(encryptedMessage);;
		assertEquals(expected, actual);
	}


	@Test
	public void testEncryptDecryptConfigIVrIsVtReC() {
		enigmaMachine = new MachineBuilder(MachineBuilder.IV, MachineBuilder.I, MachineBuilder.V, MachineBuilder.C);
		String actual = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		enigmaMachine.getLeftRotor().setRotorPosition('R');
		enigmaMachine.getMiddleRotor().setRotorPosition('S');
		enigmaMachine.getRightRotor().setRotorPosition('T');

		enigmaMachine.getLeftRotor().setRingPosition('M');
		enigmaMachine.getMiddleRotor().setRingPosition('N');
		enigmaMachine.getRightRotor().setRingPosition('O');


		String encryptedMessage = enigmaMachine.encrypt(actual);

		enigmaMachine.getLeftRotor().setRotorPosition('R');
		enigmaMachine.getMiddleRotor().setRotorPosition('S');
		enigmaMachine.getRightRotor().setRotorPosition('T');

		enigmaMachine.getLeftRotor().setRingPosition('M');
		enigmaMachine.getMiddleRotor().setRingPosition('N');
		enigmaMachine.getRightRotor().setRingPosition('O');


		String expected = enigmaMachine.encrypt(encryptedMessage);;
		assertEquals(expected, actual);
	}

	@Test
	public void testEncryptDecryptConfigIcIIIoVlReA() {
		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.III, MachineBuilder.V, MachineBuilder.A);
		String actual = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		enigmaMachine.getLeftRotor().setRotorPosition('C');
		enigmaMachine.getMiddleRotor().setRotorPosition('O');
		enigmaMachine.getRightRotor().setRotorPosition('L');

		enigmaMachine.getLeftRotor().setRingPosition('M');
		enigmaMachine.getMiddleRotor().setRingPosition('N');
		enigmaMachine.getRightRotor().setRingPosition('O');


		String encryptedMessage = enigmaMachine.encrypt(actual);

		enigmaMachine.getLeftRotor().setRotorPosition('C');
		enigmaMachine.getMiddleRotor().setRotorPosition('O');
		enigmaMachine.getRightRotor().setRotorPosition('L');

		enigmaMachine.getLeftRotor().setRingPosition('M');
		enigmaMachine.getMiddleRotor().setRingPosition('N');
		enigmaMachine.getRightRotor().setRingPosition('O');


		String expected = enigmaMachine.encrypt(encryptedMessage);;
		assertEquals(expected, actual);
	}

	@Test
	public void testEncryptDecryptConfigIaIIIsVzReB() {
		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.III, MachineBuilder.V, MachineBuilder.B);
		String actual = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('S');
		enigmaMachine.getRightRotor().setRotorPosition('Z');

		enigmaMachine.getLeftRotor().setRingPosition('D');
		enigmaMachine.getMiddleRotor().setRingPosition('R');
		enigmaMachine.getRightRotor().setRingPosition('F');


		String encryptedMessage = enigmaMachine.encrypt(actual);

		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('S');
		enigmaMachine.getRightRotor().setRotorPosition('Z');

		enigmaMachine.getLeftRotor().setRingPosition('D');
		enigmaMachine.getMiddleRotor().setRingPosition('R');
		enigmaMachine.getRightRotor().setRingPosition('F');


		String expected = enigmaMachine.encrypt(encryptedMessage);;
		assertEquals(expected, actual);
	}








}

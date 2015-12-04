package test;

import static org.junit.Assert.*;
import model.MachineBuilder;
import model.Rotor;

import org.junit.Test;

public class RotorTest {
	Rotor rotor;
	MachineBuilder enigmaMachine;


	@Test
	public void testGetRotorPositionRight() {
		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.II, MachineBuilder.III, MachineBuilder.B);

		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('A');
		enigmaMachine.getRightRotor().setRotorPosition('A');

		enigmaMachine.getLeftRotor().setRingPosition('A');
		enigmaMachine.getMiddleRotor().setRingPosition('A');
		enigmaMachine.getRightRotor().setRingPosition('A');
		enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		char actual = enigmaMachine.getRightRotor().getRotorPosition();
		char expected = 'A';
		assertEquals(expected, actual);

	}

	@Test
	public void testGetRotorPositionMiddle() {
		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.II, MachineBuilder.III, MachineBuilder.B);

		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('A');
		enigmaMachine.getRightRotor().setRotorPosition('A');

		enigmaMachine.getLeftRotor().setRingPosition('A');
		enigmaMachine.getMiddleRotor().setRingPosition('A');
		enigmaMachine.getRightRotor().setRingPosition('A');
		enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		char actual = enigmaMachine.getMiddleRotor().getRotorPosition();
		char expected = 'B';
		assertEquals(expected, actual);

	}

	@Test
	public void testGetRotorPositionLeft() {
		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.II, MachineBuilder.III, MachineBuilder.B);

		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('A');
		enigmaMachine.getRightRotor().setRotorPosition('A');

		enigmaMachine.getLeftRotor().setRingPosition('A');
		enigmaMachine.getMiddleRotor().setRingPosition('A');
		enigmaMachine.getRightRotor().setRingPosition('A');
		enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		char actual = enigmaMachine.getLeftRotor().getRotorPosition();
		char expected = 'A';
		assertEquals(expected, actual);

	}


	@Test
	public void testGetRingPositionRight() {
		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.II, MachineBuilder.III, MachineBuilder.B);

		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('A');
		enigmaMachine.getRightRotor().setRotorPosition('A');

		enigmaMachine.getLeftRotor().setRingPosition('E');
		enigmaMachine.getMiddleRotor().setRingPosition('D');
		enigmaMachine.getRightRotor().setRingPosition('V');
		enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		char actual = enigmaMachine.getRightRotor().getRingPosition();
		char expected = 'V';
		assertEquals(expected, actual);

	}

	@Test
	public void testGetRingPositionMiddle() {
		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.II, MachineBuilder.III, MachineBuilder.B);

		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('A');
		enigmaMachine.getRightRotor().setRotorPosition('A');

		enigmaMachine.getLeftRotor().setRingPosition('E');
		enigmaMachine.getMiddleRotor().setRingPosition('D');
		enigmaMachine.getRightRotor().setRingPosition('V');
		enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		char actual = enigmaMachine.getMiddleRotor().getRingPosition();
		char expected = 'E';
		assertEquals(expected, actual);

	}

	@Test
	public void testGetRingPositionLeft() {
		enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.II, MachineBuilder.III, MachineBuilder.B);

		enigmaMachine.getLeftRotor().setRotorPosition('A');
		enigmaMachine.getMiddleRotor().setRotorPosition('A');
		enigmaMachine.getRightRotor().setRotorPosition('A');

		enigmaMachine.getLeftRotor().setRingPosition('E');
		enigmaMachine.getMiddleRotor().setRingPosition('D');
		enigmaMachine.getRightRotor().setRingPosition('V');
		enigmaMachine.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		char actual = enigmaMachine.getLeftRotor().getRingPosition();
		char expected = 'E';
		assertEquals(expected, actual);

	}


	@Test
	public void testRotate() {
		rotor  = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ",'Q');
		rotor.rotate();
		rotor.rotate();
		char actual = rotor.getRotorPosition();
		char expected = 'C';
		assertEquals(expected, actual);
	}

	@Test
	public void testGetRotorNotch() {
		rotor  = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ",'Q');

		char actual = rotor.getRotorNotch();
		char expected = 'Q';
		assertEquals(expected, actual);
	}

	@Test
	public void testMapForward() {
		rotor = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ",'Q');
		rotor.setRingPosition('E');
		rotor.setRotorPosition('S');
		int actual = rotor.mapForward(5);
		int expected = 1;
		assertEquals(expected, actual);
	}

	@Test
	public void testMapReverse() {
		rotor = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ",'Q');
		rotor.setRingPosition('E');
		rotor.setRotorPosition('S');
		int actual = rotor.mapReverse(5);
		int expected = 23;
		assertEquals(expected, actual);
	}
	
	





}

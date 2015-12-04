package main;

import model.MachineBuilder;




public class ConsoleTester {
	public static void main(String[] args) {
		
	//  build an enigma machine with the given rotors
			MachineBuilder enigmaMachine = new MachineBuilder(MachineBuilder.I, MachineBuilder.II, MachineBuilder.III, MachineBuilder.B);
			
			// set the initial rotor positions
			enigmaMachine.getLeftRotor().setRotorPosition('A');
			enigmaMachine.getMiddleRotor().setRotorPosition('A');
			enigmaMachine.getRightRotor().setRotorPosition('A');
			
			// set the rings initial positions
			enigmaMachine.getLeftRotor().setRingPosition('A');
			enigmaMachine.getMiddleRotor().setRingPosition('A');
			enigmaMachine.getRightRotor().setRingPosition('A');
			
			//set the plugboard
			enigmaMachine.plugboardPair('A', 'B');
			enigmaMachine.plugboardPair('Y', 'Z');
			
			//Encrypt/Decrypt : ONLY CAPITAL LETTERS ALLOWED
			System.out.println(enigmaMachine.encrypt("MONJAIE"));
		
	}
}

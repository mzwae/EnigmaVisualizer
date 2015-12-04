
package view.observers;

public interface RotorObserver {
	public void configureRotors(
			String[] leftRotor,
			String[] middleRotor, 
			String[] rightRotor, 
			char lRotorStartPosition, 
			char mRotorStartPosition, 
			char rRotorStartPosition, 
			char lRotorRing, 
			char mRotorRing, 
			char rRotorRing, 
			String reflector);
}

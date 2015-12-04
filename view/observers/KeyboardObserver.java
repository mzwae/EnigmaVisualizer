package view.observers;

public interface KeyboardObserver {
	public int buttonPressAction(char plainLetter);
	public void buttonReleaseAction(int encryptedLetter);
}

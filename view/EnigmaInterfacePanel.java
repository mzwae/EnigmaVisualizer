package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import view.observers.KeyboardObserver;
import view.observers.TypingObserver;
import model.MachineBuilder;

public class EnigmaInterfacePanel extends JPanel {

	private LampboardPanel lampboardPanel;
	private KeyboardPanel keyboardPanel;
	private JSplitPane splitPane;
	private MachineBuilder enigma;
	private TypingObserver typingObserver;

	public EnigmaInterfacePanel() {

		
	
		// Create the panels
		lampboardPanel = new LampboardPanel();
		keyboardPanel = new KeyboardPanel();
		// Set the Layout
		setLayout(new BorderLayout());

		// Create the split pane
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, lampboardPanel,
				keyboardPanel);
		splitPane.setBorder(null);
		splitPane.setContinuousLayout(true);
		splitPane.setEnabled(false);
		splitPane.setResizeWeight(0.50);
		splitPane.setDividerSize(0);

		// Set the keyboard observer
		setKeyboardObserver();

		// Add the split pane to the main panel
		add(splitPane, BorderLayout.CENTER);
	}

	/**
	 * Keyboard observer setter
	 */
	private void setKeyboardObserver() {
		keyboardPanel.setKeyboardObserver(new KeyboardObserver() {

			public int buttonPressAction(char plainLetter) {
				int encryptedLetter = enigma.encrypt(
						Character.toString(plainLetter)).charAt(0) - 'A';
				lampboardPanel.switchLampOn(encryptedLetter);
				typingObserver.typingActionPerformed();
				lampboardPanel.getOutputText().setText(
						lampboardPanel.getOutputText().getText()
								+ (char) (encryptedLetter + 'A'));

				return encryptedLetter;
			}

			public void buttonReleaseAction(int encryptedLetter) {
				lampboardPanel.switchLampOff(encryptedLetter);
			}

		});
	}

	/**
	 * Typing observer setter
	 * 
	 * @param typingObserver
	 */
	public void setTypingObserver(TypingObserver typingObserver) {
		this.typingObserver = typingObserver;
	}

	/**
	 * Construct the enigma machine from its parts
	 * 
	 * @param enigma
	 */
	public void buildEnigmaMachine(MachineBuilder enigma) {
		this.enigma = enigma;
	}

	/**
	 * Reset the panel
	 */
	public void resetPanel() {
		enigma.resetRotors();
		keyboardPanel.getInputText().setText("");
		lampboardPanel.getOutputText().setText("");
		typingObserver.typingActionPerformed();

	}
	


}
package controller;

import view.SplashScreen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import view.EnigmaInterfacePanel;
import view.MenuBar;
import view.PlugboardPanel;
import view.RotorPanel;
import view.TextPanel;
import view.VisualisationPanel;
import view.observers.MenuBarObserver;
import view.observers.RotorObserver;
import view.observers.TypingObserver;
import model.MachineBuilder;

public class GUIFrameBuilder extends JFrame {

	private TextPanel textPanel;
	private RotorPanel rotorPanel;
	private MachineBuilder enigma;
	private Dimension dimensions;
	private PlugboardPanel plugBoardPanel;
	private EnigmaInterfacePanel enigmaInterfacePanel;
	private MenuBar menuBar;
	private VisualisationPanel visualisationPanel;
	private SplashScreen splash;

	private final int KEYBOARD_WINDOW = 1;
	private final int TEXT_WINDOW = 2;
	private final int VISUALISATION_WINDOW = 3;
	private int currentWindow = KEYBOARD_WINDOW;

	public GUIFrameBuilder() {
		super("Military (M3) Enigma  Emulator V6.0");

		// Use the system window appearance defaults
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (Exception e) {
			e.printStackTrace();
		}
		setIconImage(readImageFromResources("enigmaIcon.png"));
		splash = new SplashScreen();

		// construct the enigma machine with the default settings
		enigma = new MachineBuilder(MachineBuilder.I, MachineBuilder.II,
				MachineBuilder.III, MachineBuilder.B);

		// Set the layout of the frame
		setLayout(new BorderLayout());
		splash.updateProgressBar(10);

		// Create Menu bar
		menuBar = new MenuBar();
		splash.updateProgressBar(20);

		// Set JMenu
		setJMenuBar(menuBar.getMenuBar());
		splash.updateProgressBar(30);

		// Create panels
		textPanel = new TextPanel();
		splash.updateProgressBar(40);

		rotorPanel = new RotorPanel();
		splash.updateProgressBar(50);

		plugBoardPanel = new PlugboardPanel();
		enigmaInterfacePanel = new EnigmaInterfacePanel();
		splash.updateProgressBar(60);

		visualisationPanel = new VisualisationPanel();

		// Pass the constructed enigma machine to the constructed panels
		textPanel.buildEnigmaMachine(enigma);
		splash.updateProgressBar(70);

		rotorPanel.buildEnigmaMachine(enigma);
		enigmaInterfacePanel.buildEnigmaMachine(enigma);
		visualisationPanel.buildEnigmaMachine(enigma);
		splash.updateProgressBar(80);

		// Add panels to the frame
		add(enigmaInterfacePanel, BorderLayout.CENTER);
		add(rotorPanel, BorderLayout.SOUTH);
		splash.updateProgressBar(90);

		add(plugBoardPanel, BorderLayout.NORTH);

		// Set the rotor Observer
		setRotorObserver();

		// Set the input text observer
		setTypingObserver();

		// Set the keyboard observer
		setEnigmaInterfaceObserver();

		// Set the visualisatin panel observer
		setVisualisationObserver();

		// Set the menu bar observer
		setMenuObserver();

		splash.updateProgressBar(100);

		// Frame settings
		setVisible(true);
		dimensions = new Dimension(1300, 950);
		setPreferredSize(dimensions);
		setMinimumSize(dimensions);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);// center window on the screen
		textPanel.requestFocusForInputTextArea();
		splash.dispose();

	}

	/**
	 * Rotor observer setter
	 */
	private void setRotorObserver() {

		rotorPanel.setRotorObserver(new RotorObserver() {
			public void configureRotors(String[] leftRotor,
					String[] middleRotor, String[] rightRotor,
					char lRotorStartPosition, char mRotorStartPosition,
					char rRotorStartPosition, char lRotorRing, char mRotorRing,
					char rRotorRing, String reflector) {

				// Configure the enigma machine
				enigma.getLeftRotor().buildIntegerRotorArrays(leftRotor);
				enigma.getMiddleRotor().buildIntegerRotorArrays(middleRotor);
				enigma.getRightRotor().buildIntegerRotorArrays(rightRotor);

				enigma.getLeftRotor().setRotorPosition(lRotorStartPosition);
				enigma.getMiddleRotor().setRotorPosition(mRotorStartPosition);
				enigma.getRightRotor().setRotorPosition(rRotorStartPosition);

				enigma.getLeftRotor().setRingPosition(lRotorRing);
				enigma.getMiddleRotor().setRingPosition(mRotorRing);
				enigma.getRightRotor().setRingPosition(rRotorRing);

				enigma.getReflector().buildIntegerReflectorArray(reflector);

				// Connect paired letters
				pluginSockets();

				// Redraw enigma wires
				if (currentWindow == VISUALISATION_WINDOW) {
					visualisationPanel.refreshMappingPanel();
				}
				// Refresh text area panel
				if (currentWindow == TEXT_WINDOW) {
					textPanel.refreshTextArea();
				}

				// Refresh keyboard textfields
				if (currentWindow == KEYBOARD_WINDOW)
					enigmaInterfacePanel.resetPanel();

			}
		});

	}

	/**
	 * Typing observer setter
	 */
	private void setTypingObserver() {

		textPanel.setTypingObserver(new TypingObserver() {
			public void typingActionPerformed() {
				rotorPanel.setRotorPositions(enigma.getLeftRotor()
						.getRotorPosition(), enigma.getMiddleRotor()
						.getRotorPosition(), enigma.getRightRotor()
						.getRotorPosition());
			}
		});

	}

	/**
	 * Enigma Interface(keyboard and lampboard) observer setter
	 */
	private void setEnigmaInterfaceObserver() {

		enigmaInterfacePanel.setTypingObserver(new TypingObserver() {
			public void typingActionPerformed() {
				rotorPanel.setRotorPositions(enigma.getLeftRotor()
						.getRotorPosition(), enigma.getMiddleRotor()
						.getRotorPosition(), enigma.getRightRotor()
						.getRotorPosition());
			}
		});
	}

	/**
	 * Visualisation observer setter
	 */
	private void setVisualisationObserver() {
		visualisationPanel.setTypingObserver(new TypingObserver() {
			public void typingActionPerformed() {
				rotorPanel.setRotorPositions(enigma.getLeftRotor()
						.getRotorPosition(), enigma.getMiddleRotor()
						.getRotorPosition(), enigma.getRightRotor()
						.getRotorPosition());
			}

		});
	}

	/**
	 * Menu bar observer setter
	 */

	private void setMenuObserver() {

		menuBar.setMenuBarObserver(new MenuBarObserver() {
			public void displayTextArea() {
				currentWindow = TEXT_WINDOW;
				textPanel.setVisible(true);
				enigmaInterfacePanel.setVisible(false);
				visualisationPanel.setVisible(false);
				add(textPanel, BorderLayout.CENTER);

				// Refresh text window
				textPanel.resetTextPanel();
				textPanel.requestFocusForInputTextArea();

			}

			public void displayKeyboard() {
				currentWindow = KEYBOARD_WINDOW;
				enigmaInterfacePanel.setVisible(true);
				textPanel.setVisible(false);
				visualisationPanel.setVisible(false);

				add(enigmaInterfacePanel, BorderLayout.CENTER);

				// Refresh Enigma interface window
				enigmaInterfacePanel.resetPanel();

			}

			public void displayVisualisation() {
				currentWindow = VISUALISATION_WINDOW;
				visualisationPanel.setVisible(true);
				textPanel.setVisible(false);
				enigmaInterfacePanel.setVisible(false);
				add(visualisationPanel, BorderLayout.CENTER);
				visualisationPanel.clearMapping();
				visualisationPanel.requestFocusForInput();
			}

		});
	}

	/**
	 * Plug in the sockets chosen in the plugboard
	 */
	private void pluginSockets() {
		enigma.initialisePlugboard();
		Scanner scanner = new Scanner(plugBoardPanel.readPlugboardField());
		while (scanner.hasNext()) {
			String plugs = scanner.next();
			if (plugs.length() == 2) {
				char firstLetter = plugs.charAt(0);
				char secondLetter = plugs.charAt(1);

				// If a letter is connected to more than one other letter, or
				// connected to itself
				if (enigma.isPaired(firstLetter)
						|| enigma.isPaired(secondLetter)
						|| (firstLetter == secondLetter)) {
					JOptionPane
							.showMessageDialog(
									null,
									"Plugboard settings were not applied! A letter cannot be "
											+ "connected to more than one other letter or connected to "
											+ "itself.",
									"ERROR: Incorrect input",
									JOptionPane.ERROR_MESSAGE);
					enigma.initialisePlugboard();
					plugBoardPanel.requestFocusForplugsTextField();

					return;
				}

				else
					// Connect the two letters in the plugboard
					enigma.plugboardPair(firstLetter, secondLetter);

			}

			else// if letters are not typed in pairs
			{
				JOptionPane
						.showMessageDialog(
								null,
								"Plugboard settings were not applied! "
										+ "Please type each two connected letters separately and add a space "
										+ "after each pair."

								, "ERROR: Incorrect input",
								JOptionPane.ERROR_MESSAGE);
				enigma.initialisePlugboard();
				plugBoardPanel.requestFocusForplugsTextField();
				return;
			}
		}
		scanner.close();

		if (currentWindow == VISUALISATION_WINDOW) {
			visualisationPanel.requestFocusForInput();
		}

		if (currentWindow == TEXT_WINDOW) {
			textPanel.requestFocusForInputTextArea();
		}

		displaySucessNotification();// Confirm settings update
	}

	/**
	 * Display a temporary notification to notify the user about changing
	 * settings success
	 */
	private void displaySucessNotification() {
		JOptionPane opt = new JOptionPane("Settings Applied!",
				JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION,
				null, new Object[] {}); // Remove buttons
		final JDialog dialog = opt.createDialog("Success");
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(700);// Wait for 0.7 second before hiding the
										// notification
					dialog.dispose();

				} catch (Exception e) {

					e.getStackTrace();
				}
			}
		}).start();
		dialog.setVisible(true);
	}

	/**
	 * Read an image file from the resources folder
	 * 
	 * @param fileName
	 * @return
	 */
	public Image readImageFromResources(String fileName) {

		try {
			return ImageIO.read(getClass()
					.getResource("/resources/" + fileName));
		} catch (Exception e1) {

			return null;
		}
	}

}

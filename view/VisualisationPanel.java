package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import view.observers.TypingObserver;
import view.visualisation.VisualPlugboard;
import view.visualisation.VisualReflector;
import view.visualisation.VisualRotor;
import model.MachineBuilder;

public class VisualisationPanel extends JPanel {

	private VisualReflector reflector;
	private VisualRotor leftRotor;
	private VisualRotor middleRotor;
	private VisualRotor rightRotor;
	private VisualPlugboard plugboard;
	private MachineBuilder enigma;
	private JTextField inputField;
	private JTextField plainTextField;
	private JTextField encryptedTextField;
	private JPanel inputPanel;
	private JPanel mappingPanel;
	private JPanel inOutPanel;
	private TypingObserver typingObserver;
	private GridBagConstraints gbc;
	private Font font;
	private Font font2;
	private JPanel labelsPanel;
	private BufferedImage image;
	private TitledBorder inTitleBorder;
	private Font font3;
	private Font font4;

	public VisualisationPanel() {

		// Set panel background image
		setPanelBackground();

		// Create fonts
		createFonts();

		// Set visualization panel layout
		setLayout(new GridBagLayout());

		// Create visualization panel components
		createPanelComponents();

		// Filter input to upper case letters only
		filterInput();

		// Add listener on input field change
		addInputListener();

		// Configure Input Panel components
		configureInputComponents();

		// Layout input panel components
		layoutInputPanel();

		// Layout mapping panel components
		layoutMappingPanel();

		// Create input panel and mapping panel
		createInOutPanel();

		// Add panels
		add(inputPanel);
		add(inOutPanel);

	}

	private void layoutMappingPanel() {
		mappingPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		mappingPanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		mappingPanel.setBackground(new Color(255, 250, 210));

		// Add mapping panel components
		mappingPanel.add(reflector);
		mappingPanel.add(leftRotor);
		mappingPanel.add(middleRotor);
		mappingPanel.add(rightRotor);
		mappingPanel.add(plugboard);

	}

	/**
	 * Layout input panel components
	 */
	private void layoutInputPanel() {
		// Set input panel layout
		inputPanel.setLayout(new GridBagLayout());

		// Create grid configuration
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 0, 5, 0);

		gbc.weightx = 0.1;
		gbc.weighty = 0.1;

		// First row
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 9;
		inputPanel.add(encryptedTextField, gbc);

		// Second row, first column
		JLabel rightLabel = new JLabel(">>");
		rightLabel.setFont(font4);
		rightLabel.setForeground(Color.WHITE);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		inputPanel.add(rightLabel, gbc);

		// Second row, middle column
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		inputPanel.add(inputField, gbc);

		// Second row, third column
		JLabel leftLabel = new JLabel("<<");
		leftLabel.setFont(font4);
		leftLabel.setForeground(Color.WHITE);

		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(leftLabel, gbc);

		// Third row
		gbc.gridwidth = 9;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		inputPanel.add(plainTextField, gbc);

	}

	/**
	 * Configure input panel components
	 */
	private void configureInputComponents() {

		Border inputBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		inTitleBorder = BorderFactory.createTitledBorder("Input");
		inTitleBorder.setTitleFont(font3);
		inTitleBorder.setTitleColor(Color.WHITE);

		// Set border for input panel
		inputPanel.setBorder(BorderFactory.createCompoundBorder(inputBorder,
				inTitleBorder));

		inputPanel.setFont(font);
		plainTextField.setFont(font);
		plainTextField.setBackground(new Color(255, 250, 210));
		plainTextField.setToolTipText("Input letters  log");
		encryptedTextField.setFont(font);
		encryptedTextField.setForeground(new Color(66, 148, 199));
		encryptedTextField.setBackground(new Color(255, 250, 210));
		encryptedTextField.setToolTipText("Output letters log");
		inputField.setBackground(new Color(255, 250, 210));
		inputField.setCaretColor(Color.RED);
		inputField
				.setToolTipText("Enter a single letter at a time, and see how it is mapped  "
						+ "on the right");

		plainTextField.setForeground(new Color(107, 66, 38));
		inputField.setFont(font);
		plainTextField.setEditable(false);
		encryptedTextField.setEditable(false);
		inputPanel.setOpaque(false);
	}

	/**
	 * Listen to the letters entered in the input field
	 */
	private void addInputListener() {
		plainTextField.getDocument().addDocumentListener(
				new DocumentListener() {

					public void insertUpdate(DocumentEvent e) {
						enigma.resetRotors();
						enigma.encrypt(plainTextField.getText());
						typingObserver.typingActionPerformed(); // Update rotor
																// positions

						// If no input, remove lines
						if (plainTextField.getText().length() == 0) {
							plugboard.removeMappingLines();
							rightRotor.removeMappingLines();
							middleRotor.removeMappingLines();
							leftRotor.removeMappingLines();
							reflector.removeMappingLines();

						} else // Draw lines
						{
							int letter = plainTextField.getText().charAt(
									plainTextField.getText().length() - 1) - 'A';

							// Plugboard (Start)
							plugboard.setLineIn(letter);
							if (enigma.getPairOf(letter) != -1)
								letter = enigma.getPairOf(letter);

							// From the right rotor to the left rotor (forward)
							rightRotor.setLineIn(letter);
							letter = enigma.getRightRotor().mapForward(letter);
							middleRotor.setLineIn(letter);
							letter = enigma.getMiddleRotor().mapForward(letter);
							leftRotor.setLineIn(letter);

							// Reflector
							letter = enigma.getLeftRotor().mapForward(letter);
							reflector.setLineInOut(letter);

							// From the left rotor to the right rotor (reverse)
							letter = enigma.getLeftRotor().mapReverse(
									enigma.getReflector().reflectorMap(letter));

							leftRotor.setLineOut(letter);
							letter = enigma.getMiddleRotor().mapReverse(letter);
							middleRotor.setLineOut(letter);

							letter = enigma.getRightRotor().mapReverse(letter);
							rightRotor.setLineOut(letter);

							// Plugboard (finish)
							if (enigma.getPairOf(letter) != -1)
								letter = enigma.getPairOf(letter);
							plugboard.setLineOut(letter);

							// Update encrypted text output
							encryptedTextField.setText(encryptedTextField
									.getText() + (char) (letter + 'A'));

						}

						drawLines();
					}

					public void removeUpdate(DocumentEvent e) {
						insertUpdate(e);
					}

					public void changedUpdate(DocumentEvent e) {
					}
				});

	}

	/**
	 * Pass upper case letters only and filter any other characters
	 */
	private void filterInput() {

		((AbstractDocument) inputField.getDocument())
				.setDocumentFilter(new DocumentFilter() {
					public void replace(FilterBypass fb, int offset,
							int length, String text, AttributeSet attrs)
							throws BadLocationException {

						if (text.length() != 1)
							return;

						// If it is not an alphabet letter
						if ((text.charAt(0) < 'a' || text.charAt(0) > 'z')
								&& (text.charAt(0) < 'A' || text.charAt(0) > 'Z'))
							return;

						// else, if it is an alphabet letter, make sure it is
						// upper case
						text = text.toUpperCase();

						// Update plain text field
						plainTextField.setText(plainTextField.getText() + text);
					}
				});
	}

	/**
	 * Create input panel and mapping panel components
	 */
	private void createPanelComponents() {

		mappingPanel = new JPanel();
		inputPanel = new JPanel();
		plugboard = new VisualPlugboard();
		leftRotor = new VisualRotor();
		middleRotor = new VisualRotor();
		rightRotor = new VisualRotor();
		reflector = new VisualReflector();

		inputField = new JTextField(3);

		plainTextField = new JTextField(15);

		encryptedTextField = new JTextField(15);

	}

	/**
	 * Create fonts
	 */
	private void createFonts() {
		font = new Font("Century", Font.LAYOUT_LEFT_TO_RIGHT, 16);
		font2 = new Font("Century", Font.BOLD, 16);
		font3 = new Font("Century", Font.LAYOUT_LEFT_TO_RIGHT, 25);
		font4 = new Font("Century", Font.LAYOUT_LEFT_TO_RIGHT, 50);
	}

	/**
	 * Draw mapping lines
	 */
	public void drawLines() {

		// Create and add plugboard connections
		int[][] plugboardConnections = new int[26][2];
		for (int i = 0; i < 26; i++) {
			if (enigma.getPairOf(i) == -1)
				plugboardConnections[i][0] = plugboardConnections[i][1] = i;
			else {
				plugboardConnections[i][1] = i;
				plugboardConnections[i][0] = enigma.getPairOf(i);
			}

		}
		plugboard.drawLines(plugboardConnections);
		reflector.drawLines(enigma.getReflector());
		leftRotor.drawLines(enigma.getLeftRotor());
		middleRotor.drawLines(enigma.getMiddleRotor());
		rightRotor.drawLines(enigma.getRightRotor());
		repaint();
		revalidate();
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
	 * Clear the mapping panel from mapping lines
	 * 
	 */
	public void clearMapping() {
		enigma.resetRotors();
		typingObserver.typingActionPerformed(); // Update rotor positions
		plainTextField.setText("");
		encryptedTextField.setText("");
		requestFocusForInput();
		drawLines();
	}

	/**
	 * Refresh mapping panel and remove any previously drawn lines
	 */
	public void refreshMappingPanel() {
		plainTextField.setText("");
		encryptedTextField.setText("");
		drawLines();
	}

	/**
	 * Position the cursor in the input text field
	 */
	public void requestFocusForInput() {
		inputField.requestFocusInWindow();
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
	 * Create labels for the visualization components
	 */
	private void createLabelsPanel() {
		labelsPanel = new JPanel();
		labelsPanel.setLayout(new GridLayout(1, 4));
		labelsPanel.setBackground(new Color(255, 250, 210));

		// Spaces to align it with the reflector
		JLabel reflectorLabel = new JLabel(" Reflector  ");

		// Spaces to align it with rotor
		JLabel lRotorLabel = new JLabel(" Left Rotor        ");

		JLabel mRotorLabel = new JLabel("Middle Rotor");
		JLabel rRotorLabel = new JLabel("Right Rotor");
		JLabel plubBoardLabel = new JLabel("Plugboard");
		reflectorLabel.setFont(font2);
		reflectorLabel.setHorizontalAlignment(JLabel.CENTER);
		lRotorLabel.setHorizontalAlignment(JLabel.CENTER);
		mRotorLabel.setHorizontalAlignment(JLabel.CENTER);
		rRotorLabel.setHorizontalAlignment(JLabel.CENTER);
		plubBoardLabel.setHorizontalAlignment(JLabel.CENTER);

		lRotorLabel.setFont(font2);
		mRotorLabel.setFont(font2);
		rRotorLabel.setFont(font2);
		plubBoardLabel.setFont(font2);

		labelsPanel.add(reflectorLabel);
		labelsPanel.add(lRotorLabel);
		labelsPanel.add(mRotorLabel);
		labelsPanel.add(rRotorLabel);
		labelsPanel.add(plubBoardLabel);

	}

	/**
	 * Create the input panel and mapping panel
	 */
	private void createInOutPanel() {
		createLabelsPanel();
		inOutPanel = new JPanel();
		inOutPanel.setLayout(new BorderLayout());
		inOutPanel.add(labelsPanel, BorderLayout.NORTH);
		inOutPanel.add(mappingPanel, BorderLayout.CENTER);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null)
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	/**
	 * Set the panel background image
	 */

	private void setPanelBackground() {
		try {
			image = javax.imageio.ImageIO.read(getClass().getResource(
					"/resources/keyboardbg.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

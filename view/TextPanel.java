package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.text.AttributeSet;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.JSplitPane;
import javax.swing.text.AbstractDocument;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import view.observers.TypingObserver;
import model.MachineBuilder;

public class TextPanel extends JPanel {

	private MachineBuilder enigma;
	private JTextArea inputTextArea;
	private JTextArea outputTextArea;
	private JSplitPane splitPane;
	private JScrollPane inputScrollPane;
	private JScrollPane outputScrollPane;
	private TypingObserver theTypingObserver;
	private Font font;
	private BufferedImage image;

	public TextPanel() {

		// Set panel background image
		setPanelBackground();

		// set font preferences
		font = new Font("Century", Font.LAYOUT_LEFT_TO_RIGHT, 22);

		// Setting the panel layout to GridLayout
		setLayout(new GridLayout());
		setBackground(Color.GREEN);

		// Set external borders
		Border externalBorder = BorderFactory.createEmptyBorder(30, 30, 30, 30); // Setting
																					// external
																					// borders
		setBorder(externalBorder);

		// creating and configuring the Text Areas
		inputTextArea = new JTextArea();
		inputTextArea.setFont(font);
		inputTextArea.setBackground(new Color(255, 250, 210));
		inputTextArea
				.setToolTipText("Type the text you want to encrypt/decrypt here");
		inputTextArea.setCaretColor(Color.RED);
		outputTextArea = new JTextArea();
		outputTextArea.setFont(font);
		outputTextArea.setEditable(false);
		outputTextArea.setBackground(new Color(255, 250, 210));
		outputTextArea.setForeground(new Color(107, 66, 38));
		inputTextArea
				.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		outputTextArea.setBorder(BorderFactory
				.createEmptyBorder(10, 10, 10, 10));
		
		// configuring the scroll panes
		inputScrollPane = new JScrollPane(inputTextArea);
		outputScrollPane = new JScrollPane(outputTextArea);

		inputScrollPane.setBorder(BorderFactory.createCompoundBorder(
				externalBorder, BorderFactory.createTitledBorder("Input")));
		inputScrollPane.setBackground(new Color(255, 250, 210));

		outputScrollPane.setBorder(BorderFactory.createCompoundBorder(
				externalBorder, BorderFactory.createTitledBorder("Output")));
		outputScrollPane.setBackground(new Color(255, 250, 210));

		// configuring the split pane
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, inputScrollPane,
				outputScrollPane);
		splitPane.setDividerSize(5);
		splitPane.setResizeWeight(0.5);
		splitPane.setContinuousLayout(true);

		add(splitPane);// Add the splitPane to the panel

		// Change alphabet input to upper case and ignore any other characters
		setInputAlphabetToUpperCase();

		// document change observer
		setDocChangeObserver();
	}

	/**
	 * document change observer for the input text area
	 */
	private void setDocChangeObserver() {
		inputTextArea.getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {// typing
				enigma.resetRotors();
				outputTextArea.setText(enigma.encrypt(inputTextArea.getText()));
				theTypingObserver.typingActionPerformed();
			}

			public void removeUpdate(DocumentEvent e) {// deleting
				enigma.resetRotors();
				outputTextArea.setText(enigma.encrypt(inputTextArea.getText()));
				theTypingObserver.typingActionPerformed();
			}

			// unused function
			public void changedUpdate(DocumentEvent e) {
			}
		});
	}

	/**
	 * Change alphabet input to upper case and ignore any other characters
	 */
	private void setInputAlphabetToUpperCase() {
		((AbstractDocument) inputTextArea.getDocument())
				.setDocumentFilter(new DocumentFilter() {
					public void replace(FilterBypass filterBypass, int offset,
							int length, String text, AttributeSet attributes)
							throws BadLocationException {

						for (int i = 0; i < text.length(); i++)
							// Ignore all characters except alphabet letters
							if ((text.charAt(i) < 'a' || text.charAt(i) > 'z')
									&& (text.charAt(i) < 'A' || text.charAt(i) > 'Z')
									&& text.charAt(i) != ' '
									&& text.charAt(i) != '\n')
								return;

						text = text.toUpperCase();// make sure that all letters
													// are upper case
						super.replace(filterBypass, offset, length, text,
								attributes);
					}
				});
	}

	/**
	 * typing observer setter
	 * 
	 * @param typingObserver
	 */
	public void setTypingObserver(TypingObserver typingObserver) {
		this.theTypingObserver = typingObserver;
	}

	/**
	 * Position the cursor in the input text area
	 */
	public void requestFocusForInputTextArea() {
		inputTextArea.requestFocusInWindow();
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
	 * input text area setter
	 * 
	 * @param text
	 */
	public void setInputText(String text) {
		inputTextArea.setText(text);
	}

	/**
	 * Refresh by removing clear all the old
	 */
	public void refreshTextArea() {
		inputTextArea.setText("");
	}

	/**
	 * Reset text area window
	 */

	public void resetTextPanel() {
		inputTextArea.setText("");
		theTypingObserver.typingActionPerformed();
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

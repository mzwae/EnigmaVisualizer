package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import view.observers.KeyboardObserver;

import java.awt.GridBagConstraints;

public class KeyboardPanel extends JPanel {

	private JPanel keysPanel;
	private JButton keyboardButtons[] = new JButton[26];
	private KeyboardObserver keyboardObserver;
	private Font font;
	private GridBagConstraints gridBag;
	private JPanel inputTextPanel;
	private JTextField inputText;
	private Font font2;
	private BufferedImage image;

	public KeyboardPanel() {

		font = new Font("Century", Font.LAYOUT_LEFT_TO_RIGHT, 30);
		font2 = new Font("Century", Font.BOLD, 15);
		
	
		
		// Create and configure panels
		keysPanel = new JPanel();
		inputTextPanel = new JPanel();
		keysPanel.setOpaque(false);
		inputTextPanel.setOpaque(false);
		
		
		// Create output field
		inputText = new JTextField(80);
		inputText.setEditable(false);
		inputText.setToolTipText("Input letters log");
		inputText.setBackground(new Color(255,250,210));
		inputText.setForeground(new Color(107,66,38));
		inputText.setFont(font2);
		

		// Add output field
		inputTextPanel.add(inputText);

		// Set Layout
		setLayout(new BorderLayout());
		keysPanel.setLayout(new GridBagLayout());

		// Add borders to the keyboard panel
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		// Make buttons
		makeButtons();

		// Set and configure the layout of the buttons
		layoutButtons();
		
		// Set panel background image
		setPanelBackground();
		
		// Add panel
		add(keysPanel, BorderLayout.CENTER);
		add(inputTextPanel, BorderLayout.SOUTH);

	}

	// Make and configure buttons
	private void makeButtons() {
		for (int i = 0; i < 26; i++) {

			keyboardButtons[i] = new JButton(Character.toString((char) ('A' + i)),
					new ImageIcon(getClass().getResource(
							"/gui/buttons/key.png")));
			keyboardButtons[i].setBorderPainted(false);
			keyboardButtons[i].setContentAreaFilled(false);
			keyboardButtons[i].setFocusPainted(false);
			keyboardButtons[i].setOpaque(false);
			keyboardButtons[i].setHorizontalTextPosition(SwingConstants.CENTER);
			keyboardButtons[i].setForeground(Color.WHITE);
			keyboardButtons[i].setPreferredSize(new Dimension(65, 65));
			keyboardButtons[i].setPressedIcon(new ImageIcon(getClass()
					.getResource("/gui/buttons/keyPressed.png")));
			keyboardButtons[i].setFont(font);

			final int plainLetterIndex = i;
			keyboardButtons[i].addMouseListener(new MouseListener() {

				int encryptedLetterIndex;

				public void mousePressed(MouseEvent e) {

					this.encryptedLetterIndex = keyboardObserver
							.buttonPressAction((char) ('A' + plainLetterIndex));

					// Add letter to input text and pass the character only
					inputText.setText(inputText.getText()
							+ (char) ('A' + plainLetterIndex));
				}

				public void mouseReleased(MouseEvent e) {
					keyboardObserver
							.buttonReleaseAction(this.encryptedLetterIndex);
				}

				public void mouseExited(MouseEvent e) {
				}

				public void mouseEntered(MouseEvent e) {
				}

				public void mouseClicked(MouseEvent e) {
				}
			});
		}
	}

	/**
	 * Set the layout of the buttons
	 */
	private void layoutButtons() {
		gridBag = new GridBagConstraints();
		gridBag.fill = GridBagConstraints.NONE;
		gridBag.weightx = 1;
		gridBag.weighty = 1;

		// Add the first row of the buttons
		String firstRow = "QWERTZUIO";
		for (int i = 0; i < 9; i++) {
			gridBag.gridx = i * 2;
			gridBag.gridy = 0;
			keysPanel.add(keyboardButtons[firstRow.charAt(i) - 'A'], gridBag);
		}

		// Add the second row of the buttons
		String secondRow = "ASDFGHJK";
		for (int i = 0; i < 8; i++) {
			gridBag.gridx = i * 2 + 1;
			gridBag.gridy = 1;
			keysPanel.add(keyboardButtons[secondRow.charAt(i) - 'A'], gridBag);
		}

		// Add the third row of the buttons
		String thirdRow = "PYXCVBNML";
		for (int i = 0; i < 9; i++) {
			gridBag.gridx = i * 2;
			gridBag.gridy = 2;
			keysPanel.add(keyboardButtons[thirdRow.charAt(i) - 'A'], gridBag);
		}
	}

	/**
	 * Keyboard Observer setter
	 * 
	 * @param keyboardObserver
	 */
	public void setKeyboardObserver(KeyboardObserver keyboardObserver) {
		this.keyboardObserver = keyboardObserver;
	}

	/**
	 * Input text getter
	 * 
	 * @return input text
	 */
	public JTextField getInputText() {
		return inputText;
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

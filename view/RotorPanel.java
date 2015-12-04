package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import view.observers.RotorObserver;
import model.MachineBuilder;

public class RotorPanel extends JPanel {

	private String[][] availableRotors = { MachineBuilder.I, MachineBuilder.II,
			MachineBuilder.III, MachineBuilder.IV, MachineBuilder.V };
	private String[] availableReflectors = { MachineBuilder.A,
			MachineBuilder.B, MachineBuilder.C };
	private MachineBuilder enigma;
	private RotorObserver rotorObserver;

	private GridBagConstraints layoutConstraints;
	private JComboBox reflectorComboBox;
	private JComboBox lRotorNameComboBox;
	private JComboBox mRotorNameComboBox;
	private JComboBox rRotorNameComboBox;

	private JComboBox lRotorStartComboBox;
	private JComboBox mRotorStartComboBox;
	private JComboBox rRotorStartComboBox;

	private JComboBox lRingComboBox;
	private JComboBox mRingComboBox;
	private JComboBox rRingComboBox;

	private Font font;
	private Font font2;
	private Font font3;

	private JButton applyButton;

	private JTextField lRotorPosition;
	private JTextField mRotorPosition;
	private JTextField rRotorPosition;

	private JPanel reflectorPanel;
	private JPanel lRotorPanel;
	private JPanel mRotorPanel;
	private JPanel rRotorPanel;
	private JPanel rotorsPositionPanel;

	private JLabel rotorPanelLabels[] = { new JLabel("Name"),
			new JLabel("Name"), new JLabel("Start"), new JLabel("Ring"),
			new JLabel("Name"), new JLabel("Start"), new JLabel("Ring"),
			new JLabel("Name"), new JLabel("Start"), new JLabel("Ring"),
			new JLabel("Left"), new JLabel("Middle"), new JLabel("Right") };

	private TitledBorder titleBorder[] = {
			BorderFactory.createTitledBorder("Reflector"),
			BorderFactory.createTitledBorder("Left Rotor"),
			BorderFactory.createTitledBorder("Middle Rotor"),
			BorderFactory.createTitledBorder("Right Rotor"),
			BorderFactory.createTitledBorder("Rotors Current Position") };
	private BufferedImage image;

	public RotorPanel() {
		// Set panel background image
		setPanelBackground();

		font = new Font("Century", Font.BOLD, 40);
		font2 = new Font("Century", Font.BOLD, 20);
		font3 = new Font("Century", Font.BOLD, 12);
		editLabelFont();
		editTitleBorderFont();

		// Setting the panel layout
		setLayout(new GridBagLayout());

		applyButton = new JButton("Apply Settings", new ImageIcon(getClass()
				.getResource("/gui/buttons/applyButton2.png")));

		applyButton.setBorderPainted(false);
		applyButton.setContentAreaFilled(false);
		applyButton.setFocusPainted(false);
		applyButton.setOpaque(false);
		applyButton.setHorizontalTextPosition(SwingConstants.CENTER);
		applyButton.setFont(font3);
		applyButton.setForeground(Color.white);
		applyButton.setPreferredSize(new Dimension(155, 50));
		applyButton.setPressedIcon(new ImageIcon(getClass().getResource(
				"/gui/buttons/applyButton3.png")));

		// Creating and configuring panels
		reflectorPanel = new JPanel();
		reflectorPanel.setLayout(new GridBagLayout());
		reflectorPanel.setPreferredSize(new Dimension(110, 130));
		reflectorPanel.setOpaque(false);

		lRotorPanel = new JPanel();
		lRotorPanel.setLayout(new GridBagLayout());
		lRotorPanel.setPreferredSize(new Dimension(245, 130));
		lRotorPanel.setOpaque(false);

		mRotorPanel = new JPanel();
		mRotorPanel.setLayout(new GridBagLayout());
		mRotorPanel.setPreferredSize(new Dimension(245, 130));
		mRotorPanel.setOpaque(false);

		rRotorPanel = new JPanel();
		rRotorPanel.setLayout(new GridBagLayout());
		rRotorPanel.setPreferredSize(new Dimension(245, 130));
		rRotorPanel.setOpaque(false);

		rotorsPositionPanel = new JPanel();
		rotorsPositionPanel.setLayout(new GridBagLayout());
		rotorsPositionPanel.setPreferredSize(new Dimension(250, 130));
		rotorsPositionPanel.setOpaque(false);

		// Create common comboBoxes
		String[] rotorComobBoxOptions = { "I", "II", "III", "IV", "V" };
		String[] rotorComboBoxLetterOptions = new String[26];

		// converting "rotorComboBoxLetterOptions" integers into alphabet
		// letters
		for (int i = 0; i < 26; i++)
			rotorComboBoxLetterOptions[i] = Character
					.toString((char) ('A' + i));

		// Creating and configuring the current rotor positions panel
		lRotorPosition = new JTextField("A", 1);
		lRotorPosition.setBackground(Color.BLACK);
		lRotorPosition.setForeground(Color.WHITE);
		lRotorPosition.setFont(font);
		lRotorPosition.setEditable(false);

		mRotorPosition = new JTextField("A", 1);
		mRotorPosition.setBackground(Color.BLACK);
		mRotorPosition.setForeground(Color.WHITE);
		mRotorPosition.setFont(font);
		mRotorPosition.setEditable(false);

		rRotorPosition = new JTextField("A", 1);
		rRotorPosition.setBackground(Color.BLACK);
		rRotorPosition.setForeground(Color.WHITE);
		rRotorPosition.setFont(font);
		rRotorPosition.setEditable(false);

		// Creating and configuring reflector panel
		String[] reflectorComboBoxOptions = { "A", "B", "C" };
		reflectorComboBox = new JComboBox(reflectorComboBoxOptions);
		reflectorComboBox.setSelectedIndex(1);
		reflectorComboBox.setFont(font2);

		// Creating and configuring left rotor panel
		lRotorNameComboBox = new JComboBox(rotorComobBoxOptions);
		lRotorNameComboBox.setSelectedIndex(0);
		lRotorNameComboBox.setFont(font2);

		// Creating and configuring middle rotor panel
		mRotorNameComboBox = new JComboBox(rotorComobBoxOptions);
		mRotorNameComboBox.setSelectedIndex(1);
		mRotorNameComboBox.setFont(font2);

		// Creating and configuring right rotor panel
		rRotorNameComboBox = new JComboBox(rotorComobBoxOptions);
		rRotorNameComboBox.setSelectedIndex(2);
		rRotorNameComboBox.setFont(font2);

		// Creating and setting left rotor default start position
		lRotorStartComboBox = new JComboBox(rotorComboBoxLetterOptions);
		lRotorStartComboBox.setSelectedIndex(0);
		lRotorStartComboBox.setFont(font2);

		// Creating and setting middle rotor default start position
		mRotorStartComboBox = new JComboBox(rotorComboBoxLetterOptions);
		mRotorStartComboBox.setSelectedIndex(0);
		mRotorStartComboBox.setFont(font2);

		// Creating and setting right rotor default start position
		rRotorStartComboBox = new JComboBox(rotorComboBoxLetterOptions);
		rRotorStartComboBox.setSelectedIndex(0);
		rRotorStartComboBox.setFont(font2);

		// Creating and setting left rotor ring default position
		lRingComboBox = new JComboBox(rotorComboBoxLetterOptions);
		lRingComboBox.setSelectedIndex(0);
		lRingComboBox.setFont(font2);

		// Creating and setting middle rotor ring default position
		mRingComboBox = new JComboBox(rotorComboBoxLetterOptions);
		mRingComboBox.setSelectedIndex(0);
		mRingComboBox.setFont(font2);

		// Creating and setting right rotor ring default position
		rRingComboBox = new JComboBox(rotorComboBoxLetterOptions);
		rRingComboBox.setSelectedIndex(0);
		rRingComboBox.setFont(font2);

		// setting rotor panels borders
		Border externalBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);
		reflectorPanel.setBorder(BorderFactory.createCompoundBorder(
				externalBorder, titleBorder[0]));
		lRotorPanel.setBorder(BorderFactory.createCompoundBorder(
				externalBorder, titleBorder[1]));
		mRotorPanel.setBorder(BorderFactory.createCompoundBorder(
				externalBorder, titleBorder[2]));
		rRotorPanel.setBorder(BorderFactory.createCompoundBorder(
				externalBorder, titleBorder[3]));
		rotorsPositionPanel.setBorder(BorderFactory.createCompoundBorder(
				externalBorder, titleBorder[4]));

		// Creating and setting the layout configuration
		layoutConstraints = new GridBagConstraints();
		layoutConstraints.anchor = GridBagConstraints.NORTHWEST;
		layoutConstraints.weightx = 2;
		layoutConstraints.weighty = 2;

		// Add Reflector panel components
		gridPosition(0, 0);
		reflectorPanel.add(rotorPanelLabels[0], layoutConstraints);
		gridPosition(0, 1);
		reflectorPanel.add(reflectorComboBox, layoutConstraints);

		// Add Left Rotor panel components
		gridPosition(1, 0);
		lRotorPanel.add(rotorPanelLabels[1], layoutConstraints);
		gridPosition(1, 1);
		lRotorPanel.add(lRotorNameComboBox, layoutConstraints);
		gridPosition(2, 0);
		lRotorPanel.add(rotorPanelLabels[2], layoutConstraints);
		gridPosition(2, 1);
		lRotorPanel.add(lRotorStartComboBox, layoutConstraints);
		gridPosition(0, 0);
		lRotorPanel.add(rotorPanelLabels[3], layoutConstraints);
		gridPosition(0, 1);
		lRotorPanel.add(lRingComboBox, layoutConstraints);

		// Add Middle Rotor panel components
		gridPosition(1, 0);
		mRotorPanel.add(rotorPanelLabels[4], layoutConstraints);
		gridPosition(1, 1);
		mRotorPanel.add(mRotorNameComboBox, layoutConstraints);
		gridPosition(2, 0);
		mRotorPanel.add(rotorPanelLabels[5], layoutConstraints);
		gridPosition(2, 1);
		mRotorPanel.add(mRotorStartComboBox, layoutConstraints);
		gridPosition(0, 0);
		mRotorPanel.add(rotorPanelLabels[6], layoutConstraints);
		gridPosition(0, 1);
		mRotorPanel.add(mRingComboBox, layoutConstraints);

		// Add Right Rotor panel components
		gridPosition(1, 0);
		rRotorPanel.add(rotorPanelLabels[7], layoutConstraints);
		gridPosition(1, 1);
		rRotorPanel.add(rRotorNameComboBox, layoutConstraints);
		gridPosition(2, 0);
		rRotorPanel.add(rotorPanelLabels[8], layoutConstraints);
		gridPosition(2, 1);
		rRotorPanel.add(rRotorStartComboBox, layoutConstraints);
		gridPosition(0, 0);
		rRotorPanel.add(rotorPanelLabels[9], layoutConstraints);
		gridPosition(0, 1);
		rRotorPanel.add(rRingComboBox, layoutConstraints);

		// Add current rotor positions panel components
		gridPosition(0, 0);
		rotorsPositionPanel.add(rotorPanelLabels[10], layoutConstraints);
		gridPosition(0, 1);
		rotorsPositionPanel.add(lRotorPosition, layoutConstraints);
		gridPosition(1, 0);
		rotorsPositionPanel.add(rotorPanelLabels[11], layoutConstraints);
		gridPosition(1, 1);
		rotorsPositionPanel.add(mRotorPosition, layoutConstraints);
		gridPosition(2, 0);
		rotorsPositionPanel.add(rotorPanelLabels[12], layoutConstraints);
		gridPosition(2, 1);
		rotorsPositionPanel.add(rRotorPosition, layoutConstraints);

		// Add all these panels to the RotorPanel
		add(rotorsPositionPanel);
		add(reflectorPanel);
		add(lRotorPanel);
		add(mRotorPanel);
		add(rRotorPanel);
		add(applyButton);

		// Set "Apply Settings" button observer
		setApplyButtonObserver();

		// set the size
		setPreferredSize(new Dimension(100, 130));
	}

	/**
	 * Set "Apply Settings" button observer
	 */
	private void setApplyButtonObserver() {
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (lRotorNameComboBox.getSelectedIndex() == mRotorNameComboBox
						.getSelectedIndex()
						|| rRotorNameComboBox.getSelectedIndex() == mRotorNameComboBox
								.getSelectedIndex()
						|| lRotorNameComboBox.getSelectedIndex() == rRotorNameComboBox
								.getSelectedIndex()) {
					JOptionPane
							.showMessageDialog(
									null,
									"Rotor settings were not applied!"
											+ " Please choose different rotors.",
									"ERROR: Incorrect input",
									JOptionPane.ERROR_MESSAGE);
					return;
				}
				rotorObserver.configureRotors(
						availableRotors[lRotorNameComboBox.getSelectedIndex()],
						availableRotors[mRotorNameComboBox.getSelectedIndex()],
						availableRotors[rRotorNameComboBox.getSelectedIndex()],
						(char) ('A' + lRotorStartComboBox.getSelectedIndex()),
						(char) ('A' + mRotorStartComboBox.getSelectedIndex()),
						(char) ('A' + rRotorStartComboBox.getSelectedIndex()),
						(char) ('A' + lRingComboBox.getSelectedIndex()),
						(char) ('A' + mRingComboBox.getSelectedIndex()),
						(char) ('A' + rRingComboBox.getSelectedIndex()),
						availableReflectors[reflectorComboBox
								.getSelectedIndex()]);

				// Set current rotor positions
				setRotorPositions();
			}
		});
	}

	/**
	 * Set the current rotor positions in the rotor positions panel: by getting
	 * values from the constructed enigma machine
	 */
	private void setRotorPositions() {
		lRotorPosition.setText(Character.toString(enigma.getLeftRotor()
				.getRotorPosition()));
		mRotorPosition.setText(Character.toString(enigma.getMiddleRotor()
				.getRotorPosition()));
		rRotorPosition.setText(Character.toString(enigma.getRightRotor()
				.getRotorPosition()));
	}

	/**
	 * Rotor Observer Setter
	 * 
	 * @param rotorObserver
	 */
	public void setRotorObserver(RotorObserver rotorObserver) {
		this.rotorObserver = rotorObserver;
	}

	/**
	 * Rotor Positions Setter: from seeded values
	 * 
	 * @param left
	 * @param middle
	 * @param right
	 */
	public void setRotorPositions(char left, char middle, char right) {
		lRotorPosition.setText(Character.toString(left));
		mRotorPosition.setText(Character.toString(middle));
		rRotorPosition.setText(Character.toString(right));
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
	 * Grid position setter
	 * 
	 * @param x
	 *            X-Position
	 * @param y
	 *            Y-Position
	 * */
	private void gridPosition(int x, int y) {
		layoutConstraints.gridx = x;
		layoutConstraints.gridy = y;
	}

	/**
	 * Edit rotor panel labels fonts
	 */
	private void editLabelFont() {
		for (int i = 0; i < rotorPanelLabels.length; i++) {
			rotorPanelLabels[i].setFont(font3);
			rotorPanelLabels[i].setForeground(Color.WHITE);

		}
	}

	/**
	 * Edit title border fonts
	 */
	private void editTitleBorderFont() {
		for (int i = 0; i < titleBorder.length; i++) {

			titleBorder[i].setTitleFont(font3);
			titleBorder[i].setTitleColor(Color.WHITE);

		}
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
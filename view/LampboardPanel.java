package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LampboardPanel extends JPanel {

	private JLabel lamps[] = new JLabel[26];
	private JPanel lampsPanel;
	private Font font;
	private GridBagConstraints gridBag;
	private JPanel outputTextPanel;
	private JTextField outputText;
	private Font font2;
	private BufferedImage image;

	public LampboardPanel() {

		font = new Font("Century", Font.BOLD, 35);
		font2 = new Font("Century", Font.BOLD, 15);

		// Set panel background image
				setPanelBackground();

		// Create panels
		lampsPanel = new JPanel();
		outputTextPanel = new JPanel();

		// Create and configure output text field
		outputText = new JTextField(80);

		outputTextPanel = new JPanel();
		outputTextPanel.setOpaque(false);

		outputText.setEditable(false);
		outputText.setToolTipText("Output letters log");
		outputText.setEditable(false);
		outputText.setBackground(new Color(255,250,210));
		outputText.setForeground(new Color(66,148,199));
		outputText.setFont(font2);
		outputText.setToolTipText("Output letters log");


			
		// Set Layout
		setLayout(new BorderLayout());
		lampsPanel.setLayout(new GridBagLayout());

		// Add borders to lampboard panel
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


		// Make lamps in the lampboard panel
		makeLamps();
		lampsPanel.setOpaque(false);

		// Set and configure the layout of the lamps
		layoutLamps();

		// Add the output text field to the out text panel
		outputTextPanel.add(outputText);
		outputText.setFont(font2);

		// Add the lamps panel and output text panel to the lampboard panel
		add(lampsPanel, BorderLayout.CENTER);
		add(outputTextPanel, BorderLayout.NORTH);

	}

	/**
	 * Configure the layout of the lamps in the lampboard
	 */
	private void layoutLamps() {
		gridBag = new GridBagConstraints();
		gridBag.fill = GridBagConstraints.NONE;
		gridBag.weightx = 1;
		gridBag.weighty = 1;

		// Add the first row of the lamps
		String firstRow = "QWERTZUIO";
		for (int i = 0; i < 9; i++) {
			gridBag.gridx = i * 2;
			gridBag.gridy = 0;
			lampsPanel.add(lamps[firstRow.charAt(i) - 'A'], gridBag);
		}

		// Add the second row of the lamps
		String secondRow = "ASDFGHJK";
		for (int i = 0; i < 8; i++) {
			gridBag.gridx = i * 2 + 1;
			gridBag.gridy = 1;
			lampsPanel.add(lamps[secondRow.charAt(i) - 'A'], gridBag);
		}

		// Add the third row of the lamps
		String thirdRow = "PYXCVBNML";
		for (int i = 0; i < 9; i++) {
			gridBag.gridx = i * 2;
			gridBag.gridy = 2;
			lampsPanel.add(lamps[thirdRow.charAt(i) - 'A'], gridBag);
		}

	}

	/**
	 * Create and configure lamps in the lampboard panel
	 */
	private void makeLamps() {
		for (int i = 0; i < 26; i++) {
			lamps[i] = new JLabel(Character.toString((char) ('A' + i))
					, new ImageIcon(getClass().getResource(
					"/gui/buttons/lamp.png")), SwingConstants.CENTER);


			lamps[i].setOpaque(false);
			lamps[i].setHorizontalTextPosition(SwingConstants.CENTER);
			lamps[i].setForeground(Color.GRAY);
			lamps[i].setPreferredSize(new Dimension(65, 65));
			lamps[i].setFont(font);
		}
		}


	/**
	 * Switch lamp on
	 * 
	 * @param lampIndex
	 */
	public void switchLampOn(int lampIndex) {
		lamps[lampIndex].setForeground(Color.YELLOW);
	}

	/**
	 * Switch lamp off
	 * 
	 * @param lampIndex
	 */
	public void switchLampOff(int lampIndex) {
		lamps[lampIndex].setForeground(Color.GRAY);

	}

	/**
	 * Output text getter
	 * 
	 * @return output text
	 */
	public JTextField getOutputText() {
		return outputText;
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

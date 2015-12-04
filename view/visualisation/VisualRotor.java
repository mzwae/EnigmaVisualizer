package view.visualisation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Rotor;

public class VisualRotor extends JPanel {
	private Dimension dimensions;
	private GridBagConstraints gbc;
	private int lineIn = -1;
	private int lineOut = -1;
	private Font font;
	private Font font2;
	private Font theFont;

	public VisualRotor() {

		font = new Font("Century", Font.LAYOUT_LEFT_TO_RIGHT, 16);
		font2 = new Font("Century", Font.LAYOUT_LEFT_TO_RIGHT, 16);
		
		// Set layout
		setLayout(new GridBagLayout());

		// Create and configure grid
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		gbc.weighty = 1;

		// Create VisualRotor panel dimensions
		dimensions = new Dimension(230, 550);

		// Set size
		setPreferredSize(dimensions);

		
	}

	/**
	 * Draw rotor lines
	 * 
	 * @param rotor
	 */
	public void drawLines(Rotor rotor) {
		// Remove any existing mapping lines
		removeAll();

		for (int i = 0; i < 26; i++) {

			// Create and add rotor left letters column
			gbc.gridx = 0;
			gbc.gridy = i;
			JLabel letter = new JLabel(Character.toString((char) ((rotor
					.getRingPosition() - 'A' + i) % 26 + 'A')));

			letter.setFont(font);

			add(letter, gbc);

			// Create and add rotor right letters column
			gbc.gridx = 2;
			char current = (char) ((rotor.getRotorPosition() - 'A' + i) % 26 + 'A');

			String pre = rotor.getRotorNotch() == current ? "<" : "";

			letter = new JLabel(pre + current);
			if (rotor.getRotorNotch() == current) {
				theFont = font2;
				letter.setForeground(Color.MAGENTA);
			} else
				theFont = font;

			letter.setFont(theFont);

			add(letter, gbc);
		}

		int[][] connections = new int[26][2];
		for (int i = 0; i < 26; i++) {
			connections[i][1] = i;
			connections[i][0] = rotor.mapForward(i);
		}

		// Add rotor inner wirings drawing
		gbc.gridheight = 26;
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(new WiringDrawer(connections), gbc);
		gbc.gridheight = 1;
	}



	/**
	 * LineIn Setter
	 * 
	 * @param line
	 */
	public void setLineIn(int line) {
		this.lineIn = line;
	}

	/**
	 * LineOut setter
	 * 
	 * @param line
	 */
	public void setLineOut(int line) {
		this.lineOut = line;
	}

	/**
	 * Remove mapping lines
	 */
	public void removeMappingLines() {
		setLineOut(-1);
		setLineIn(-1);
	}

	/**
	 * Create rotors current position window frame Overrides:
	 * paintComponent(...) in JComponent
	 */
	public void paintComponent(Graphics g) {

		Graphics2D rotorWindowFrame = (Graphics2D) g;

		rotorWindowFrame.setColor(Color.GREEN);
		rotorWindowFrame.setStroke(new BasicStroke(3));
		rotorWindowFrame.drawRect(1, 0, dimensions.width - 6, 17);
	}
	
	/**
	 * 
	 * Inner class for drawing the inner wirings of the rotors
	 *
	 */
	private class WiringDrawer extends JPanel {
		private Color colors[] = { Color.ORANGE, Color.CYAN, Color.BLACK,
				Color.PINK, Color.GRAY, Color.MAGENTA, Color.GREEN,
				new Color(190, 140, 245), new Color(121, 7, 242),
				new Color(137, 143, 27), new Color(200, 2, 250),
				new Color(222, 166, 1), new Color(190, 140, 245),
				new Color(82, 219, 250) };
		private int[][] connections;
		private int width;

		/**
		 * Constructor
		 * 
		 * @param connections
		 */
		public WiringDrawer(int[][] connections) {
			this.connections = connections;
			width = dimensions.width - 50;
		}

		/**
		 * Paint inner wirings Overrides: paintComponent(...) in JComponent
		 */
		public void paintComponent(Graphics g) {
			for (int i = 0; i < connections.length; i++) {
				Graphics2D wiringLine = (Graphics2D) g;
				
				// Improve image quality
				wiringLine.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);

				if (lineOut == i) {
					wiringLine.setStroke(new BasicStroke(4));
					wiringLine.setColor(Color.BLUE);
				}

				else if (lineIn == i) {
					wiringLine.setStroke(new BasicStroke(4));
					wiringLine.setColor(Color.RED);
				}

				else {
					wiringLine.setStroke(new BasicStroke(1));
					wiringLine.setColor(colors[i % colors.length]);
				}
				wiringLine.drawLine(0, connections[i][0] * 21 + 12, width,
						connections[i][1] * 21 + 12);
			}
		}

		/**
		 * Get the preferred size of the generated image Overrides:
		 * getPreferredSize() in JComponent
		 */
		public Dimension getPreferredSize() {
			return new Dimension(width + 1, dimensions.height);
		}
	}
}

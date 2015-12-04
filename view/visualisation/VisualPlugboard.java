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

public class VisualPlugboard extends JPanel {
	private Dimension dimensions;
	private GridBagConstraints gbc;
	private int lineIn = -1;
	private int lineOut = -1;
	private Font font;
	private Font font2;

	public VisualPlugboard() {
		font = new Font("Century", Font.LAYOUT_LEFT_TO_RIGHT, 16);
		font2 = new Font("Century", Font.BOLD, 20);

		// Set layout
		setLayout(new GridBagLayout());

		// Create and configure grid
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		gbc.weighty = 1;

		// Create VisualPlugboard panel dimensions
		dimensions = new Dimension(160, 550);

		// Set size
		setPreferredSize(dimensions);

		// Set background color
		setBackground(new Color(255, 250, 210));

	}

	/**
	 * Draw plugboard lines
	 * 
	 * @param connections
	 */
	public void drawLines(int[][] connections) {

		// Remove any existing mapping lines
		removeAll();

		for (int i = 0; i < 26; i++) {

			// Create left letters column
			gbc.gridx = 0;
			gbc.gridy = i;
			JLabel letter = new JLabel(Character.toString((char) ('A' + i)));

			letter.setFont(font);
			add(letter, gbc);

			// Create right letters column
			gbc.gridx = 2;
			letter = new JLabel(Character.toString((char) ('A' + i)));

			letter.setFont(font);
			add(letter, gbc);

			// Add the input & output indicating arrows (<) or (>)
			gbc.gridx = 3;

			// Input arrow
			if (i == lineIn) {
				letter.setFont(font2);
				letter.setForeground(Color.RED);
				JLabel label = new JLabel("<");
				label.setForeground(Color.RED);
				label.setFont(font2);
				add(label, gbc);

				// Output arrow
			} else if (i == lineOut) {
				JLabel label = new JLabel(">");
				label.setForeground(Color.BLUE);
				label.setFont(font2);
				letter.setForeground(Color.BLUE);
				letter.setFont(font2);
				add(label, gbc);

				// Otherwise, do not add an arrow, just add a place holder
			} else {
				add(new JLabel("    "), gbc);
			}

		}

		gbc.gridheight = 26;
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(new WiringDrawer(connections), gbc);
		gbc.gridheight = 1;

	}

	/**
	 * LineIn setter
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
	 * 
	 * Inner class for drawing the inner wirings of the plugboard
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
			width = dimensions.width - 60;
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

				int x1 = 0;
				int y1 = connections[i][0] * 21 + 12;
				int x2 = width;
				int y2 = connections[i][1] * 21 + 12;

				wiringLine.drawLine(x1, y1, x2, y2);
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

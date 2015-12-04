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

import model.Reflector;

public class VisualReflector extends JPanel {
	private Font font;
	private Dimension dimensions;
	private GridBagConstraints gbc;
	private int lineInOut = -1;

	public VisualReflector() {

		font = new Font("Century", Font.LAYOUT_LEFT_TO_RIGHT, 16);

		// Set layout
		setLayout(new GridBagLayout());

		// Create and configure grid
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		gbc.weighty = 1;

		// Create VisualReflector panel dimensions
		dimensions = new Dimension(135, 550);

		// Set size
		setPreferredSize(dimensions);

		// Set background color
		setBackground(new Color(255, 250, 210));

	}

	/**
	 * Draw reflector lines
	 * 
	 * @param reflector
	 */
	public void drawLines(Reflector reflector) {
		// Remove any existing mapping lines
		removeAll();


		for (int i = 0; i < 26; i++) {

			// Create and add reflector left letters column
			gbc.gridx = 1;
			gbc.gridy = i;
			gbc.anchor = GridBagConstraints.CENTER;
			JLabel letter = new JLabel(Character.toString((char) ('A' + i)));
			letter.setFont(font);
			add(letter, gbc);
		}

		int[][] connections = new int[26][2];
		for (int i = 0; i < 26; i++) {
			connections[i][1] = i;
			connections[i][0] = reflector.reflectorMap(i);
		}

		// Create and add reflector inner wirings
		gbc.gridheight = 26;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(new WiringDrawer(connections), gbc);
		gbc.gridheight = 1;

	}

	/**
	 * lineInOut setter
	 * 
	 * @param line
	 */
	public void setLineInOut(int line) {
		this.lineInOut = line;
	}

	/**
	 * Remove mapping lines
	 */
	public void removeMappingLines() {
		setLineInOut(-1);
	}

	/**
	 * 
	 * Inner class for drawing the inner wirings of the reflector
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
			width = dimensions.width - 20;
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
				
				if (lineInOut == i || lineInOut == connections[i][0]) {
					wiringLine.setStroke(new BasicStroke(4));
					wiringLine.setColor(Color.BLACK);
				} else {
					wiringLine.setStroke(new BasicStroke(1));
					wiringLine.setColor(colors[i % colors.length]);
				}

				// Draw reflector inner wirings
				int x = 1 + i * 3;
				int y = connections[i][0] * 21 + 12;
				int height = connections[i][1] * 21 + 12 - y;
				if (height > 0)
					g.drawRect(x, y, width, height);
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

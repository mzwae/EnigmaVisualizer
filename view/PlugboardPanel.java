package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class PlugboardPanel extends JPanel {

	private JTextField plugsTextField;
	private Font font;
	private Font font2;
	private TitledBorder titleBorder;
	private BufferedImage image;

	public PlugboardPanel() {
		font = new Font("Century", Font.BOLD, 20);
		font2 = new Font("Century", Font.LAYOUT_LEFT_TO_RIGHT, 25);

		
		// Set panel background image
		setPanelBackground();
		
		// Create and configure the text field
		plugsTextField = new JTextField(50);
		plugsTextField.setFont(font);
		plugsTextField.setBackground(new Color(255,250,210));
		plugsTextField.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(255,250,210)),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		plugsTextField.setToolTipText("Type each connected pair of letters followed by a spcae and "
				+ "then press apply settings. For example, AB CD EF...");
		plugsTextField.setCaretColor(Color.RED);

		
		// Add border to the text field component
		Border externalBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
		titleBorder = BorderFactory.createTitledBorder("Plugboard");
		titleBorder.setTitleFont(font2);
		titleBorder.setTitleColor(Color.WHITE);
	
		setBorder(BorderFactory.createCompoundBorder(externalBorder, titleBorder));

		// Add the text field to the plugboard panel
		add(plugsTextField);

		// Pass capital letters only and ignore any other characters
		filterInput();
	}

	/**
	 * read the text entered in the plugboard field
	 * 
	 * @return connected letter pairs
	 */
	public String readPlugboardField() {
		return plugsTextField.getText();
	}

	/**
	 * Pass capital letters only and ignore any other characters
	 */
	private void filterInput() {

		((AbstractDocument) plugsTextField.getDocument())
				.setDocumentFilter(new DocumentFilter() {
					public void replace(FilterBypass fb, int offset,
							int length, String text, AttributeSet attrs)
							throws BadLocationException {
						for (int i = 0; i < text.length(); i++)
							if ((text.charAt(i) < 'a' || text.charAt(i) > 'z')
									&& (text.charAt(i) < 'A' || text.charAt(i) > 'Z')
									&& text.charAt(0) != ' ')
								return;
						super.replace(fb, offset, length, text.toUpperCase(),
								attrs);

					}
				});

	}

	/**
	 * Position the cursor in the plugboard text field
	 */
	public void requestFocusForplugsTextField() {
		plugsTextField.requestFocusInWindow();
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

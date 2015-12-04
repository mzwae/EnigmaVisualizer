package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class SplashScreen extends JFrame {

	private JProgressBar progressBar;
	private ImageIcon imageIcon;
	private Font font;

	public SplashScreen() {

		font = new Font("Century", Font.BOLD, 30);

		progressBar = new JProgressBar();
		progressBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
		progressBar.setStringPainted(true);
		progressBar.setPreferredSize(new Dimension(getSize().width, 27));
		progressBar.setBackground(Color.WHITE);
		progressBar.setForeground(new Color(66, 148, 199));
		progressBar.setFont(font);

		imageIcon = new ImageIcon(readImageFromResources("splashLogo.jpg"));

		JLabel logoLabel = new JLabel(imageIcon);
		logoLabel.setBorder(new LineBorder(Color.BLACK, 1));
		getContentPane().add(logoLabel, BorderLayout.CENTER);
		getContentPane().add(progressBar, BorderLayout.SOUTH);

		Dimension labelSize = logoLabel.getPreferredSize();
		setBounds(0, 0, labelSize.width,
				labelSize.height + progressBar.getPreferredSize().height);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - labelSize.width) / 2,
				(screenSize.height - labelSize.height) / 2);

		setUndecorated(true);
		setVisible(true);
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public Image readImageFromResources(String fileName) {

		try {
			return ImageIO.read(getClass()
					.getResource("/resources/" + fileName));
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}
	}

	/**
	 * Update progress bar values
	 * 
	 * @param progressBarValue
	 */
	public void updateProgressBar(final int progressBarValue) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				progressBar.setValue(progressBarValue);

			}
		});
	}

}

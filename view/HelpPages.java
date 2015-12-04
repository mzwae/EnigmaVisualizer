package view;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.*;

public class HelpPages extends JFrame {
	private JScrollPane scrollHelpPane;
	private JEditorPane helpPane;
	private String text;

	public HelpPages(String fileName, String title) {
		setTitle(title);
		setSize(800, 600);
		setIconImage(readImageFromResources("enigmaIcon.png"));
		text = getContentOf(fileName);
		helpPane = new JEditorPane("text/html", text);
		scrollHelpPane = new JScrollPane(helpPane);
		helpPane.setEditable(false);
		helpPane.setCaretPosition(0);
		add(scrollHelpPane);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Get the content from the help text file
	 * 
	 * @param file
	 * @return
	 */
	private String getContentOf(String file) {
		BufferedReader bufferReader = getBufferedReader(file);
		StringBuilder stringBuilder = new StringBuilder();
		try {
			String line;
			while ((line = bufferReader.readLine()) != null)
				stringBuilder.append(line
						+ System.getProperty("line.separator"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}

	/**
	 * Input stream reader
	 * 
	 * @param fileName
	 * @return
	 */
	private BufferedReader getBufferedReader(String fileName) {
		BufferedReader input = null;
		try {
			return new BufferedReader(new InputStreamReader(new Object()
					.getClass().getResourceAsStream("/resources/" + fileName),
					"UTF-8"));
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(1);
		} finally {
			closeStream(input);
		}
		return input;
	}

	/**
	 * Close Input Stream
	 * 
	 * @param closeable
	 */
	private void closeStream(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Read an image file from the resources folder
	 * 
	 * @param fileName
	 * @return
	 */
	public Image readImageFromResources(String fileName) {

		try {
			return ImageIO.read(getClass()
					.getResource("/resources/" + fileName));
		} catch (Exception e1) {

			return null;
		}
	}
}

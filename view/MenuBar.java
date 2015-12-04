package view;
import view.HelpPages;
import view.observers.MenuBarObserver;

import javax.swing.ButtonGroup;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar {

	private JMenuBar menuBar;

	private MenuBarObserver menuObserver;

	private JMenu display;
	private JMenu tutorial;
	private Font font;

	public MenuBar() {

		font = new Font("Century", Font.LAYOUT_LEFT_TO_RIGHT, 20);	

		// Create menu bar
		menuBar = new JMenuBar();
		
		// Configure menu bar font
		UIManager.put("Menu.font", font);

		// Create display tab
		createDisplayTab();

		//Create help tab
			createHelpTab();
		// Add tab to MenuBar
		menuBar.add(display);
		menuBar.add(tutorial);
	}

	/**
	 * Create display tab in the menu bar
	 */

	private void createDisplayTab() {
		display = new JMenu("Display");
		ButtonGroup displayGroup = new ButtonGroup();
		JRadioButtonMenuItem textArea = new JRadioButtonMenuItem("Text area");
		JRadioButtonMenuItem keyboard = new JRadioButtonMenuItem("Keyboard");
		JRadioButtonMenuItem visualisation = new JRadioButtonMenuItem(
				"Visualisation");
		keyboard.setSelected(true);
		displayGroup.add(textArea);
		displayGroup.add(keyboard);
		displayGroup.add(visualisation);

		display.add(keyboard);
		display.add(textArea);
		display.add(visualisation);


		textArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuObserver.displayTextArea();
			}
		});

		keyboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuObserver.displayKeyboard();
			}
		});
		
		visualisation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuObserver.displayVisualisation();

			}
		});

	}
	
	/**
	 * Create Help tab in the menu bar
	 */
	private void createHelpTab()
	{

		tutorial = new JMenu("Tutorial");
		JMenuItem aboutEnigma = new JMenuItem("About the Enigma machine");
		JMenuItem aboutSoftware = new JMenuItem("About the software");
		
		tutorial.add(aboutEnigma);
		tutorial.add(aboutSoftware);

		aboutEnigma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HelpPages("enigmaHelp.htm", "About Enigma");
			}
		});

		aboutSoftware.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HelpPages("softwareHelp.txt", "About Enigma Emulator Software");
			}
		});

	}

	/**
	 * Menu bar observer setter
	 * 
	 * @param menuObserver
	 */
	public void setMenuBarObserver(MenuBarObserver menuObserver) {
		this.menuObserver = menuObserver;
	}

	/**
	 * Menu bar getter
	 * 
	 * @return Menu Bar
	 * */
	public JMenuBar getMenuBar() {
		return menuBar;
	}

}

package com.setupteam.buscaminas.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.setupteam.buscaminas.controller.MenuBarCommands;
import com.setupteam.buscaminas.controller.MenuBarListener;
/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */
public class MainMenuBar extends JMenuBar {
	private JMenu gameMenu, gameModesMenu, difficultyMenu;
	private JMenu helpMenu;

	private JMenuItem exitOption;
	private JMenuItem aboutOption, guideOption;
	private JMenuItem onePlayerOption, multiplayerOption;
	private JMenuItem easyOption, mediumOption, expertOption, customOption;

	public MainMenuBar() {
		this.gameMenu = new JMenu("Juego");

		this.difficultyMenu = new JMenu("Dificultad");
		this.easyOption = new JMenuItem("Facil");
		this.mediumOption = new JMenuItem("Intermedio");
		this.expertOption = new JMenuItem("Experto");
		this.customOption = new JMenuItem("Personalizado");
		
		this.gameModesMenu = new JMenu("Modos de juego");
		this.onePlayerOption = new JMenuItem("Un jugador");
		this.multiplayerOption = new JMenuItem("Multijugador");

		this.exitOption = new JMenuItem("Salir");

		
		this.helpMenu = new JMenu("Ayuda");
		this.guideOption=new JMenuItem("Guia");
		this.aboutOption=new JMenuItem("Acerca de");
		
		this.init();
	}

	private void init() {
		MenuBarListener.getInstance().setMainMenuBar(this);
		this.onePlayerOption.setEnabled(false);
		this.customOption.setEnabled(false);

		this.setOptionsTo(this.difficultyMenu, easyOption, mediumOption, expertOption, customOption);
		this.setOptionsTo(this.gameModesMenu, onePlayerOption, multiplayerOption);
		this.setOptionsTo(this.helpMenu, guideOption, aboutOption);

		this.setActionListener(exitOption, MenuBarCommands.EXIT);
		this.setActionListener(easyOption, MenuBarCommands.EASY);
		this.setActionListener(mediumOption, MenuBarCommands.MEDIUM);
		this.setActionListener(expertOption, MenuBarCommands.EXPERT);
		this.setActionListener(customOption, MenuBarCommands.CUSTOM);
		this.setActionListener(onePlayerOption, MenuBarCommands.ONE_PLAYER);
		this.setActionListener(multiplayerOption, MenuBarCommands.MULTIPLAYER);
		this.setActionListener(guideOption, MenuBarCommands.GUIDE);
		this.setActionListener(aboutOption, MenuBarCommands.ABOUT);

		this.gameMenu.add(this.difficultyMenu);
		this.gameMenu.add(gameModesMenu);
		this.gameMenu.add(this.exitOption);

		this.add(this.gameMenu);
		this.add(this.helpMenu);
	}

	private void setActionListener(JMenuItem mi, MenuBarCommands actionCommand) {
		mi.setActionCommand(actionCommand.toString());
		mi.addActionListener(MenuBarListener.getInstance());
	}

	private void setOptionsTo(JMenu m, JMenuItem... ops) {
		for (int i = 0; i < ops.length; i++) {
			m.add(ops[i]);
		}
	}

	public JMenuItem getEasyOption() {
		return easyOption;
	}

	public JMenuItem getMediumOption() {
		return mediumOption;
	}

	public JMenuItem getExpertOption() {
		return expertOption;
	}

	public JMenuItem getCustomOption() {
		return customOption;
	}

	public JMenuItem getOnePlayerOption() {
		return onePlayerOption;
	}

	public JMenuItem getMultiplayerOption() {
		return multiplayerOption;
	}
	
	public JMenu getDifficultyMenu() {
		return difficultyMenu;
	}
}

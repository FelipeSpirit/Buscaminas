package com.setupteam.buscaminas.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.setupteam.buscaminas.utilities.MinesweeperConstants;
import com.setupteam.buscaminas.view.HostDialog;
import com.setupteam.buscaminas.view.MainMenuBar;
import com.setupteam.buscaminas.view.game.GameAreaPanel;

/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */
public class MenuBarListener implements ActionListener, MinesweeperConstants {
	private static MenuBarListener menuBarListener;

	private MainMenuBar mmb;
	private GameAreaPanel gap;
	private HostDialog hostDialog;

	private MenuBarListener() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (MenuBarCommands.valueOf(e.getActionCommand())) {
		case EASY:
			this.gap.setDifficulty(EASY);
			break;
		case MEDIUM:
			this.gap.setDifficulty(MEDIUM);
			break;
		case EXPERT:
			this.gap.setDifficulty(EXPERT);
			break;
		case CUSTOM:
			break;
		case MULTIPLAYER:
			this.mmb.getOnePlayerOption().setEnabled(true);
			this.mmb.getMultiplayerOption().setEnabled(false);
			this.mmb.getDifficultyMenu().setEnabled(false);
			this.hostDialog.setVisible(true);
			break;
		case ONE_PLAYER:
			this.mmb.getMultiplayerOption().setEnabled(true);
			this.mmb.getOnePlayerOption().setEnabled(false);
			this.mmb.getDifficultyMenu().setEnabled(true);
			break;
		case ABOUT:
			break;
		case GUIDE:
			break;
		case EXIT:
			System.exit(0);
			break;
		}
	}

	public static MenuBarListener getInstance() {
		if (menuBarListener == null)
			menuBarListener = new MenuBarListener();
		return menuBarListener;
	}

	public void setMainMenuBar(MainMenuBar mmb) {
		this.mmb = mmb;
	}

	public void setGameAreaPanel(GameAreaPanel gap) {
		this.gap = gap;
	}

	public void setHostDialog(HostDialog hostDialog) {
		this.hostDialog = hostDialog;
	}
	
	public GameAreaPanel getGameAreaPanel() {
		return gap;
	}
}

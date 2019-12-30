package com.setupteam.buscaminas.view;

import java.awt.BorderLayout;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JPanel;

import com.setupteam.buscaminas.connection.ConnectionCommands;
import com.setupteam.buscaminas.controller.MenuBarListener;
import com.setupteam.buscaminas.utilities.Constants;
import com.setupteam.buscaminas.utilities.MinesweeperConstants;
import com.setupteam.buscaminas.view.game.Cell;
import com.setupteam.buscaminas.view.game.GameAreaPanel;

/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */
public class MainPanel extends JPanel {

	private GameAreaPanel gap;
	private StatsPanel sp;

	public MainPanel(GameAreaPanel gap) {
		this.gap = gap;
		this.sp = new StatsPanel();
		this.init();
	}

	public MainPanel() {
		this.gap = new GameAreaPanel();
		this.sp = new StatsPanel();
		this.init();
	}

	private void init() {
		MenuBarListener.getInstance().setGameAreaPanel(gap);
		this.setLayout(new BorderLayout());
		this.add(this.gap, BorderLayout.CENTER);
		this.add(this.sp, BorderLayout.EAST);
	}

	public void leftClick(Point p) {
		Cell cell = this.gap.getCellMap().searchCell(p);
		if (cell != null) {
			cell.show();

			if (cell.getValue() == MinesweeperConstants.BOMB) {
				cell.explode();
				this.lose();
			} else if (this.gap.getCellMap().isComplete())
				this.win();
			else if (cell.getValue() == MinesweeperConstants.EMPTY) {
				this.gap.getCellMap().scan(cell);
				if (this.gap.getCellMap().isComplete())
					this.win();
			}
		}
	}

	public void rightClick(Point p) {
		Cell cell = this.gap.getCellMap().searchCell(p);
		cell.flag();
	}

	public void win() {
		if (Constants.client == null)
			System.out.println("Ganaste");
		else
			try {
				Constants.client.send(ConnectionCommands.WINNER);
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
			this.gap.setEnabled(false);
	}

	public void lose() {
		this.gap.getCellMap().showBombs();
		this.gap.setEnabled(false);
	}

	public GameAreaPanel getGameAreaPanel() {
		return gap;
	}
}

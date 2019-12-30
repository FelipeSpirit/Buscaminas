package com.setupteam.buscaminas.view.game;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.setupteam.buscaminas.utilities.Constants;
/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */
public class GameAreaPanel extends JPanel {

	private CellMap cellMap;

	public GameAreaPanel(CellMap cellMap) {
		this.cellMap = cellMap;
		this.setSize(this.cellMap.getSize());
	}

	public GameAreaPanel() {
		this.cellMap = new CellMap();
		this.setSize(this.cellMap.getSize());
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.cellMap.draw(g);
	}

	public CellMap getCellMap() {
		return cellMap;
	}

	public void setDifficulty(int[] difficulty) {
		Constants.minesweeper.setValues(difficulty);
		Constants.minesweeper.generateField();
		this.chargeCellMap();
	}

	public void chargeCellMap() {
		this.cellMap = new CellMap();
		this.setEnabled(true);
	}

	public void setCellMap(CellMap cellMap) {
		this.cellMap = cellMap;
		this.setSize(this.cellMap.getSize());
	}

}

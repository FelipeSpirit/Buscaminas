package com.setupteam.buscaminas.view.game;

import java.awt.Graphics;
import java.awt.Point;

import com.setupteam.buscaminas.model.entity.Minesweeper;
import com.setupteam.buscaminas.utilities.Constants;
import com.setupteam.buscaminas.utilities.MinesweeperConstants;
/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */
public class CellMap extends Figure implements MinesweeperConstants {
	private Cell[][] cells;
	private Minesweeper minesweeper;

	public CellMap(Minesweeper minesweeper) {
		super(0, 0, minesweeper.getColumns() * SIZE, minesweeper.getRows() * SIZE, CELL_COLOR);
		this.cells = new Cell[minesweeper.getRows()][minesweeper.getColumns()];
		this.minesweeper = minesweeper;
		this.createCells();
	}

	public CellMap() {
		this(Constants.minesweeper);
	}

	private void createCells() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cell(SIZE * j, SIZE * i,
						(minesweeper.getField()[i + 1][j + 1] == 0 ? minesweeper.checkBombs(i, j)
								: minesweeper.getField()[i + 1][j + 1]));
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j].draw(g);
			}
		}
	}

	public Cell searchCell(Point p) {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if (cells[i][j].isClicked(p))
					return cells[i][j];
			}
		}
		return null;
	}

	public int[] searchIndexCell(Cell c) {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if (cells[i][j].equals(c))
					return new int[] { i, j };
			}
		}
		return null;
	}

	public void scan(Cell c) {
		int[] values = searchIndexCell(c);
		scan(values[0], values[1]);
	}

	public void scan(int x, int y) {
		Cell aux;
		for (int i = 0; i < DELTA_X.length; i++) {
			if ((x + DELTA_X[i] >= 0 && x + DELTA_X[i] < cells.length)
					&& (y + DELTA_Y[i] >= 0 && y + DELTA_Y[i] < cells[0].length)) {
				aux = cells[x + DELTA_X[i]][y + DELTA_Y[i]];
				if (aux.getValue() == EMPTY
						&& this.minesweeper.getField()[1 + x + DELTA_X[i]][1 + y + DELTA_Y[i]] == EMPTY) {
					if (aux.getState().equals(State.COVERED)) {
						aux.show();
						scan(aux);
					}
				} else if (aux.getValue() != 0
						&& this.minesweeper.getField()[1 + x + DELTA_X[i]][1 + y + DELTA_Y[i]] == EMPTY) {
					aux.show();
				}
			}
		}
	}

	public void showBombs() {
		Cell aux;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				aux = cells[i][j];
				if (aux.getValue() == BOMB) {
					aux.setState(State.COVERED);
					aux.show();
					aux.explode();
				}
			}
		}
	}

	public boolean isComplete() {
		int bombs = 0;

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if (cells[i][j].getState().equals(State.FLAGGED) || cells[i][j].getState().equals(State.COVERED))
					bombs++;
			}
		}
		return bombs == this.minesweeper.getMines();
	}

	public Cell[][] getCells() {
		return cells;
	}

	public Minesweeper getMinesweeper() {
		return minesweeper;
	}
}

package com.setupteam.buscaminas.model.entity;

import com.setupteam.buscaminas.utilities.MinesweeperConstants;

/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */
public class Minesweeper implements MinesweeperConstants {
	private int rows, columns, mines;
	private byte[][] field;

	public Minesweeper(int rows, int cols, int mines) {
		this.rows = rows;
		this.columns = cols;
		this.mines = mines;
	}

	public Minesweeper(int[] values) {
		this(values[0], values[1], values[2]);
	}

	private void setPerimeter() {
		this.field = new byte[this.rows + 2][this.columns + 2];
		for (int i = 0; i < this.field[0].length; i++) {
			this.field[0][i] = LIMIT;
			this.field[this.field.length - 1][i] = LIMIT;
		}

		for (int i = 0; i < this.field.length; i++) {
			this.field[i][0] = LIMIT;
			this.field[i][this.field[0].length - 1] = LIMIT;
		}
	}

	public void generateField() {
		this.setPerimeter();

		int check;

		do {
			for (int i = 1; i < this.field.length - 1; i++) {
				for (int j = 1; j < this.field[i].length - 1; j++) {
					this.field[i][j] = EMPTY;
				}
			}

			this.generateMine(this.mines);
			check = this.countMines();

			while (this.mines > check) {
				this.generateMine(this.mines - check);
				check = this.countMines();
			}

			check = this.countMines();
		} while (check != this.mines);
	}

	private void generateMine(int mines) {
		for (int i = 0; i < mines; i++) {
			this.field[1 + RANDOM.nextInt(rows)][1 + RANDOM.nextInt(columns)] = BOMB;
		}
	}

	private int countMines() {
		int check = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (this.field[i + 1][j + 1] == BOMB)
					check++;
			}
		}
		return check;
	}

	public int checkBombs(int x, int y) {
		int bombs = 0;
		for (int i = 0; i < 8; i++) {
			if (this.field[x + DELTA_X[i] + 1][y + DELTA_Y[i] + 1] == BOMB)
				bombs++;
		}
		return bombs;
	}

	public byte[][] getField() {
		return field;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public int getMines() {
		return mines;
	}
	
	public void setValues(int rows, int cols, int mines) {
		this.rows = rows;
		this.columns = cols;
		this.mines = mines;
	}

	public void setValues(int[] values) {
		this.rows = values[0];
		this.columns = values[1];
		this.mines = values[2];
	}
	
	public void setField(byte[][] field) {
		this.field = field;
	}
}
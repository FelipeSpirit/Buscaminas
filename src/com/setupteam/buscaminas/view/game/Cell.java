package com.setupteam.buscaminas.view.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.setupteam.buscaminas.utilities.MinesweeperConstants;
/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */
public class Cell extends Figure implements MinesweeperConstants {
	private int value;
	private State state;

	public Cell(int x, int y) {
		super(x, y, SIZE, SIZE, CELL_COLOR);
		this.state = State.COVERED;
	}

	public Cell(int x, int y, int perimeter) {
		super(x, y, SIZE, SIZE, CELL_COLOR);
		this.value = perimeter;
		this.state = State.COVERED;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
		g.setColor(color);
		g.fillRect(x + 1, y + 1, width - 1, height - 1);

		switch (this.state) {
		case COVERED:
			break;
		case DISCOVERED:
			g.setColor(Color.black);
			g.drawString((this.value == EMPTY ? " " : (this.value == BOMB ? "B" : this.value)) + "", x + 6, y + 15);
			break;
		case FLAGGED:
			g.setColor(Color.black);
			g.drawString("F", x + 6, y + 15);
			break;
		}
	}

	public boolean isClicked(Point p) {
		return (p.x >= X_DELAY + this.x && p.x <= X_DELAY + this.x + this.width)
				&& (p.y >= Y_DELAY + this.y && p.y <= Y_DELAY + this.y + this.height);
	}

	public void show() {
		if (!this.state.equals(State.FLAGGED)) {
			this.state = State.DISCOVERED;
			this.color = Color.lightGray;
		}
	}

	public void flag() {
		if (this.state.equals(State.FLAGGED)) {
			this.state = State.COVERED;
			this.color = Color.gray;
		} else if (!this.state.equals(State.DISCOVERED)) {
			this.color = Color.yellow;
			this.state = State.FLAGGED;
		}
	}

	public void explode() {
		this.color = Color.red;
	}

	public int getValue() {
		return value;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}

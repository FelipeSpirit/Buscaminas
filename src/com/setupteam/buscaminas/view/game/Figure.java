package com.setupteam.buscaminas.view.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */
public abstract class Figure {
	protected int x, y, width, height;
	protected Color color;

	public Figure(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	public abstract void draw(Graphics graphics);

	public Dimension getSize() {
		return new Dimension(width, height);
	}
}

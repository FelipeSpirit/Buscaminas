package com.setupteam.buscaminas.utilities;

import java.awt.Color;
import java.util.Random;

/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */
public interface MinesweeperConstants {
	public static final int[] DELTA_X = { -1, 0, 1, -1, 1, -1, 0, 1 };
	public static final int[] DELTA_Y = { -1, -1, -1, 0, 0, 1, 1, 1 };

	public static final int[] EASY = { 8, 8, 10 };
	public static final int[] MEDIUM = { 16, 16, 40 };
	public static final int[] EXPERT = { 16, 30, 99 };

	public static final int SIZE = 20;
	public static final int EMPTY = 0;
	public static final int LIMIT = 2;
	public static final int BOMB = 9;

	public static final int X_DELAY = 8;
	public static final int Y_DELAY = 54;

	public static final byte EASY_MODE = 1;
	public static final byte MEDIUM_MODE = 2;
	public static final byte EXPERT_MODE = 3;
	public static final byte CUSTOM_MODE = 4;

	public static final Color CELL_COLOR = Color.gray;

	public static final Random RANDOM = new Random();
}

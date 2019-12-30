package com.setupteam.buscaminas.runner;

import com.setupteam.buscaminas.utilities.Constants;
import com.setupteam.buscaminas.view.MainFrame;

public class Runner {

	public static void main(String[] args) {
		Constants.minesweeper.generateField();
		new MainFrame();
	}
}
